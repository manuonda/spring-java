package tree;

import java.lang.reflect.Array;
import java.util.Arrays;



// Tutorial : https://www.youtube.com/watch?v=-DzowlcaUmE&list=PLfqMhTWNBTe0sPLFF91REaJQEteFZtLzA
public class BinaryTreeApp {

    /**
     * String args
     * @param args
     */
   public static void main(String ...args) {
        BinaryTree binaryTree = new BinaryTree();
        Node node = null;
        node = binaryTree.addRecursive(node,7);
        node = binaryTree.addRecursive(node,2);
        node = binaryTree.addRecursive(node,4);
        node = binaryTree.addRecursive(node,8);
        node = binaryTree.addRecursive(node,12);
        //System.out.println(binaryTree.toString());

        System.out.println("-------- Find Elements -----------------");
        System.out.println("Elemento 2 exist in tree :  " + binaryTree.findElement( node , 2) );
        System.out.println("Elemento 3 exist in tree :  " + binaryTree.findElement(node, 3));
        System.out.println("Elemento 4 exist in tree :  " + binaryTree.findElement(node, 4));

        int sum_all_value_nodes = binaryTree.sumAllNodes(node);
        System.out.printf("Sum All nodes : " + sum_all_value_nodes);

        System.out.println("------- Arrays  ----------");
        ArrayOperation operationArray = new ArrayOperation();
        int[] values = {-2 , -1 , 0 ,2,3,4,5};
        int resultado = operationArray.searchBinaryInArray(values, 4);
        System.out.println("Search value  : " + resultado);


        Node Node1 = new Node(1);
        Node Node2 = new Node(2);
        Node Node4 = new Node(4);
        Node Node5 = new Node(5);
        Node Node6 = new Node(6);
        Node Node3 = new Node(3);

        Node1.left = Node2;
        Node1.right = Node3;
        Node2.left = Node4;
        Node2.right = Node5;
        Node3.right = Node6;

        // Depth First Values
        System.out.println("============= Depth First Values ==================");
        String[] valuesCharacter = new String[6];

        System.out.println("==== Pre Order ========");
        binaryTree.deepFirstNode(Node1);
        System.out.println("");
        System.out.println("==== In Order ========");
        binaryTree.inOrder(Node1);
        System.out.println("");
        System.out.println("==== Post Order Transversal ========");
        binaryTree.postOrderTransversal(Node1);
        System.out.println("");
    }
}
