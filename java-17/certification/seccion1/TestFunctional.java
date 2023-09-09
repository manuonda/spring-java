package seccion1;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class TestFunctional {
  

interface Evaluate<T>{
    boolean isNegative(T t);
}
 public static void main(String[] args) {
    System.out.println("Init main");
    TestFunctional fiApi = new TestFunctional();

    // Evalute (T) is a functional interface i.e one abstract method:
    // boolean isNegative(T t) // similar to java.util.function.Predicate
    Evaluate<Integer> lambda = i -> i < 0;
    System.out.println("Evalute : " + lambda.isNegative(-1));
    System.out.println("Evalute : " + lambda.isNegative(1));

    // Predicate<T> is a functional interface i.e one abstract method
    // boolean test
    Predicate<Integer> predicate = i -> i < 0 ;
    System.out.println("Predicate : " + predicate.test(-1));
    System.out.println("Predicate : " + predicate.test(1));

    int x = 4;
    System.out.println("Is "+ x + " even ? "+ check(x, n -> n % 2 == 0));
    x = 7;
    System.out.println("Is "+ x + " even ? "+ check(x, n -> n % 2 == 0));
    
    String name = "Mr. Joe Bloggs";
    System.out.println("Does "+name + " start with Mr. ? " + check(name, s -> s.startsWith("Mr")));
    name  = "Ms. Ann Bloggs";
    System.out.println("Does "+name + " start with Mr. ? " + check(name, s -> s.startsWith("Mr")));
    
    fiApi.predicate();
    fiApi.supplier(); 
    fiApi.consumer();
    fiApi.function();
    fiApi.unaryBinaryOperation();
 }

 public static <T> boolean check(T t, Predicate<T> lambda) {
    return lambda.test(t);
 }

 public void predicate(){
    //Predicate<T> is a functional interface i.e. one abstract method: 
    // boolean test(T t);
    Predicate<String> pStr = s -> s.contains("City");
    System.out.println(pStr.test("Vatican City"));

    //BiPredicate<T,U> is a functional interface i.e one abstract method:
    //boolean test(T t, U u)
    BiPredicate<String,Integer> checLength = (str, leng) ->  str.length() == leng;
    System.out.println(checLength.test("Vatican City", 8));
    
 }

 // Supplier<T> is a functional  interface i.e one abstract method
 // T get()
 public void supplier() {
   Supplier<StringBuilder> supSB = () -> new StringBuilder();
   System.out.println("Supplier SB" + supSB.get().append("SK"));

   Supplier<LocalTime> supTime = () -> LocalTime.now();
   System.out.println("Supplier time" + supTime.get());

   Supplier<Double> sRandom = () -> Math.random();
   System.out.println(sRandom.get());
 }

 public void consumer(){
    // Consumer<T> is a functional interface i.e. one abstract method:
    // void accept(T t)
    
    Consumer<String> printC = s -> System.out.println(s);
    printC.accept("To be or not to be, that is the question");

    List<String> names = new  ArrayList<>();
    names.add("David");
    names.add("Andres");
    names.forEach(printC);

    //BiConsumer<T,U> is a functional interface i.e. one abstract method:
    // void accept(T t, U u);
    var mapCapitalCities = new HashMap<String,String>();
    BiConsumer<String,String> biCon = (key, value) -> mapCapitalCities.put(key , value);
    biCon.accept("Dubli","Ireland");
    biCon.accept("Washington DC","USA");
    System.out.println(mapCapitalCities);


    BiConsumer<String,String> mapPrint = (key, value) -> System.out.println(key + " is the capital of : "+ value);
    mapCapitalCities.forEach(mapPrint);
 }

 public void function(){
   // Function <T,R> is a functional interface i.e. one abstract method 
   // R apply (T t)
   Function<String, Integer> fn2 = s ->s.length();
   System.out.println("Function : " + fn2.apply("Prueba informacion"));

   // BiFunction<T, U, R> is a functional interface i.e. one abstract method:
   // R apply()
   BiFunction<String,String, Integer> biFn = (s1, s2) -> s1.length() + 
                              s2.length();
   System.out.println(" BiFunction : " + biFn.apply("William", "Shakespeare"));

   BiFunction<String, String, String> biFun2 = (s1, s2) -> s1.concat(s2);
   System.out.println("BiFunction : " + biFun2.apply("William", "Shakespeare"));
 }

 public void unaryBinaryOperation(){
   // UnaryOperation<T> extends Function<T, T> is a functional interface .ie
   // one abstract method
   // T apply(T t)
   UnaryOperator<String> unaryOp = name -> "My name is" + name;
   System.out.println("UnaryOperation : " + unaryOp.apply("Sean"));
   
   // BinaryOperation<T> extends Function<T,T,T> is a functional interface
   // i.e. one abstract method
   // T apply(T t1, T t2)
   BinaryOperator<String> binaryOp = (s1 ,s2)-> s1.concat(s2);
   System.out.println("BinaryOperation : "+ binaryOp.apply("William", "Shakespeare"));

 }
}