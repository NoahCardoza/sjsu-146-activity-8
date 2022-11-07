
public class QuickSortRandomPivot extends QuickSortEndPivot {
        public QuickSortRandomPivot(int[] array) {
            super(array);
        }
        @Override
        protected int partition(int start, int end) {
            swap(rand.nextInt(end - start) + start, end);
            return super.partition(start, end);
        }
}
