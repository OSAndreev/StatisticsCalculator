package Calculate;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.StatUtils;
import java.util.ArrayList;
import java.util.List;

public class CoefficientOfVariationCalculator implements Calculator<List<Double>, List<List<Double>>> {
    private List<Double> result = new ArrayList<>();

    @Override
    public void calculate(List<List<Double>> columns) {
        result.clear();
        for (List<Double> column : columns) {
            double sd = new StandardDeviation().evaluate(column.stream().mapToDouble(Double::doubleValue).toArray());
            double mean = StatUtils.mean(column.stream().mapToDouble(Double::doubleValue).toArray());
            double coefficientOfVariation = (mean == 0) ? 0 : (sd / mean) * 100;
            result.add(coefficientOfVariation);
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
