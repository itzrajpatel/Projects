import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DigitalClock extends JFrame {

    private JLabel timeLabel;

    public DigitalClock() {
        setTitle("Digital Clock");
        setSize(300, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.BOLD, 48));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(timeLabel, BorderLayout.CENTER);

        Timer timer = new Timer(1000, e -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            timeLabel.setText(dateFormat.format(new Date()));
        });
        timer.start();
    }

    public static void main(String[] args) {
        new DigitalClock().setVisible(true);
    }
}