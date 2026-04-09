package recursion.chap7;

import java.util.HashMap;
import java.util.Map;

public class FibMemo {
    public static void main(String[] args) {
        System.out.println(fibMemo(40));

        // System.out.println(fib(40));
    }

    static Map<Integer, Integer> fibCache = new HashMap<>();
    public static int fibMemo(int nthNumber) {

        if(fibCache.containsKey(nthNumber)) {
            // if the value was already cached, return it
            return fibCache.get(nthNumber);
        }

        if (nthNumber == 1 || nthNumber == 2) {
            // BASE CASE
            fibCache.put(nthNumber, 1); // update the cache
            return 1;
        } else {
            // RECURSIVE CASE
            var result = fibMemo(nthNumber - 1) + fibMemo(nthNumber - 2);
            fibCache.put(nthNumber, result); // update the cache
            return result;
        }
        
    }

    public static int fib(int nthNumber) {

  

        if (nthNumber == 1 || nthNumber == 2) {
            // BASE CASE
            
            return 1;
        } else {
            // RECURSIVE CASE
            var result = fib(nthNumber - 1) + fib(nthNumber - 2);
           
            return result;
        }

    }

}
