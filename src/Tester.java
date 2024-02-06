import java.util.Random;


public class Tester {
    private final SortingAlgorithm sortingAlgorithm;

    public Tester(SortingAlgorithm sa) {
        this.sortingAlgorithm = sa;
    }

    public double singleTest(int size) {
        int[] array = generateRandomArray(size);

        long startTime = System.nanoTime();
        sortingAlgorithm.sorty(array);
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1e6; // Convert to milliseconds
    }

    public void test(int iterations, int size, String name) {
        double totalTime = 0;

        for (int i = 0; i < iterations; i++) {
            double elapsedTime = singleTest(size);
            System.out.println(name+ " Test " + (i + 1) + ": " + elapsedTime + " milliseconds");
            totalTime += elapsedTime;
        }

        double averageTime = totalTime / iterations;
        System.out.println(name + "'s Average Time for Size " + size + ": " + averageTime + " milliseconds\n");
    }

    private int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }

        return array;
    }

    public static void main(String[] args) {
        int iterations = 5;
        int size = 10000;

        Tester bubbleSortTester = new Tester(new BubbleSort());
        bubbleSortTester.test(iterations, size, "Bubble Sort");

        Tester insertionSortTester = new Tester(new InsertionSort());
        insertionSortTester.test(iterations, size, "Insertion Sort");

        Tester selectionSortTester = new Tester(new SelectionSort());
        selectionSortTester.test(iterations, size, "Selection Sort");

        Tester shellSortTester = new Tester(new ShellSort());
        shellSortTester.test(iterations, size, "Shell Sort");

        Tester quickSortTester = new Tester(new QuickSort());
        quickSortTester.test(iterations, size, "Quick Sort");

        Tester mergeSortTester = new Tester(new MergeSort());
        mergeSortTester.test(iterations, size, "Merge Sort");
    }
}
