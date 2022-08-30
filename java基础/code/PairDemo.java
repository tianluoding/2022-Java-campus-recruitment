import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

/**
 * PairDemo
 */
public class PairDemo {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("abc", 2);
        map.put("acd", 1);
        map.put("bcd", 0);

        List<Pair<String, Integer>> lPairs = new ArrayList<>();
        lPairs.add(new Pair<String,Integer>("abc", 0));
        lPairs.add(new Pair<String,Integer>("acd", 1));
        lPairs.add(new Pair<String,Integer>("bcd", 2));
        Comparator<Pair<String, Integer>> pairComp = (p1, p2) -> p1.getKey().compareTo(p2.getKey());
        
        lPairs.sort(pairComp);
        System.out.println(lPairs);
    }
}