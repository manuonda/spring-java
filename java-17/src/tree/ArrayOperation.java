package tree;

/**
 * Class Array Operation
 */
public class ArrayOperation {

    /**
     * Funcion que permite realizar
     * la busqueda de un elemento en un array ordenado
     * @param array
     * @param target
     * @return
     */
    public int searchBinaryInArray(int[] arrayElement, int target) {
        int left = 0;
        int right = arrayElement.length - 1;
        int encontrado = 0;
        while ( left <= right) {
           int indexMiddle  = (left + right) / 2;
           if ( target ==  arrayElement[indexMiddle]) {
               return target;
           } else if ( target < arrayElement[indexMiddle]) {
               right = indexMiddle -  1;
            } else {
               left = indexMiddle + 1;
            }

        }

        return encontrado ;
     }



}
