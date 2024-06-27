package Calculate;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.ArrayList;
import java.util.List;

public class ConfidenceIntervalCalculator implements Calculator<List<String>, List<List<Double>>> {
    private List<String> result = new ArrayList<>();
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
            String interval = "[" + (mean - marginOfError) + "; " + (mean + marginOfError) + "]";
            result.add(interval);
        }
    }

    @Override
    public List<String> getResult(){
        return result;
    }
    @Override
    public String getStringResult() {
        return result.toString();
    }
}
