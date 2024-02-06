import java.util.Arrays;

/**
 * Problem 11:
 */
public class TesterKSorted {
    private final SortingAlgorithm sortingAlgorithm;

    public TesterKSorted(SortingAlgorithm sa) {
        this.sortingAlgorithm = sa;
    }

    public double singleTest(int size) {
        int[] array = generateKSortedArray(size);

        long startTime = System.nanoTime();
        sortingAlgorithm.sorty(array);
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1e6; // Convert to milliseconds
    }

    public void test(int iterations, int size, String name) {
        double totalTime = 0;

        for (int i = 0; i < iterations; i++) {
            double elapsedTime = singleTest(size);
            System.out.println(name + " Test " + (i + 1) + ": " + elapsedTime + " milliseconds");
            totalTime += elapsedTime;
        }

        double averageTime = totalTime / iterations;
        System.out.println(name + "'s Average Time for Size " + size + ": " + averageTime + " milliseconds\n");
    }

    private int[] generateKSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i + (int) (Math.random() * 10);
        }
        Arrays.sort(array);
        return array;
    }


    public static void main(String[] args) {
        int iterations = 5;
        int size = 10000;

        TesterKSorted bubbleSortTester = new TesterKSorted(new BubbleSort());
        bubbleSortTester.test(iterations, size, "Bubble Sort");

        TesterKSorted insertionSortTester = new TesterKSorted(new InsertionSort());
        insertionSortTester.test(iterations, size, "Insertion Sort");

        TesterKSorted selectionSortTester = new TesterKSorted(new SelectionSort());
        selectionSortTester.test(iterations, size, "Selection Sort");

        TesterKSorted shellSortTester = new TesterKSorted(new ShellSort());
        shellSortTester.test(iterations, size, "Shell Sort");

        TesterKSorted quickSortTester = new TesterKSorted(new QuickSort());
        quickSortTester.test(iterations, size, "Quick Sort");

        TesterKSorted mergeSortTester = new TesterKSorted(new MergeSort());
        mergeSortTester.test(iterations, size, "Merge Sort");
    }
}
