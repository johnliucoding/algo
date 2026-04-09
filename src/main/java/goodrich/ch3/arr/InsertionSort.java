package goodrich.ch3.arr;

public class InsertionSort {

  public static void sort(char[] data) {
    for(int k = 1; k < data.length; k++) {
      char cur = data[k];
      int j = k;
      while (j > 0 && data[j - 1] > cur) {
        data[j] = data[j-1];
        j--;
      }
      data[j] = cur;
    }
  }
}
