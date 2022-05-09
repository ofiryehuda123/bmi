import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
    private JTextField tfIdealWeight;
    private JTextField tfWeight;
    private JLabel JLabel;

    public static void main(String[] args) {
        Main myFrame = new Main();
    }

    public Main() {
        setContentPane(mainPanel);
        setTitle("BMI calculator");
        setSize(900, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);



        //CLEAR
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfLastName.setText(null);
                tfFirstName.setText(null);
                tfAge.setText(null);
                tfWeight.setText(null);
                jslider.setValue(140);
                if (femaleCheckBox.isSelected()){
                    femaleCheckBox.setSelected(false);
                    maleCheckBox.setEnabled(true);
                } else {
                    maleCheckBox.setSelected(false);
                    femaleCheckBox.setEnabled(true);
                }
            }
        });



        maleCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (maleCheckBox.isSelected()){
                    femaleCheckBox.setEnabled(false);
                }
            }
        });

        femaleCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(femaleCheckBox.isSelected()) {
                    maleCheckBox.setEnabled(false);
                }
            }
        });


        jslider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });
    }















}
