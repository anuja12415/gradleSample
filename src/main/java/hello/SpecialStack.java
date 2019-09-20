package hello;

import java.util.Stack;

public class SpecialStack extends Stack<Integer> {
    Stack<Integer> min = new Stack<Integer>();


    void push(int x){
        if(isEmpty() == true){
            super.push(x);
            min.push(x);
        }else{
            super.push(x);
            int y = min.peek();
            if(x < y){
                min.push(x);
            }else{
                min.push(y);
            }
        }
    }

    public Integer pop(){
        int x = super.pop();
        min.pop();
        return x;
    }

    int getMin(){
        return min.peek();
    }

    public static void main(String[] args)
    {
        SpecialStack s = new SpecialStack();
        s.push(10);
        s.push(20);
        s.push(30);
        System.out.println(s.getMin());
        s.push(5);
        s.push(2);
        System.out.println(s.getMin());
    }

}

