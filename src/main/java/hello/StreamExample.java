package hello;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamExample {

    public static void main(String[] args){

           /* System.out.println(isIsogram("Dermatoglyphics"));
            System.out.println(isIsogram("isogram"));
            System.out.println(isIsogram("moose"));
            System.out.println(isIsogram("isIsogram"));
            System.out.println(isIsogram("aba"));
            System.out.println(isIsogram("moOse"));
            System.out.println(isIsogram("thumbscrewjapingly"));
            System.out.println(isIsogram(""));*/

//            System.out.println(findShort("bitcoin take over the world maybe who knows perhaps"));
        System.out.println("In main " + digital_root(16));
    }

    public static boolean  isIsogram(String str) {
        // ...

        if(str.length() == 0){
            return true;
        }
        int distinctCount = (int)str.toLowerCase().chars().distinct()
                .count();
        int actualCount = str.length();
        if(distinctCount == actualCount){
            return true;
        }
        return false;
    }

    public static int findShort(String s) {
//        String[] strArray = s.split(" ");
//        int minLength = strArray[0].length();
//        for(String str : strArray){
//            if(minLength > str.length()){
//                minLength = str.length();
//            }
//        }
//        return minLength;
        return  Stream.of(s.split(" ")).mapToInt(String::length).min().getAsInt();
    }

    public static int digital_root(int n) {
        // ...
//        System.out.println("Digital Root getting called " + n);
//        if (n < 10) {
//            return n;
//        }else{
//            int sum = 0;
//            while (n != 0)
//            {
//                sum = sum + n % 10;
//                n = n/10;
//            }
//            System.out.println(sum);
//            return digital_root(sum);
//        }
        return (n != 0 && n%9 == 0) ? 9 : n % 9;
    }

}


