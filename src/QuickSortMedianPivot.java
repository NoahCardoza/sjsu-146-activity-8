
public class QuickSortMedianPivot extends QuickSortEndPivot {
        public QuickSortMedianPivot(int[] array) {
            super(array);
        }

        private int randomizedSelect (int start, int end, int index) {
            comparisons++;
            if (start >= end) {
                return  start;
            }

            int pivot = rand.nextInt(end - start) + start;
            int k = pivot - start + 1;

            comparisons++;
            if (index == k) {
                return pivot;
            } else if (index < k) {
                comparisons++; // if index < k -> true
                return randomizedSelect(start, pivot - 1, index);
            } else {
                comparisons++; // if index < k -> false
                return randomizedSelect(pivot + 1, end, index - k);
            }
        }

        @Override
        protected int partition(int start, int end) {
            int pivotIndex = randomizedSelect(start, end, (start + end) / 2);
            swap(pivotIndex, end);
            return super.partition(start, end);
        }
}
