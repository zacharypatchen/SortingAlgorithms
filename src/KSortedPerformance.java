import java.io.FileWriter;
import java.io.IOException;
/**
 * Problem 11:
 */
public class KSortedPerformance {

    public static void main(String[] args) {
        int[] arraySizes = {100, 500, 1000, 2000, 5000, 10000, 20000, 75000, 150000};
        int iterations = 20;

        SortingAlgorithm[] sortingAlgorithms = {
                new BubbleSort(),
                new InsertionSort(),
                new SelectionSort(),
                new ShellSort(),
                new QuickSort(),
                new MergeSort()
        };

        try {
            FileWriter fileWriter = new FileWriter("k_sorted_performance_report.txt");

            for (SortingAlgorithm sortingAlgorithm : sortingAlgorithms) {
                fileWriter.write("Sorting algorithm - " + sortingAlgorithm.getClass().getSimpleName() + "\n");

                for (int size : arraySizes) {
                    TesterKSorted tester = new TesterKSorted(sortingAlgorithm);
                    double averageTime = tester.singleTest(size);

                    for (int i = 1; i < iterations; i++) {
                        averageTime += tester.singleTest(size);
                    }

                    averageTime /= iterations;
                    fileWriter.write("K-sorted " + size + " elements in " + averageTime + " ms (avg)\n");
                }

                fileWriter.write("\n");
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
