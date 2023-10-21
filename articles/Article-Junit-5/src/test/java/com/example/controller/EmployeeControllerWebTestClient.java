package com.example.controller;

import com.example.AbstractIntegrationTest;
import com.example.dto.EmployeeDTO;
import com.example.dto.UserDTO;
import com.example.exception.EmployeeNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.util.Asserts;
import net.minidev.json.JSONObject;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Role;
import org.springframework.http.*;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Tag("integration_resttemplate")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerWebTestClient extends AbstractIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    EmployeeDTO employeeDTO;

    @Autowired
    ObjectMapper objectMapper;


    private String url;

    private String token;

    private HttpHeaders headers;
    @BeforeEach
    void init(){
      this.objectMapper = new ObjectMapper();
      this.employeeDTO = EmployeeDTO.builder()
              .id(1).nombre("david").apellido("garcia")
              .email("manuonda32@gmail.com").build();
      this.url = "http://localhost:"+port+"/api/v1";
     // this.token = this.obtenerToken();
     // this.headers = new HttpHeaders();
     // this.headers.set("Authorization", "Bearer " + token);
     // this.headers.setContentType(MediaType.APPLICATION_JSON);
    }

    /**
     * Funcion que permite obtener token
     * @return
     */
    public String obtenerToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String urlBase = this.url +"/auth/register";
        // Crea un objeto JSON que contiene las credenciales de autenticación
        JSONObject request = new JSONObject();
        request.put("username", "david");
        request.put("lastName", "garcia");
        request.put("password","123456");

        HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);

        ResponseEntity<UserDTO> response = restTemplate.exchange(urlBase,
                HttpMethod.POST,
                entity,
                UserDTO.class);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            return response.getBody().getToken();
        } else {
            throw new RuntimeException("La autenticación falló. Estado de respuesta: " + response.getStatusCodeValue());
        }
    }
  /*
    @Test
    @DisplayName("Save Employeed")
    public void shouldSaveEmployee_whenSave_returnObjectEmployee(){
       // Crear la entidad de la solicitud con el objeto y el encabezado
        HttpEntity<EmployeeDTO> entity = new HttpEntity<>(employeeDTO, this.headers);
        ResponseEntity<EmployeeDTO> response =  this.restTemplate
                .withBasicAuth("david","123456")
                .postForEntity(this.url+"/employees/create", entity,EmployeeDTO.class);


        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().id()).isGreaterThan(0);
        Assertions.assertThat(response.getBody().equals(employeeDTO));

    }
  */
    @Test
    @DisplayName("List all employees")
    public void shouldListEmployees_whenFindAll_returnListEmployees() throws JsonProcessingException {
        // given
        ResponseEntity<EmployeeDTO> response =  this.restTemplate
                .postForEntity(this.url+"/employees/create",employeeDTO,EmployeeDTO.class);
        EmployeeDTO employeeDTO1 = EmployeeDTO.builder()
                .id(2).nombre("andres").apellido("garcia").email("manuonda64@gmail.com")
                .build();

        ResponseEntity<EmployeeDTO> response1 = this.restTemplate
                .postForEntity(this.url+"/employees/create", employeeDTO1, EmployeeDTO.class);
        //when
        ResponseEntity<EmployeeDTO[]> responseList = this.restTemplate
                .getForEntity(this.url+"/employees/list" ,EmployeeDTO[].class);

        List<EmployeeDTO> listado = Arrays.asList(responseList.getBody());

       //then

       Assert.assertEquals(HttpStatus.OK, responseList.getStatusCode());
       Assertions.assertThat(responseList.getBody().length).isEqualTo(2);
       Assert.assertEquals(responseList.getBody().length,2);
       Assert.assertEquals(MediaType.APPLICATION_JSON, responseList.getHeaders().getContentType());

       Assert.assertEquals(listado.get(0).id().intValue(),1L);
       Assert.assertEquals(listado.get(0).nombre(),"david");

        JsonNode json = objectMapper.readTree(objectMapper.writeValueAsString(listado));
        Assert.assertEquals("david", json.get(0).path("nombre").asText());
        Assert.assertEquals(1,json.get(0).path("id").asInt());

    }

    @Test
    @DisplayName("Save Employee Post for entity")
    public void shouldSaveEmployee_whenSave_returnObject(){

        //when
        ResponseEntity<EmployeeDTO> responseEntity = this.restTemplate.postForEntity(
                this.url+"/employees/create", employeeDTO, EmployeeDTO.class);

        EmployeeDTO employeeDTO1 = responseEntity.getBody();

        //then
        Assertions.assertThat(employeeDTO1).isNotNull();
        Assert.assertEquals(employeeDTO1.id().intValue(),1L);
        Assert.assertEquals(employeeDTO1.nombre(),"david");
        Assert.assertEquals(employeeDTO1.apellido(),"garcia");

    }


    @Test
    @DisplayName("Delete EmployeeDTO")
    public void shouldDeleteEmployee_whenDeleteById_returnEmptyObject(){
        // given
        ResponseEntity<EmployeeDTO> response = restTemplate.postForEntity(
              this.url+"/employees/create", employeeDTO, EmployeeDTO.class);

        EmployeeDTO employeeDTO1 = EmployeeDTO.builder()
                .id(2).nombre("andres").apellido("garcia").email("manuonda64@gmail.com")
                .build();

        ResponseEntity<EmployeeDTO> response1 = this.restTemplate
                .postForEntity(this.url+"/employees/create", employeeDTO1, EmployeeDTO.class);

        //when
        restTemplate.delete(this.url+"/employees/delete/1");
        ResponseEntity<EmployeeDTO> respuestaDelete1 = restTemplate.getForEntity(this.url+"/employees/1",EmployeeDTO.class);
        /* se puede utilizar exchange
        Map<String, Long> pathVariables =new HashMap<>();
        pathVariables.put("id", 3L);
        ResponseEntity<Void> exchange = restTemplate.exchange(
                URI.create(this.url+"/employees/{id}"), HttpMethod.GET, null, Void.class
        );*/

        //then
        Assert.assertEquals(HttpStatus.NOT_FOUND, respuestaDelete1.getStatusCode());
        Assert.assertEquals(respuestaDelete1.getBody().id(),null);
        Assertions.assertThat(respuestaDelete1.getBody().id()).isNull();

    }
}