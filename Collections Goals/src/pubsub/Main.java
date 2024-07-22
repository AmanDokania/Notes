package pubsub;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Map<Integer,Integer> map = new HashMap<>();
        map.putIfAbsent(1,13);
        Map<Integer, List<Integer>> listMap = new HashMap<>();
//        listMap.putIfAbsent(12,new ArrayList<>());
//        listMap.get(12).add(34);
        listMap.computeIfAbsent(12, k-> null);
        System.out.println(listMap);



        List<Integer> ageList = List.of(1,2,12,45,13,131,13,14,323,13124,213131,13124,21313,13313);
        boolean count = ageList.stream()
                .peek(age-> System.out.println("Current Age : "+age))
                .filter(age-> age>20)
                .peek((age)-> System.out.println("Age After filtration: "+age))
                .map(age -> age+20)
                .peek(age -> System.out.println("Age After Modification: " + age))
                .anyMatch(age -> age==65);
        System.out.println(count);


    }
}