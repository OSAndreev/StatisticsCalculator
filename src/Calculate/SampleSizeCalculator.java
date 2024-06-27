package Calculate;

import java.util.ArrayList;
import java.util.List;

public class SampleSizeCalculator implements Calculator<List<Integer>, List<List<Double>>> {
    private List<Integer> result = new ArrayList<>();

    @Override
    public void calculate(List<List<Double>> columns) {
        result.clear();
        for (List<Double> column : columns) {
            result.add(column.size());
        }
    }

    @Override
    public List<Integer> getResult() {
        return result;
    }

    @Override
    public String getStringResult() {
        return result.toString();
    }
}
