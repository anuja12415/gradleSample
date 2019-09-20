package hello;

public class BinarySearchTree {
    Node root;

    //This is not required because a constructor like this exists by default.
//    BinarySearchTree(){
//        root = null;
//    }
    static class Node{
        int data;
        Node left, right;
        Node(int data){
            this.data = data;
        }
    }

    public void insert(int key){
        //You are not mentioning this.root. Because it will by default pass the root of that tree itself.
        this.root = insertRec(this.root, key);
    }

    //This is pass by value. So you will have return root for the function to retain the value of new tree.
     Node insertRec(Node root, int key){
        if(root == null){
            root = new Node(key);
            return root;
        }
        else if(root.data > key){
           root.left =  insertRec(root.left, key);
        }else{
           root.right = insertRec(root.right, key);
        }
        return root;
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.data);
            inorderRec(root.right);
        }
    }

    boolean search(Node root, int key){
        if(root.data == key){
            return true;
        }
        else if(root.data > key){
            return  search(root.left, key);
        }else {
            return search(root.right, key);
        }
    }


    boolean checkBST(Node root) {
        if(root == null){
            return true;
        }else if(!((root.left != null && root.left.data < root.data) && (root.right != null && root.right.data > root.data))){
            return false;
        }else{
            return checkBST(root.left) && checkBST(root.right);
        }
    }

    public static void main(String[] args){
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(50);
        binarySearchTree.insert(30);
        binarySearchTree.insert(20);
        binarySearchTree.insert(40);
        binarySearchTree.insert(70);
        binarySearchTree.insert(60);
        binarySearchTree.insert(80);

//        binarySearchTree.inorderRec(binarySearchTree.root);
//        System.out.println(binarySearchTree.search(binarySearchTree.root, 30));
        System.out.println("Is this a Binary Search Tree " + binarySearchTree.checkBST(binarySearchTree.root));

    }
}
