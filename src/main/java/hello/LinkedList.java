package hello;

public class LinkedList {
    Node head;

    //The inner class is made static so that main can access it
    static class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
            next = null;
        }
    }

    public static void main(String[] args){
        int[] myArray = {11,12,13,14};
       LinkedList linkedList = new LinkedList();
       linkedList.head = new Node(1);
       Node second = new Node(2);
       Node third = new Node(3);
       linkedList.head.next = second;
       second.next = third;
       //You need not set third.next ==  null because that is the default value you are setting in the Constructor.


        LinkedList linkedList1 = new LinkedList();

        linkedList1.head = new Node(myArray[0]);
        Node current = linkedList1.head;
        for(int i=1; i< myArray.length; i++){
            Node newNode = new Node(myArray[i]);
            current.next = newNode;
            current = newNode;
        }


        //Printing LinkedList1
        System.out.println("Printing List 1");
        current = linkedList.head;
        while(current != null){
            System.out.println(current.data);
            current = current.next;
        }

        System.out.println("Printing List 2");
        current = linkedList1.head;
        while(current != null){
            System.out.println(current.data);
            current = current.next;
        }

        //Adding a node at the beginning
        //Here even if the head of the Linkedlist is empty. The same code will work
        Node first = new Node(0);
        first.next = linkedList.head;
        linkedList.head = first;

        //Printing LinkedList1 after adding node at beginning
        System.out.println("Printing List 1 after adding node at beginning");
        current = linkedList.head;
        while(current != null){
            System.out.println(current.data);
            current = current.next;
        }

        //Adding a node at the end of the list
        //Handle edge case as well
        if(linkedList.head == null){
            linkedList.head = new Node(4);
        }else {
            current = linkedList.head;
            while (current.next != null) {
                current = current.next;
            }
            Node last = new Node(4);
            current.next = last;
        }

        //Printing LinkedList1 after adding node at the end
        System.out.println("Printing List 1 after adding node at the end");
        current = linkedList.head;
        while(current != null){
            System.out.println(current.data);
            current = current.next;
        }

        //Inserting Node in LinkedList 1 after node with value 2
        current = linkedList.head;
        int valueAfter = 2;
        if(current == null){
            System.out.println("Linked List is empty");
        }
        else {
            while (current != null && current.data != valueAfter) {
                current = current.next;
            }
            if (current == null) {
                System.out.println("Value could not be found");
            } else {
                Node currentNext = current.next;
                Node middleNode = new Node(15);
                current.next = middleNode;
                middleNode.next = currentNext;
            }
        }

        //Printing LinkedList1 after adding node after 2
        System.out.println("Printing List 1 after adding node after 2");
        current = linkedList.head;
        while(current != null){
            System.out.println(current.data);
            current = current.next;
        }

    }
}
