package Calculate;

import org.apache.commons.math3.stat.correlation.Covariance;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class CovarianceCalculator implements Calculator<List<List<Double>>, List<List<Double>>> {
    private List<List<Double>> result;

    @Override
    public void calculate(List<List<Double>> columns) {
        result = new ArrayList<>();
        Covariance covariance = new Covariance();
        for (List<Double> innerList1 : columns) {
            List<Double> row = new ArrayList<>();
            for (List<Double> innerList2 : columns) {
                row.add(covariance.covariance(innerList1.stream().mapToDouble(Double::doubleValue).toArray(), innerList2.stream().mapToDouble(Double::doubleValue).toArray()));
            }
            result.add(row);
        }
    }

    @Override
    public List<List<Double>> getResult() {
        return result;
    }

    @Override
    public String getStringResult() {
        return result.toString();
    }
}
