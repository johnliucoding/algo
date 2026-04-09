package recursion.chap5;

public class BinarySearch {
    public static void main(String[] args) {
        int[] collection = { 2, 3, 4, 6, 8, 9 };

        int foundIdx = search(3, collection, 0, collection.length - 1);
        System.out.println(foundIdx);

        int foundIdx2 = search(13, collection, 0, collection.length - 1);
        System.out.println(foundIdx2);
    }

    public static int search(int item, int[] collection, int leftIdx, int rightIdx) {

        // leftIdx rightIdx to keep track which range to work

        if (leftIdx > rightIdx) {
            // BASE CASE
            return -1; // not found
        }
        int mid = (leftIdx + rightIdx) / 2;
        if (item == collection[mid]) {
            // BASE CASE
            return mid; // found
        } else if (item < collection[mid]) {
            // RECURSIVE CASE
            return search(item, collection, leftIdx, mid - 1);
        } else {
            // RECURSIVE CASE
            return search(item, collection, mid + 1, rightIdx);
        }
    }
}
