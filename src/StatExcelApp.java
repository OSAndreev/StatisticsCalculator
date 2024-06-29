import Calculate.TableCalculator;
import ExcelTools.ReadExcel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class StatExcelApp extends JFrame {
    private JButton importButton, exportButton, exitButton;
    private JComboBox sheetNames = new JComboBox<>();
    private TableCalculator calculator = new TableCalculator();

    private File selectedFile;

    public StatExcelApp() {
        super("Statistical Excel Processor");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Импорт данных
        importButton = new JButton("Import Data");
        importButton.setPreferredSize(new Dimension(150, 50));
        importButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                importData();
            }
        });

        // Экспорт данных
        exportButton = new JButton("Export Data");
        exportButton.setPreferredSize(new Dimension(150, 50));
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                exportData();
            }
        });

        // Завершение работы программы
        exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(150, 50));
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        add(importButton);
        add(exportButton);
        add(exitButton);
        add(sheetNames);
    }

    private void importData() {
        JFileChooser fileChooser = null;
        try {
            fileChooser = new JFileChooser(new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile());
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "xlsx");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            List<String> names = null;
            try {
                names = ReadExcel.getSheetNames(selectedFile.getAbsolutePath());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            for (String name : names) sheetNames.addItem(name);
        }
    }

    private void exportData() {
        String name = (String) sheetNames.getSelectedItem();
        try {
            if (selectedFile == null) {
                throw new IllegalArgumentException("File is not choosed");
            }
            calculator.read(selectedFile.getAbsolutePath(), name);
            calculator.calculateAll();
            calculator.write();
            JOptionPane.showMessageDialog(this, "Data exported successfully", "Export Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }



        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save Excel file");


    }

}
