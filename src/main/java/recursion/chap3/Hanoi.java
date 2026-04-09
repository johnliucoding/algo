package recursion.chap3;


import java.util.Arrays;


/**
 * Hanoi
 */
public class Hanoi {

    static int total_disks = 6;
    static int[] a = {6,5,4,3,2,1};
    static int[] b = new int[6];
    static int[] c = new int[6];

    public static void main(String[] args) {
        printTowers();
        System.out.println("starting --- ");
        solve(total_disks, a, c, b);
        System.out.println("ending --- ");
        printTowers();
    }

    public static void moveOne(int[] from, int[] to) {
        int frmIdx = 0;
        while(frmIdx < from.length && from[frmIdx] != 0) {
            frmIdx++;
        }
        

        int toIdx = 0;
        while(toIdx < to.length && to[toIdx] != 0) {
            toIdx++;
        }

        int temp = from[frmIdx-1];
        from[frmIdx-1] = 0;
        to[toIdx] = temp;

    }

    public static void solve(int numOfDisk, int[] start, int[] end, int[] temp ) {
        if(numOfDisk == 1) {
            moveOne(start, end);
            printTowers();
            return;
        } else {
            solve(numOfDisk-1, start, temp, end);
            moveOne(start, end);
            printTowers();
            solve(numOfDisk-1, temp, end, start);
            return;
        }


        
    }

    public static void printTowers() {
        System.out.println("tower A: " + printTower(a));

        System.out.println("tower B: " + printTower(b));

        System.out.println("tower C: " + printTower(c));
    }

    public static String printTower(int[] tower) {
        int pos = 0;
        while(pos < tower.length && tower[pos] != 0) {
            pos++;
        }
        int[] newArr = Arrays.copyOfRange(tower, 0, pos);
        return Arrays.toString(newArr);
    }
}