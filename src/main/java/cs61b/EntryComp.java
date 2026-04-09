package cs61b;

import com.google.common.collect.Multiset.Entry;
import com.google.common.primitives.Ints;

import java.util.Comparator;

public enum EntryComp implements Comparator<Entry<?>> {

    DESCENDING("hello", 4) {
        @Override
        public int compare(final Entry<?> a, final Entry<?> b) {
            return Ints.compare(b.getCount(), a.getCount());
        }
    },
    ASCENDING("world", 5) {
        @Override
        public int compare(final Entry<?> a, final Entry<?> b) {
            return Ints.compare(a.getCount(), b.getCount());
        }
    };

    private String name;
    private int age;

    EntryComp(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

