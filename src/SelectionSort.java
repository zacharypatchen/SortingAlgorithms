// SelectionSort.java
public class SelectionSort implements SortingAlgorithm {
    @Override
    public int[] sorty(int[] input) {
        int n = input.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (input[j] < input[minIndex]) {
                    minIndex = j;
                }
            }

            // swap elements
            int temp = input[minIndex];
            input[minIndex] = input[i];
            input[i] = temp;
        }

        return input;
    }
}
