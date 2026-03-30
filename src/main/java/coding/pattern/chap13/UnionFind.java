package coding.pattern.chap13;

/**
 * @author liuxiaodong02
 */
public class UnionFind {

    private int[] parent;
    private int[] size;

    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public boolean union(int x, int y) {
        int repX = find(x);
        int repY = find(y);
        if (repX != repY) {
            // if 'repX' represents a larger community, connect
            // 'repY''s community to it
            if (size[repX] > size[repY]) {
                parent[repY]  = repX;
                size[repX] += size[repY];
            } else {
                // otherwise, connect 'repX' community to `repY`
                parent[repX] = repY;
                size[repY] += size[repX];
            }
            return true;
        }
        // return false if x, y belong to the same group
        return false;
    }

    public int find(int x) {
        if(x < 0 || x >= parent.length) {
            throw new IndexOutOfBoundsException("x is out of bound");
        }
        // find with path compression
        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }

    public int size(int x) {
        return size[find(x)];
    }
}
