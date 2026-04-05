package coding.pattern.chap19;

public class Josephus {

    public static int josephus(int n, int k) {
        // base case: if there's only one person, the last person is person 0
        if(n == 1) {
            return 0;
        }
        // calculate the position of the last person remaining in the reduced problem
        // with n-1 people, we use modulo n to ensure the answer doesn't exceed n-1
        return (josephus(n-1, k)+k) % n;
    }

    public static int josephusOptimized(int n, int k ) {
        int res = 0;
        for (int i = 2; i <= n; i++) {
            // res[i] = (res[i-1] + k) % i
            res = (res + k) % i;
        }
        return res;
    }
}
