package hello;

public class BinaryTree {

    Node root;

    static class Node{
        int data;
        Node left, right;
        Node(int data){
            left = right = null;
            this.data = data;
        }
    }

    public static void main(){
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = new Node(1);
        binaryTree.root.left = new Node(2);
        binaryTree.root.right = new Node(3);
        binaryTree.root.left.left = new Node(4);
    }
}
