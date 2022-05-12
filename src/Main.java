import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Main extends JFrame {
    private JPanel mainPanel;
    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JTextField tfAge;
    private JButton clearButton;
    private JButton submitButton;
    private JRadioButton smallRadioButton;
    private JRadioButton mediumRadioButton;
    private JRadioButton largeRadioButton;
    private JSlider jslider;
    private JTextField tfIdealWeight;
    private JTextField tfWeight;
    private JLabel bmiLabel;
    private JLabel idealWeight;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JLabel LastName;
    private JLabel JLabel;
    private double slimness;
    private ButtonGroup buttonGroupGender = new ButtonGroup();
    private ButtonGroup buttonGroup = new ButtonGroup();

    public static void main(String[] args) {
        try {
            Main myFrame = new Main();
            myFrame.setResizable(false);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Main() {
        setContentPane(mainPanel);
        setTitle("BMI calculator");
        setSize(1000  , 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        buttonGroupGender.add(maleRadioButton);
        buttonGroupGender.add(femaleRadioButton);
        buttonGroup.add(smallRadioButton);
        buttonGroup.add(mediumRadioButton);
        buttonGroup.add(largeRadioButton);

        //CLEAR
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfLastName.setText(null);
                tfFirstName.setText(null);
                tfAge.setText(null);
                tfWeight.setText(null);
                jslider.setValue(140);
                buttonGroupGender.clearSelection();
                buttonGroup.clearSelection();
            }
        });

        jslider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println(jslider.getValue());
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(3);
                double finalBmi= Double.parseDouble(tfWeight.getText() ) * 100 * 100 / (jslider.getValue() * jslider.getValue());
                bmiLabel.setText(df.format(finalBmi) +" "+ setBmiStatusLabel(finalBmi));
                double finalIdealWeight = calculateIdealWeight(jslider.getValue(),Double.parseDouble(tfAge.getText()), slimness);
                idealWeight.setText(String.valueOf(finalIdealWeight));
                    ;
            }
        });

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object source = e.getSource();
                if (source==smallRadioButton) {
                    slimness = 0.9;
                }else if (source==mediumRadioButton){
                    slimness=1;
                }else {
                    slimness = 1.1;
                }
                }
        };

        smallRadioButton.addActionListener(listener);
        largeRadioButton.addActionListener(listener);
        mediumRadioButton.addActionListener(listener);
        femaleRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private String setBmiStatusLabel(double userBmi){
        String userWeightStatus;
        if (userBmi<15){
            userWeightStatus="Anorexic";
        } else if (userBmi>=15 && userBmi<18.5){
            userWeightStatus="Underweight ";
        }else if(userBmi>=18.5 && userBmi<24.9){
            userWeightStatus="Normal";
        }else if (userBmi>=24.9 && userBmi<29.9){
            userWeightStatus="Overweight";
        }else if(userBmi>=30 && userBmi<35){
            userWeightStatus="Obese";
        }else {
            userWeightStatus="Extreme Obese";
        }

        return userWeightStatus;
    }

    private double calculateIdealWeight(double userHeight,double userAge,double slimness){
        return (userHeight-100+(userAge/10))*0.9*slimness;

    }

}