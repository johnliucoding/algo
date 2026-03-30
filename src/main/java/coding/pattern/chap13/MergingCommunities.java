package coding.pattern.chap13;

/**
 * @author liuxiaodong02
 */
public class MergingCommunities {
    private UnionFind uf;
    public MergingCommunities(int n) {
        this.uf = new UnionFind(n);
    }

    public void connect(int x, int y) {
        uf.union(x, y);
    }

    public int getCommunitySize(int x) {
        return uf.size(x);
    }

}
