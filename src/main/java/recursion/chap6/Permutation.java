package recursion.chap6;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    
    public static void main(String[] args) {
        System.out.println(permutation("ABCD"));
    }

    public static List<String> permutation(String chars) {
        if(chars.length() == 0 || chars.length() == 1) {
            // BASE CASE
            return List.of(chars);
        }

        var perms = new ArrayList<String>();
        
        String head = chars.substring(0, 1);
        String tail = chars.substring(1);

        List<String> tailPerms = permutation(tail);
        for (var tailPerm : tailPerms) {
            for (int i = 0; i < tailPerm.length() + 1; i++) {
                StringBuilder sBuilder = new StringBuilder(tailPerm);
                var newperm = sBuilder.insert(i, head).toString();
                // var newperm = tailPerm.substring(0, i) + head + tailPerm.substring(i);
                perms.add(newperm);
                
            }
        }
        return perms;
    }
}
