package semantic.stream;

import java.util.function.ObjIntConsumer;

/**
 * @author liuxiaodong02
 */
public final class Enumerate {

    record Pair<T>(T element, int index) {}

    private Enumerate() {
    }

    public static <T> void over(T[] array, ObjIntConsumer<T> action) {
        for (int i = 0; i < array.length; i++) {
            action.accept(array[i], i);
        }
    }

    public static <T> void over(Iterable<T> iterable, ObjIntConsumer<T> action) {
        int i = 0;
        for (T item : iterable) {
            action.accept(item, i++);
        }
    }

//    public static <T> void stream(T[] array, ObjIntConsumer<T> action) {
//        for (int i = 0; i < array.length; i++) {
//            action.accept(array[i], i);
//        }
//    }
//
//    public static <T> void over(Iterable<T> iterable, ObjIntConsumer<T> action) {
//        int i = 0;
//        for (T item : iterable) {
//            action.accept(item, i++);
//        }
//    }
}