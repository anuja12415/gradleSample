package hello;

import org.apache.kafka.common.protocol.types.Field;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReflectionDemo {

    public ReflectionDemo() {
    }

    public static void main(String[] args){
       new ReflectionDemo().myNonStaticMethod();
    }

    public void myNonStaticMethod(){
        System.out.println("Hello ");
        Test test = new Test("Blah Blah");
        Class myClass = test.getClass();

        System.out.println("Name of class " + myClass.getSimpleName());

        Method[] methods = myClass.getMethods();
        for(Method method : methods){
            System.out.println(method.getName());
        }

        try {
            Constructor<Test> constructor = Test.class.getConstructor(String.class);
            System.out.println("Name of the Constructor " + constructor.getName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    class Test{
        private String s;

        public Test(String s ) {
            this.s = "Hello";
        }

        public void method1(){
            System.out.println("This is Method 1");
        }

        public void method2(){
            System.out.println("This is method 2");
        }
    }
}
