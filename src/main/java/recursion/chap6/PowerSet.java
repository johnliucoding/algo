package recursion.chap6;

import java.util.ArrayList;
import java.util.List;

public class PowerSet {
    public static void main(String[] args) {
        System.out.println(power("ABC"));
        
    }

    public static List<String> power(String str) {

        if(str.isEmpty()) {
            // BASE CASE empty set
            return List.of(str);
        }
        // RECURSIVE CASE
        var list = new ArrayList<String>();

        var head = str.substring(0, 1);
        var tail = str.substring(1);

        // power set that don't include the head
        var tailPowerSet = power(tail);
        
        for (String string : tailPowerSet) {
            list.add(head + string);
        }

        list.addAll(tailPowerSet);

        return list;

        
    }
}
