import java.io.FileWriter;
import java.io.IOException;
/**
 * This class takes a while to run. 'performance_report.txt' is the file created by this class which can be found in
 * the project directory. I recommend running SortingAlgorithmGraph class to best view the results from this class.
 */
public class Performance {

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
            FileWriter fileWriter = new FileWriter("performance_report.txt");

            for (SortingAlgorithm sortingAlgorithm : sortingAlgorithms) {
                fileWriter.write("Sorting algorithm - " + sortingAlgorithm.getClass().getSimpleName() + "\n");

                for (int size : arraySizes) {
                    Tester tester = new Tester(sortingAlgorithm);
                    double averageTime = tester.singleTest(size);

                    for (int i = 1; i < iterations; i++) {
                        averageTime += tester.singleTest(size);
                    }

                    averageTime /= iterations;
                    fileWriter.write("Sorted " + size + " elements in " + averageTime + " ms (avg)\n");
                }

                fileWriter.write("\n");
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
