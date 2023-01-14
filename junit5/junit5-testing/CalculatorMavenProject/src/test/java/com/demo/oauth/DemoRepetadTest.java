package com.demo.oauth;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class DemoRepetadTest {

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




    //@Disabled("TODO: Still need to work on it ")
    @DisplayName("Division by zero")
    @RepeatedTest(value = 3 , name ="{displayName}. Repetition {currentRepetition} of {totalRepetitions}" )
    public void testIntegerDivision_WhenDividendIsDividedByZero_ShouldThrowAritmeticException(
            RepetitionInfo repetitionInfo,
            TestInfo testInfo
    ){
        System.out.println("Running :  "+ testInfo.getTestMethod().get().getName());
        System.out.println("Repetion #: "+ repetitionInfo.getCurrentRepetition() +
                "of "+repetitionInfo.getTotalRepetitions());
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
