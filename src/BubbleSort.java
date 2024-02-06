// BubbleSort.java
public class BubbleSort implements SortingAlgorithm {
    @Override
    public int[] sorty(int[] input) {
        int n = input.length;
        boolean swapped;

        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (input[i - 1] > input[i]) {
                    // swap elements
                    int temp = input[i - 1];
                    input[i - 1] = input[i];
                    input[i] = temp;
                    swapped = true;
                }
            }
            n--;
        } while (swapped);

        return input;
    }
}
