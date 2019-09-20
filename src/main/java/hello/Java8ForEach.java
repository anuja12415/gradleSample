package hello;

import java.util.*;

public class Java8ForEach {
    public static void main(String[] args){
        List<String> names  = new ArrayList<>(Arrays.asList("Anuja", "is", "a", "good", "girl"));
        names.add("very good");
        System.out.println(names.get(2));

        //This is use of Java forEach
        names.forEach(System.out::println);

        //ForEach for Map
        Map<String,String> countryCapitalMap = new HashMap<>();
        countryCapitalMap.put("US", "Washington DC");
        countryCapitalMap.put("England", "London");
        countryCapitalMap.put("France", "Paris");
        countryCapitalMap.forEach((k,v) -> {
            System.out.println(k);
            System.out.println(v);
        });


        //Double Brace Initialization of List
        List<String> myList = new ArrayList<String>(){{
            add("1");
            add("2");
            add("3");
        }};

        Collections.addAll(myList, "Adding", "elements", "to", "the", "list");
        myList.forEach(System.out::println);

        List<Integer> unmodifiableList = Collections.unmodifiableList(Arrays.asList(1,2,3));
        unmodifiableList.add(4);

    }
}
