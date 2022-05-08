import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class Main extends JFrame {
    private JPanel mainPanel;
    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JTextField tfAge;
    private JButton clearButton;
    private JButton submitButton;
    private JCheckBox maleCheckBox;
    private JCheckBox femaleCheckBox;
    private JRadioButton smallRadioButton;
    private JRadioButton mediumRadioButton;
    private JRadioButton largeRadioButton;
    private JSlider jslider;
    private JLabel JLabel;


    public Main() {
        setContentPane(mainPanel);
        setTitle("BMI calculator");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        jslider.setPreferredSize(new Dimension(400,200));
        jslider.setPaintTicks(true);
        jslider.setMinorTickSpacing(10);
        jslider.setPaintTrack(true);
        jslider.setMajorTickSpacing(25);
        jslider.setPaintLabels(true);
        jslider.setFont(new Font("MV Boli",Font.PLAIN,15));
        jslider.setOrientation(SwingConstants.VERTICAL);
        clearButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                tfLastName.setText(null);
                tfFirstName.setText(null);
                tfAge.setText(null);
            }
        });

        jslider.addComponentListener(new ComponentAdapter() {
        });

        jslider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            }
        });
    }

    public static void main(String[] args) {
        Main myFrame = new Main();

    }
}














