import java.util.Random;

abstract class QuickSort {
    protected static Random rand = new Random();
    protected long comparisons;
    protected int[] array;

    public QuickSort(int[] array) {
        comparisons = 0;
        this.array = array;
    }

    protected void swap(int x, int y) {
        int tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
    }

    abstract void sort();

    public long getComparisons() {
        return comparisons;
    }
}
