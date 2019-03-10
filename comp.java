import java.util.Comparator;

class newComparator implements Comparator<treeNode> {
    public int compare(treeNode x, treeNode y) {
        return x.data - y.data;
    }
}

