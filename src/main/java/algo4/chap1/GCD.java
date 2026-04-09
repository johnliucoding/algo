package algo4.chap1;

/**
 * @author liuxiaodong02
 */
public class GCD {

    public int gcd(int p, int q) {
        if(q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }
}
