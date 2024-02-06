// InsertionSort.java
public class InsertionSort implements SortingAlgorithm {
    @Override
    public int[] sorty(int[] input) {
        int n = input.length;

        for (int i = 1; i < n; i++) {
            int key = input[i];
            int j = i - 1;

            while (j >= 0 && input[j] > key) {
                input[j + 1] = input[j];
                j--;
            }
            input[j + 1] = key;
        }

        return input;
    }
}
