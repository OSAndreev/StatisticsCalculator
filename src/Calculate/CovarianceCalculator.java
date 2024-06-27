package calculations;

import org.apache.commons.math3.stat.correlation.Covariance;
import java.util.ArrayList;
import java.util.List;

public class CovarianceCalculator implements Calculator<List<Double[]>, List<List<Double>>> {
    private List<Double[]> result = new ArrayList<>();

    @Override
    public void calculate(List<List<Double>> columns) {
        result.clear();
        Covariance covariance = new Covariance();
        for (int i = 0; i < columns.size(); i++) {
            for (int j = i + 1; j < columns.size(); j++) {
                double[] array1 = columns.get(i).stream().mapToDouble(Double::doubleValue).toArray();
                double[] array2 = columns.get(j).stream().mapToDouble(Double::doubleValue).toArray();
                double cov = covariance.covariance(array1, array2);
                result.add(new Double[]{(double) i, (double) j, cov});
            }
        }
    }

    @Override
    public List<Double[]> getResult() {
        return result;
    }

    @Override
    public String getStringResult() {
        return result.toString();
    }
}
