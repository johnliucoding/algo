package recursion.chap5;

public class BinarySearchIter {
    public static void main(String[] args) {
        int[] collection = { 2, 3, 4, 6, 8, 9 };

        int foundIdx = search(3, collection);
        System.out.println(foundIdx);

        int foundIdx2 = search(13, collection);
        System.out.println(foundIdx2);
    }

    public static int search(int item, int[] collection) {
        var leftIdx = 0;
        var rightIdx = collection.length - 1;
        var mid = (leftIdx + rightIdx) / 2;

        while (leftIdx <= rightIdx) {
            if (item == collection[mid]) {
                return mid;
            } else if (item < collection[mid]) {
                rightIdx = mid - 1;
                mid = (leftIdx + rightIdx) / 2;
            } else {
                leftIdx = mid + 1;
                mid = (leftIdx + rightIdx) / 2;
            }
        }
        return -1;

    }
}
