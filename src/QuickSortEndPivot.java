public class QuickSortEndPivot extends QuickSort {
    public QuickSortEndPivot(int[] array) {
        super(array);
    }

    public void sort() {
        sort(0, array.length - 1);
    }

    private void sort(int start, int end) {
        comparisons++;
        if (start < end) {
            int part = partition(start, end);
            sort(start, part - 1);
            sort(part + 1, end);
        }
    }

    protected int partition(int start, int end) {
        int pivot = array[end];
        int i = start - 1;

        for (int j = start; j < end; j++) {
            comparisons++;
            if (array[j] <= pivot) {
                i++;
                swap(i, j);
            }
        }

        swap(++i, end);

        return i;
    }
}