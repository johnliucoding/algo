package modern;
// 1 write tests to evaluate code correctness: verifies correctness
//   and refactor without regression

// 2 destructively sorting

// 3 TDD: writing test first, modify the code to make test pass： gamifies the assessment process

//Identify a new feature.
//Write a unit test for that feature.
//Run the test. It should fail.
//Write code that passes the test. Yay!
//Optional: refactor code to make it faster, cleaner, etc. Except now we have a reference to tests that should pass.


// 4 Test: setup -> exercise -> checking
//      exception -> error reporting

// invariants

// breaking problem in smaller problems: using comments
//  find the smallest item
//  move it to the front
//  selection sort the remaining n-1 items(without touching the front item)


// Comparable


// debug
//     breakpoint
//     visual debugging
//     stepping through the code line-by-line
//     step over  vs. step into


// iterating on a design

// recursive helper method
    // a private helper method with an additional parameter that delineate which part of the array
    // to consider
// this approach is quite common when trying to use recursion on a data structure that is not inherently
// recursive, e.g. arrays

// sub-array, sub-array-view
// range
// a subset of a larger array


// downsides to unit testing:
// writing thorough tests takes time, it's easy to write incomplete unit tests which give a false confidence
// difficult to write tests for units that depend on other units

// unit testing in general is most definitely a good idea with or without TDD

// Integration Testing: verifies that components interact properly together.
public class Sort {

    /**
     * Sorts strings destructively.
     *
     * @param strArr
     */
    public static void selectionSort(String[] strArr) {


        sort(strArr, 0);
    }

    public static void selectionSortIterative(String[] strArr) {
        // swapping smallest to the front
        for (int i = 0; i < strArr.length; i++) {
            final int smallest = smallest(strArr, i);
            swap(strArr, i, smallest);
        }
    }

    private static void sort(String[] strArr, int start) {
        if (start == strArr.length) {
            return;
        }
        int smallest = smallest(strArr, start);
        swap(strArr, start, smallest);
        sort(strArr, start + 1);
    }

    public static int smallest(String[] strArr, int start) {
        var smallest = start;
        for (int i = start + 1; i < strArr.length; i++) {
            if (strArr[i].compareTo(strArr[smallest]) < 0) {
                smallest = i;
            }
        }
        return smallest;
    }

    public static void swap(String[] strArr, int a, int b) {
        String temp = strArr[a];
        strArr[a] = strArr[b];
        strArr[b] = temp;
    }
}
