package com.example.controller;


import com.example.AbstractIntegrationTest;
import com.example.domain.Employee;
import com.example.dto.EmployeeDTO;
import com.example.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.Matchers;


@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc
public class EmployeeControllerTest extends AbstractIntegrationTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    Employee employee;

    ObjectMapper objectMapper;

    @BeforeEach
    public void init(){
     employee = Employee.builder()
             .id(1).nombre("david")
             .apellido("garcia").email("manuonda@gmail.com")
             .build();
     objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Save Employee Controller")
    @WithMockUser(roles="ADMIN")
    public void save() throws Exception {
        //given
        EmployeeDTO employeeDTO = EmployeeDTO.builder().id(1)
                        .nombre("david").apellido("garcia")
                        .email("manuonda@gmail.com").build();

         when(this.employeeService.saveEmployee(any())).thenReturn(employeeDTO);

         //when
        ResultActions result = mockMvc.perform(post("/api/v1/employees/create")
                .content(objectMapper.writeValueAsString(employeeDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
        ).andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").isNotEmpty());



    }

    @Test
    @DisplayName("Test List all")
    @WithMockUser(roles = "ADMIN")
    public void shouldListEmployee_whenList_returnListAllEmployee() throws Exception {
        //when
        List<EmployeeDTO> list = List.of(
                EmployeeDTO.builder().id(1).nombre("david").apellido("garcia").email("manuonda@gmail.com")
                        .build(),
                EmployeeDTO.builder().id(2).nombre("andres").apellido("garcia").email("manuonda32@gmail.com")
                        .build()
        );

        when(this.employeeService.findAll()).thenReturn(list);
        //given
        ResultActions result =  mockMvc.perform(get("/api/v1/employees/list")
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect( status().isOk())
                .andExpect(jsonPath("$.size()").value(list.size()))
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[*].id").exists());
    }

    @Test
    @DisplayName("Test find by id")
    public void shouldEmployeeById_whenFindById_returnEmployee() throws Exception {
        // given
        EmployeeDTO employeeDTO = EmployeeDTO.builder().id(1).nombre("david").apellido("garcia").build();

        when(this.employeeService.findById(1)).thenReturn(employeeDTO);

        //when
        mockMvc.perform(get("/api/v1/employees/{id}",1)
                .with(csrf())
                .with(user("admin").roles("ADMIN"))
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id",Matchers.is(1)))
                .andExpect(jsonPath("$.nombre").value("david"))
                .andExpect(jsonPath("$.apellido").value("garcia"))
                .andExpect(content().json(objectMapper.writeValueAsString(employeeDTO)));

    }
    @Test
    @DisplayName("Test find By Not found")
    @WithMockUser(roles = "ADMIN")
    public void shouldEmployeeIdInvalid_whenFindById_returnEmployeeNotFound() throws Exception {
        when(this.employeeService.findById(1)).thenReturn(null);

        ResultActions actions = mockMvc.perform(get("/api/v1/employees/{id}", 1));
        actions.andExpect(status().isNotFound());

    }



    @Test
    @DisplayName("Test delete unathourized")
    public void shouldEmployDelete_whenEmployeeIfNotAuthorized_returnNotFound() throws Exception {
        mockMvc.perform(delete("/api/v1/employees/delete/{id}",1)
                .with(csrf())
                .with(anonymous())
        ).andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Test update employeed find by id")
    public void shouldEmployeeUpdate_whenFindByIdUpdate_returnObjectEmployee() throws Exception{
        // given
        EmployeeDTO employeeDTO =EmployeeDTO.builder()
                .id(1).nombre("david").apellido("garcia").email("manuonda@gmail.com").build();

        when(this.employeeService.update(1,employeeDTO)).thenReturn(employeeDTO);
        //when
        ResultActions resultActions = mockMvc.perform(put("/api/v1/employees/update/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("admin").roles("ADMIN"))
                        .with(csrf())
                .content(objectMapper.writeValueAsString(employeeDTO)));
        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.nombre").value("david"))
                .andExpect(content().json(objectMapper.writeValueAsString(employeeDTO)));
    }


    @Test
    @DisplayName("Test find by email")
    public void shouldEmployeeByEmail_whenFindByEmail_returnObjectEmployee() throws Exception {
        //given
        EmployeeDTO employeeDTO = EmployeeDTO.builder().id(1)
                .nombre("david").apellido("garcia").email("manuonda32@gmail.com").build();

        when(this.employeeService.findByEmail("manuonda@gmail.com")).thenReturn(employeeDTO);
        //when
        ResultActions resultActions = mockMvc.perform(get("/api/v1/employees/findByEmail/{email}","manuonda32@gmail.com")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("admin").roles("ADMIN"))
                .with(csrf())
                .content(objectMapper.writeValueAsString(employeeDTO)));

         //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id",Matchers.is(1)))
                .andExpect(jsonPath("$.email").value("manuonda32@gmail.com"))
                .andExpect(content().json(objectMapper.writeValueAsString(employeeDTO)));

    }
}
