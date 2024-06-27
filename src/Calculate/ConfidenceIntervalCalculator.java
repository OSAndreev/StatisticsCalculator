package calculations;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.ArrayList;
import java.util.List;

public class ConfidenceIntervalCalculator implements Calculator<List<double[]>, List<List<Double>>> {
    private List<double[]> result = new ArrayList<>();
    private final double confidenceLevel = 1.96; // 95% confidence level

    @Override
    public void calculate(List<List<Double>> columns) {
        result.clear();
        for (List<Double> column : columns) {
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for (double num : column) {
                stats.addValue(num);
            }
            double mean = stats.getMean();
            double stdDev = stats.getStandardDeviation();
            double marginOfError = confidenceLevel * stdDev / Math.sqrt(column.size());
            result.add(new double[]{mean - marginOfError, mean + marginOfError});
        }
    }

    @Override
    public List<double[]> getResult() {
        return result;
    }

    @Override
    public String getStringResult() {
        StringBuilder sb = new StringBuilder();
        for (double[] interval : result) {
            sb.append("[").append(interval[0]).append(", ").append(interval[1]).append("], ");
        }
        return sb.length() > 2 ? sb.substring(0, sb.length() - 2) : sb.toString();
    }
}
