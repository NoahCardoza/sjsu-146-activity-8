
public class QuickSortFisherPivot extends QuickSortEndPivot {
        public QuickSortFisherPivot(int[] array) {
            super(array);
        }

        private void fisherYates() {
            for (int i = this.array.length - 1; i > 0; i--) {
                swap(i, rand.nextInt(i));
            }
        }
        
        @Override
        public void sort () {
            fisherYates();
            super.sort();
        }
}
