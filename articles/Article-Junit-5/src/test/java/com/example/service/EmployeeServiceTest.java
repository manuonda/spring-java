package com.example.service;


import com.example.AbstractIntegrationTest;
import com.example.domain.Employee;
import com.example.dto.EmployeeDTO;
import com.example.mapper.EmployeeMapperImpl;
import com.example.repository.EmployeeRepository;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.util.Asserts;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeServiceTest extends AbstractIntegrationTest {


    @Mock
    private EmployeeRepository  employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @Spy
    private EmployeeMapperImpl employeeMapper;

    Employee employee;

    EmployeeDTO employeeDTO;
    @BeforeEach
    void init(){
// Now working
//        this.employeeRepository = Mockito.mock(EmployeeRepository.class);
//        this.employeeMapper = Mockito.mock(EmployeeMapperImpl.class);
//        this.employeeServiceImpl = new EmployeeServiceImpl(this.employeeRepository, this.employeeMapper);

        this.employee = Employee.builder()
                .id(1).email("manuonda@gmail.com")
                .nombre("david").apellido("garcia")
                .build();
        this.employeeDTO = EmployeeDTO.builder()
                .id(1).nombre("david").apellido("garcia")
                .email("manuonda@gmail.com").build();
    }

    @Test
    @DisplayName("Test ")
    void shouldSaveWhenSaveEmployeeReturnObject() {
        //Given
        Employee employee1 = this.employeeMapper.toEmployee(this.employeeDTO);
        when(this.employeeRepository.save(employee1)).thenReturn(employee1);

        //when
        EmployeeDTO employee1DTO = this.employeeServiceImpl.saveEmployee(this.employeeDTO);

        //then
        Assertions.assertThat(employee1DTO).isNotNull();
        Assertions.assertThat(employee1DTO.id()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test find by Id")
    public void shouldEmployeeWhenFindByIdReturnObject(){
       //Given
        when(this.employeeRepository.findById(any())).thenReturn(Optional.of(this.employee));
        //When

        EmployeeDTO employeeDTO1 = this.employeeServiceImpl.findById(1);

        //Then
        //Assertions.assertTrue()
        Assertions.assertThat(employeeDTO1.id()).isEqualTo(1);
        Assertions.assertThat(employeeDTO1).isNotNull();
        org.junit.jupiter.api.Assertions.assertEquals(employeeDTO1.id(),1);
    }

    @Test
    @DisplayName("Test find by email")
    public void shouldEmploye_whenFindByEmail_returnObject(){
        when(this.employeeRepository.findByEmail("manuonda@gmail.com")).thenReturn(Optional.of(this.employee));
        //when
        EmployeeDTO dto =  this.employeeServiceImpl.findByEmail("manuonda@gmail.com");

        //then
        Assertions.assertThat(dto).isNotNull();
        Assertions.assertThat(dto.email()).isEqualTo("manuonda@gmail.com");
    }

    @Test
    @DisplayName("Test list all empty")
    public void shouldListEmptyEmployee_whenListAll_returnListObject(){
       // given
        when(this.employeeRepository.findAll()).thenReturn(Collections.emptyList());
       // when
       List<EmployeeDTO> list =  this.employeeServiceImpl.findAll();
       // then
        Assertions.assertThat(list).isEmpty();
        Assertions.assertThat(list.size()).isEqualTo(0);

        verify(this.employeeRepository).findAll();

    }

    @Test
    @DisplayName("Test list all")
    public void shouldListEmployee_whenListAll_returnListObject(){
        //given
        List<Employee> list = List.of(
                Employee.builder().id(1).nombre("David").apellido("Garcia")
                        .email("manuonda@gmail.com").build()
        );
        when(this.employeeRepository.findAll()).thenReturn(list);
        //when
        List<EmployeeDTO> listAllDTO = this.employeeServiceImpl.findAll();

        Assertions.assertThat(listAllDTO).isNotEmpty();
        Assertions.assertThat(listAllDTO.size()).isGreaterThan(0);
        verify(this.employeeRepository).findAll();

    }

    @Test
    @DisplayName("Test Exception Error")
    void shouldError_whenReturnList(){
        
    }

   
}
