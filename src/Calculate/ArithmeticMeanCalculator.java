package Calculate;

import org.apache.commons.math3.stat.StatUtils;

import java.util.ArrayList;
import java.util.List;

public class ArithmeticMeanCalculator implements Calculator<List<Double>, List<List<Double>>> {
    private List<Double> result = new ArrayList<>();

    @Override
    public void calculate(List<List<Double>> columns) {
        result = new ArrayList<>();
        for (List<Double> column : columns) {
            double arithmeticMean = StatUtils.mean(column.stream().mapToDouble(Double::doubleValue).toArray());
            result.add(arithmeticMean);
        }
    }

    @Override
    public List<Double> getResult() {
        return result;
    }

    @Override
    public String getStringResult(){
        return result.toString();
    };
}

