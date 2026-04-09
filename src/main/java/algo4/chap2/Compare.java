package chap2;

import java.util.ArrayList;
import java.util.Comparator;

import static java.util.Comparator.*;

public class Compare {
    public static void main(String[] args) {
        var list = new ArrayList<People>();

        final People people = new People(null, (short)10);

        list.add(people);


        final Comparator<People> reversed = comparingInt(People::score).reversed();

        list.sort(comparing(People::name, nullsFirst(naturalOrder()))
                .thenComparing(
                        comparing(People::score, Short::compareUnsigned).reversed()
                )
        );

    }
}

record People(String name, short score) {}
