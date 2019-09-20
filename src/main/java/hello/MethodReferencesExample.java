package hello;

import org.apache.kafka.common.protocol.types.Field;

interface  Sayable{
    void say();
}

class MethodReference {
    static void mymethod(){
        System.out.println("Hello this is static method");
    }
}

public class MethodReferencesExample {

    public static void main(String[] args){
        Sayable sayObject = MethodReference::mymethod;

        sayObject.say();
    }
}
