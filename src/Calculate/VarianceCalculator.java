package calculations;

import org.apache.commons.math3.stat.StatUtils;

import java.util.ArrayList;
import java.util.List;

public class VarianceCalculator implements Calculator<List<Double>, List<List<Double>>> {
    private List<Double> result = new ArrayList<>();

    @Override
    public void calculate(List<List<Double>> columns) {
        result.clear();
        for (List<Double> column : columns) {
            double variance = StatUtils.variance(column.stream().mapToDouble(Double::doubleValue).toArray());
            result.add(variance);
        }
    }

    @Override
    public List<Double> getResult() {
        return result;
    }

    @Override
    public String getStringResult() {
        return result.toString();
    }
}
