package recursion.chap3;

import java.util.ArrayList;
import java.util.List;

public class SumList {

    public static void main(String[] args) {
        var listOne = List.of(1,2,3,4,5);
        var result = sum(listOne);
        System.out.println(result);

        var listTwo = List.of(1,10,100,1000);
        int resultTwo = sum(listTwo);
        System.out.println(resultTwo);
    }

    public static int sum(List<Integer> intList) { // 1 intList is mutated by recursive function
                                                   // 2 new intlist for each recursive call
                                                   // 3 position index
        if(intList.size() == 0) {
            return 0;
        } else {
            var head = intList.get(0);
            
            
            var tail = new ArrayList<Integer>(intList.subList(1, intList.size()));

            return head + sum(tail);
        }
    }
    
}
