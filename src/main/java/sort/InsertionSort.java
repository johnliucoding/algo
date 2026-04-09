package basic;

import java.util.Arrays;

public class InsertionSort {
    // considering one element at a time,
    // placing the element in the correct order relative to those before it
    // maintaining the sorted front part

    // aka. insertion in a sorted array

    public static void main(String[] args) {
        var data = new char[] {'r', 'a', 'c', 'b'};
        insertionSort(data);
        System.out.println(Arrays.toString(data));
    }

    // two loops
    public static void insertionSort(char[] data) {
        for (var i = 1; i< data.length; i++) {
            var current = data[i];
            int j = i;
            while (j > 0 && data[j-1] > current) {
                data[j] = data[j-1];
                j--;
            }
            data[j] = current;
        }
    }

    // common tasks for array

    // equals(A,B)
    // toString(A)
    // hashCode(A)

    // compare
    // compareUnsigned
    // mismatch

    // fill(A, x)
    // copyOf(A,n)
    // copyOfRange(A, s, t)

    // sort(A)
    // binarySearch(A, x)


    // asList


    // setAll(T[] array, IntFunction<? extends T> generator), i -> ? extends T
    //IntStream.range(startInclusive, endExclusive)
    //          .forEach(i -> array[i] = generator.apply(i));

    //parallelPrefix(T[] array, BinaryOperator<T> op)

    // Stream(A)



}
