package tree;

/**
 * Binary Tree
 */
public class BinaryTree {

    /**
     * Function add value in tree
     * @param current
     * @param value
     * @return
     */
    public Node addRecursive(Node current, int value) {

         // add new element es un node null
         if  ( current  == null) {
            return new Node(value);
        }
        //System.out.printf(" current value : %d , value :%d", current.value, value);
        // tree left
         if ( value < current.value ) {
           current.left =  addRecursive(current.left, value);
        } // tree rigth
         else if ( value >  current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            // value already exists
           return current;
        }
        return current;
    }

    /**
     * Find Element
     * @param current
     * @param value
     * @return
     */
    public boolean findElement(Node  current, int value ) {

        if ( current == null) {
            return false;
        }
        if ( value == current.value ) {
            return true;
        }

        return value < current.value ?  findElement(current.left, value) :
                  findElement(current.right, value);
    }

    /**
     * Function that sum all nodes of tree
     * @param current
     * @return
     */
    public int sumAllNodes(Node current) {
        // instance 0
        if (current == null ) {
            return 0;
        }
        return current.value + sumAllNodes(current.left)+ sumAllNodes(current.right);
    }

    /**
     * Algoritmo DFS or BFS
     * //      1
     * //    /   \
     * //   2     3
     * //  / \     \
     * // 4   5     6
     *
     * return : //    -> [1,2,4,5,3,6]
     * @param current
     * @return
     */
   // preorder
    public void deepFirstNode(Node current) {
         if(current == null) {
           return;
         }
        System.out.print(current.value + ",");
         deepFirstNode(current.left);
         deepFirstNode(current.right);
    }



    /**
     * Algoritmo InOrder Transversal
     * //      1
     * //    /   \
     * //   2     3
     * //  / \     \
     * // 4   5     6
     *
     * return : //    -> [4,2,5,1,3,6]
     * @param current
     * @return
     */
    public void inOrder(Node current) {
        if(current == null) {
            return;
        }

        // Se ejecuta recursive el arbol izquierdo
        // recorriendo todos los nodos correspondientes
        deepFirstNode(current.left);
        // los imprime
        System.out.print(current.value + ",");
        // recorre todos los nodde de la derecha
        deepFirstNode(current.right);
    }

    /**
     * Algoritmo postOrder Transversal
     * //      1
     * //    /   \
     * //   2     3
     * //  / \     \
     * // 4   5     6
     *
     * return : //    -> [4,5,2,6,3,1]
     * @param current
     * @return
     */
    public void  postOrderTransversal(Node current) {
        if(current == null) {
            return ;
        }

        postOrderTransversal(current.left);
        postOrderTransversal(current.right);
        System.out.print(current.value + ",");
    }

}
