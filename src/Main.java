import javax.swing.*;
public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                StatExcelApp app = new StatExcelApp();
                app.setVisible(true);
            }
        });
    }

}