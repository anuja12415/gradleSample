package hello;

public class StackUsingLinkedList {
    StackNode top;

    static class StackNode{
        int data;
        StackNode next;

        StackNode(int data){
            this.data = data;
            next = null;
        }
    }

    boolean isEmpty(){
        return (this.top == null);
    }

    void push(int data){
        StackNode newNode = new StackNode(data);

        if(top == null){
            top = newNode;
        }else {
            newNode.next = top;
            top = newNode;
        }
        System.out.println("Pushed Element " + data + " to Stack");
    }

    int pop(){
        if(top == null){
            System.out.println("Stack is Empty");
            return 0;
        }else{
            int x = top.data;
            top = top.next;
            return x;
        }
    }

    int peek(){
        if(top == null){
            System.out.println("Stack is Empty");
            return 0;
        }else{
            return top.data;
        }
    }

    public static void main(String[] args){
        StackUsingLinkedList s = new StackUsingLinkedList();
        s.push(10);
        s.push(20);
        s.push(30);
        StackNode current = s.top;
        while(current != null){
            System.out.println("Element is " + current.data);
            current = current.next;
        }

        System.out.println(s.pop() + " popped from stack");
        System.out.println("Top element is " + s.peek());
    }
}
