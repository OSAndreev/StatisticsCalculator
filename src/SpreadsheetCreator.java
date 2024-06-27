import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class SpreadsheetCreator {
    private Workbook wb;
    private Sheet dataSheet;
    private int currentRow;

    public SpreadsheetCreator() {
        wb = new XSSFWorkbook();
        dataSheet = wb.createSheet("Analysis Results");
        currentRow = 0;
    }

    public void appendDataEntry(String label, Object data, CellType dataType) {
        Row row = dataSheet.createRow(currentRow++);
        createCellWithContent(row, 0, label);
        createDataCell(row, 1, data, dataType);
    }

    public void appendDataEntry(String label, Object data) {
        appendDataEntry(label, data, CellType.STRING); // Assume string as default data type
    }

    private void createCellWithContent(Row row, int cellIndex, String content) {
        Cell cell = row.createCell(cellIndex);
        cell.setCellValue(content);
    }

    private void createDataCell(Row row, int cellIndex, Object data, CellType dataType) {
        Cell cell = row.createCell(cellIndex);
        if (dataType == CellType.NUMERIC) {
            cell.setCellValue(Double.parseDouble(data.toString()));
        } else {
            cell.setCellValue(data.toString());
        }
    }

    public void addNumericalMatrix(List<List<Double>> matrix) {
        for (List<Double> rowData : matrix) {
            Row row = dataSheet.createRow(currentRow++);
            int cellIndex = 0;
            for (Double data : rowData) {
                Cell cell = row.createCell(cellIndex++);
                cell.setCellValue(data);
            }
        }
    }

    public void saveToFile(String pathToFile) {
        try (FileOutputStream outputStream = new FileOutputStream(pathToFile)) {
            wb.write(outputStream);
        } catch (IOException e) {
            System.err.println("Failed to save the file: " + e.getMessage());
        }
    }
}
