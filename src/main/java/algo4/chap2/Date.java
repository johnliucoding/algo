package chap2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public record Date(int day, int month, int year) implements Comparable<Date> {

    public Date {
        // basic validation
        if(day > 31) {throw new IllegalArgumentException("day too big");}
        if(month > 12) {throw new IllegalArgumentException("month too big");}
    }

    @Override
    public int compareTo(Date o) {
        // use comparator 写 comparable ？

        return Comparator
                .comparingInt(Date::year)
                .thenComparing(Date::month)
                .thenComparing(Date::day)
                .compare(this, o);
    }


}
