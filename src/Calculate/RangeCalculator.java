package Calculate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RangeCalculator implements Calculator<List<Double>, List<List<Double>>> {
    private List<Double> result = new ArrayList<>();

    @Override
    public void calculate(List<List<Double>> columns) {
        result.clear();
        for (List<Double> column : columns) {
            double min = Collections.min(column);
            double max = Collections.max(column);
            double range = max - min;
            result.add(range);
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
