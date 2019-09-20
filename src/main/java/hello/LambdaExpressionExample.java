package hello;

import java.util.ArrayList;
import java.util.List;

public class LambdaExpressionExample {

        public static void main(String[] args){
        Drawable d = () -> {
            System.out.println("Method of Drawable is called example 1");
        };


            Drawable d2 = () -> {
                System.out.println("Method of Drawable is called example 2");
            };


            //Here using Lambda expression you are creating different implementaion of the same method for different objects.
        d.draw();
        d2.draw();
            System.out.println("Hello Anuja");



            List<String> list=new ArrayList<String>();
            list.add("ankit");
            list.add("mayank");
            list.add("irfan");
            list.add("jai");

            list.forEach(System.out::println);

        }

}
