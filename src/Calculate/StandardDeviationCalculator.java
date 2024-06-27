package calculations;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import java.util.ArrayList;
import java.util.List;

public class StandardDeviationCalculator implements Calculator<List<Double>, List<List<Double>>> {
    private List<Double> result = new ArrayList<>();

    @Override
    public void calculate(List<List<Double>> columns) {
        result.clear();
        StandardDeviation sd = new StandardDeviation();
        for (List<Double> column : columns) {
            double stddev = sd.evaluate(column.stream().mapToDouble(Double::doubleValue).toArray());
            result.add(stddev);
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
