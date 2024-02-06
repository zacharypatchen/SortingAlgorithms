import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Problem 9 & 12 (extra credit attempt):
 * Disclosure: I received aid from ChatGPT on this part. It taught me how to use JFree's module and I had to of course
 * modify its code in order for it to run properly. I found that changing the y-axis to a logarithmic scale
 * displayed the trend lines better.
 */

public class SortingAlgorithmGraph {

    public static void main(String[] args) {
        SortingAlgorithmGraph graph = new SortingAlgorithmGraph();
        graph.createGraph("performance_report.txt", "Sorting Algorithm Runtimes - Random Data");
        graph.createGraph("k_sorted_performance_report.txt", "Sorting Algorithm Runtimes - K-Sorted Data");
    }


    public void createGraph(String filename, String chartTitle) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            XYSeries currentSeries = null;

            while ((line = br.readLine()) != null) {
                try {
                    if (line.startsWith("Sorting algorithm")) {
                        String algorithmName = line.split("-")[1].trim();
                        currentSeries = new XYSeries(algorithmName);
                    } else if (line.startsWith("Sorted")||line.startsWith("K-sorted")) {
                        String[] parts = line.split("\\s+");
                        int size;

                        if (parts.length > 6 && !parts[6].isEmpty()) {
                            size = Integer.parseInt(parts[1]);
                            String avgTimeString = parts[4];

                            if (!avgTimeString.isEmpty()) {
                                double avgTime = Double.parseDouble(avgTimeString);
                                currentSeries.add(size, avgTime);
                            }
                        }
                    }
                    else if (line.isEmpty()) {
                        if (currentSeries != null) {
                            dataset.addSeries(currentSeries);
                        }
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            }

            JFreeChart chart = ChartFactory.createXYLineChart(
                    chartTitle,
                    "Number of Elements (N)",
                    "Average Time (ms)",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );

            XYPlot plot = chart.getXYPlot();
            ValueAxis domainAxis = plot.getDomainAxis();
            domainAxis.setStandardTickUnits(createStandardTickUnits());

            LogarithmicAxis logAxis = new LogarithmicAxis("Average Time (ms)");
            plot.setRangeAxis(logAxis);

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(1000, 800));

            JFrame frame = new JFrame(chartTitle);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(chartPanel);
            frame.pack();
            frame.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private TickUnits createStandardTickUnits() {
        TickUnits units = new TickUnits();
        units.add(new NumberTickUnit(100));
        units.add(new NumberTickUnit(500));
        units.add(new NumberTickUnit(1000));
        units.add(new NumberTickUnit(2000));
        units.add(new NumberTickUnit(5000));
        units.add(new NumberTickUnit(10000));
        units.add(new NumberTickUnit(20000));
        units.add(new NumberTickUnit(75000));
        units.add(new NumberTickUnit(150000));
        return units;
    }
}
