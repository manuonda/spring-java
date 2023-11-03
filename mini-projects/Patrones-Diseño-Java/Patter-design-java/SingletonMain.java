public class SingletonMain {

    public static void main(String args[]) {
       System.out.println("FUNCIONA");
       Singleton singleton =  Singleton.getInstance();
       Singleton singleton1 =  Singleton.getInstance();
       System.out.print(singleton1 ==  singleton1);
    }

}
