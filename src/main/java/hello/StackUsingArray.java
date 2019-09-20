package hello;

public class StackUsingArray {
    static final int max = 1000;
    int top;
    int a[] = new int[max];

    StackUsingArray(){
        top = -1;
    }

    boolean isEmpty(){
        return top<0;
    }

    void push(int  x){
        if(top >= max-1){
            System.out.println("Stack OverFlow");
        }else {
            a[++top] = x;
        }
    }

    int pop(){
        if(top <0){
            System.out.println("Stack UnderFlow");
            return -1;
        }else{
            return a[top--];
        }
    }

    int peek(){
        if(top < 0){
            System.out.println("Stack UnderFlow");
            return -1;
        }else{
            return a[top];
        }
    }

    public static void main(String[] args){
        StackUsingArray s = new StackUsingArray();
        s.push(10);
        s.push(20);
        s.push(30);
        System.out.println("Popped from Stack " + s.pop());
        for (int i = s.top; i >= 0; i--){
            System.out.println("Element " + s.a[i]);
        }
    }

}
