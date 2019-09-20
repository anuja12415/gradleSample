package hello;

public class Sample {
    public static void main(String[] args){
        System.out.println(System.getenv().get("REMOTE_HOSTNAME"));
    }
}
