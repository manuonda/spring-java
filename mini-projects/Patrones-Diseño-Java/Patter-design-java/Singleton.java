/**
 * Patron de diseño Singleton
 * Es un patron Creacional que garantiza que una clase
 * tenga una sola instancia
 */
public class Singleton {

    // Instancia unica de la clase
    private static Singleton instance;

    // No permite crear instancia con el operador
    // private
    private Singleton(){

    }

    /**
     * Sin thread-safe
     * @return
     */
    // static permite acceder al metodo sin realizar
    // instancia de la clase
    public static Singleton getInstance() {
        if ( instance == null ) {
            System.out.println("Ingreso a instanciar");
            instance = new Singleton();
        }
        System.out.println("Return instancia");
        return instance;
    }

    /**
     * Esta implementación
     * usa synchronized para garantizar la exclusión mutua,
     * pero puede tener un impacto en el rendimiento.

    public static synchronized  getInstaceThread(){
        if ( instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
     */


}
