package recursion.chap3;

public class SumListIterative {


    public static void main(String[] args) {
        
    }

    public static int sum(int[] numbers) {
        int sum = 0;
        for (int element : numbers) {
            sum += element;
        }
        return sum;
    }
}
