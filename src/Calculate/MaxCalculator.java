package Calculate;

import org.apache.commons.math3.stat.StatUtils;

import java.util.ArrayList;
import java.util.List;

public class MinMaxCalculator implements Calculator<List<double[]>, List<List<Double>>> {
    private List<double[]> result = new ArrayList<>();

    @Override
    public void calculate(List<List<Double>> columns) {
        result.clear();
        for (List<Double> column : columns) {
            double min = StatUtils.min(column.stream().mapToDouble(Double::doubleValue).toArray());
            double max = StatUtils.max(column.stream().mapToDouble(Double::doubleValue).toArray());
            result.add(new double[]{min, max});
        }
    }

    @Override
    public List<double[]> getResult() {
        return result;
    }

    @Override
    public String getStringResult() {
        StringBuilder sb = new StringBuilder();
        for (double[] minMax : result) {
            sb.append("[").append(minMax[0]).append(", ").append(minMax[1]).append("], ");
        }
        return sb.length() > 2 ? sb.substring(0, sb.length() - 2) : sb.toString();
    }
}
