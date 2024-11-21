package org.example.lr4model;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;


import java.util.Random;

public class HelloController {
    private final int seed = 21 * 1000;
    private final int count = 10000;
    private final double mean = 21;  // Математичне сподівання
    private final double stdDev = 21; // Середньоквадратичне відхилення

    @FXML
    private LineChart<Number, Number> lineChart;

    private Random random = new Random(seed);

    @FXML
    private void initialize() {
        double[] values = generateNormalDistributedValues(count);
        displayNumbers(values);
        plotGraph(values);
        evaluateGenerator(values);
    }

    private double[] generateNormalDistributedValues(int count) {
        double[] values = new double[count];
        for (int i = 0; i < count; i++) {
            double u1 = random.nextDouble();
            double u2 = random.nextDouble();
            double z0 = Math.sqrt(-2 * Math.log(u1)) * Math.cos(2 * Math.PI * u2);
            values[i] = mean + stdDev * z0;
        }
        return values;
    }

    private void displayNumbers(double[] values) {
        System.out.println("Згенеровані псевдовипадкові числа:");
        for (int i = 0; i < values.length; i++) {
            System.out.printf("%5d: %8.2f\n", i + 1, values[i]);
            if (i % 10 == 9) {
                System.out.println();
            }
        }
    }

    private void plotGraph(double[] values) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Псевдовипадкові числа");
        for (int i = 1; i < values.length; i++) {
            if ((i + 1) % 100 == 1) {
                XYChart.Data<Number, Number> data = new XYChart.Data<>(i + 1, values[i]);
                series.getData().add(data);
            }
        }
        lineChart.getData().clear();
        lineChart.getData().add(series);
    }
    private void evaluateGenerator(double[] values) {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        double sum = 0;
        for (double value : values) {
            if (value < min) min = value;
            if (value > max) max = value;
            sum += value;
        }
        double average = sum / values.length;
        System.out.println("Якість генератора:");
        System.out.printf("Мінімальне значення: %.2f\n", min);
        System.out.printf("Максимальне значення: %.2f\n", max);
        System.out.printf("Середнє значення: %.2f\n", average);
    }
}