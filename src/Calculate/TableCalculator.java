package Calculate;

import ExcelTools.ReadExcel;
import ExcelTools.WriteExcel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableCalculator {
    private Map<String, Calculator> statObjects = new HashMap<>();
    private Map<String, List<?>> allResults = new HashMap<>();
    private List<List<Double>> columns;
    private  List<String>labels;

    public TableCalculator() {
        statObjects.put("Стандартное отклонение", new StandardDeviationCalculator());
        statObjects.put("Доверительный интервал для мат ожидания", new ConfidenceIntervalCalculator());
        statObjects.put("Количество элементов", new SampleSizeCalculator());
        statObjects.put("Среднее значение", new ArithmeticMeanCalculator());
        statObjects.put("Максимальное значение", new MaxCalculator());
        statObjects.put("Минимальное значение", new MinCalculator());
        statObjects.put("Среднее геометрическое", new GeometricMeanCalculator());
        statObjects.put("Коэффициент вариации", new CoefficientOfVariationCalculator());
        statObjects.put("Дисперсия", new VarianceCalculator());
        statObjects.put("Размах", new RangeCalculator());
        statObjects.put("Ковариация", new CovarianceCalculator());
    }

    public void read(String file, String name) throws IOException {
        ReadExcel excelReader = new ReadExcel();
        Map<String, List<Double>> data  = excelReader.readFromExcel(file, name);
        labels = new ArrayList<>(data.keySet());
        columns = new ArrayList<>(data.values());

    }


    public void calculateAll() {
        for (Map.Entry<String, Calculator> entry : statObjects.entrySet()) {
            String name = entry.getKey();
            Calculator<?, List<List<Double>>> calc = entry.getValue();
            try {
                calc.calculate(columns);
                allResults.put(name, (List<?>) calc.getResult());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public Map<String, List<?>> getAllResults() {
        return allResults;
    }

    public void write() throws IOException{
        WriteExcel.write(allResults, labels, "OutputStatistics");



    }}
