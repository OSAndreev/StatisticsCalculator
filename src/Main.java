import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        // Используем метод invokeLater для того, чтобы убедиться, что GUI-интерфейс
        // создается и обрабатывается в потоке обработки событий (EDT).
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Создаем экземпляр главного окна приложения
                StatExcelApp app = new StatExcelApp();
                // Устанавливаем видимость окна в true, чтобы оно отобразилось
                app.setVisible(true);
            }
        });
    }

}