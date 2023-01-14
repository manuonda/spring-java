package com.demo.oauth;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Test Math Operations in Calcular class")
public class CalculatorTest {


    Calculator calculator;

    // Creation database for example
    @BeforeAll
    static void setup(){
        System.out.println("Executing @BeforeAll method.");

    }

    @AfterAll
    static void cleanup(){
        System.out.println("Executing @AfterAll method");
    }

    @BeforeEach
    void beforeEachTestMethod(){
      // Initialize object o clean up object
        calculator = new Calculator();

        System.out.println("Executing @BeforEach method.");
    }

    @AfterEach
    void afterEachMethod(){
        System.out.println("Executing @AfterEach method.");
    }


    // test<System Under Test>_< Condition or State Change>_<Expected Result>
    @DisplayName("Test 4/2  = 2")
    @Test
    public void testIntegerDivision_WhenFourIsDividedByTwo_ShouldReturnTwo(){
        System.out.println("Running  Test 4/2 =  2");
        /// AAA : Arrange , Act, Assert

        // Arrange  // Given
        int dividend = 4;
        int divisor = 2;
        int expectedResult = 2 ;

        // Act // When
        int actualResult = calculator.integerDivsion(dividend, divisor);

        // Assert // Then
        assertEquals(expectedResult, actualResult, " 4/2 did not produced 2 ");
    }

    //@Disabled("TODO: Still need to work on it ")
    @DisplayName("Division by zero")
    @Test
    public void testIntegerDivision_WhenDividendIsDividedByZero_ShouldThrowAritmeticException(){
        System.out.println("Divided by zero ");
        //fail("Not implemented yet");
        //Arrange
        int dividend = 4;
        int divisor = 0;
        String expectedExceptionMessage = "/ by zero";
        //Act
        ArithmeticException actualException = assertThrows(ArithmeticException.class,() -> {
            //Act
            calculator.integerDivsion(dividend, divisor);
        },"Division by zero should have thrown an Arithmetic Exception");
        System.out.println("actualException message: " + actualException.getMessage());
        //Assert
        assertEquals(expectedExceptionMessage , actualException.getMessage(), "Unexpected expection message");
    }

    @DisplayName("Test integer substracion [minuend, substraend, expectedResult]")
    @ParameterizedTest
    //@MethodSource - method1
    /*@CsvSource({
            "33,1,32",
            "24,1,23",
            "54,1,53"
    }) - mnethod 2*/
    @CsvFileSource(resources = "/integerDivision.csv")
    public void integerSubstraction(int minuend, int subtraend , int expectedResult ){
        System.out.println("Running Test " + minuend + " - " + subtraend + " - " + expectedResult);
        int actualResult =  calculator.integerSubstraction(minuend,subtraend);

        assertEquals(expectedResult, actualResult ,
                ()-> minuend + "-"+  subtraend + "did not produced" + expectedResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Jhon","Kate","Alice"})
    void valueSourceDemostration(String firstName){
        System.out.println(firstName);
        assertNotNull(firstName);
    }

    /**
     * MethodSource -> call integerSubstracion
     * @return

    private static Stream<Arguments> integerSubstraction(){
      return Stream.of(
              Arguments.of(33,1,32),
              Arguments.of(54,1,53),
              Arguments.of(24,1,23)
      ) ;
    }
     */

}
