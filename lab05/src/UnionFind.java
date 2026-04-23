public class UnionFind {
    private int[] parent;
    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be positive, got: " + N);
        }
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            this.parent[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        validate(v);
        return -parent[find(v)];
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        validate(v);
        return parent[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v2) == find(v1);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        validate(v);
        if (this.parent[v] < 0){
            return v;
        } else {
            parent[v] = find(parent[v]);
            return parent[v];
        }
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int size1 = this.sizeOf(v1);
        int size2 = this.sizeOf(v2);
        if (size1 <= size2) {
            int temp = v1;
            v1 = v2;
            v2 = temp;
        }
        int p1 = find(v1);
        int p2 = find(v2);
        parent[p2] = p1;
        parent[p1] = -(size1 + size2);
    }
    private void validate(int v) {
        if (v < 0 || v >= parent.length) {
            throw new IllegalArgumentException("Invalid vertex: " + v);
        }
    }

}
