import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Main extends JFrame {
    private JPanel mainPanel;
    private JTextField textFirstName;
    private JTextField textLastName;
    private JTextField textAge;
    private JButton clearButton;
    private JButton submitButton;
    private JRadioButton smallRadioButton;
    private JRadioButton mediumRadioButton;
    private JRadioButton largeRadioButton;
    private JSlider jsliderHeight;
    private JTextField tfIdealWeight;
    private JTextField textWeight;
    private JLabel labelBmiResult;
    private JLabel IdealWeightResult;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JLabel LastName;
    private JLabel heightLabel;
    private JLabel firstName;
    private JLabel age;
    private JLabel gender;
    private JLabel bmiLabel;
    private JLabel idealWeightLabel;
    private JLabel titleLabel1;
    private JLabel titleLabel2;
    private JLabel bodyLabel;
    private JLabel weightLabel;
    private JLabel JLabel;
    private double slimness;
    private ButtonGroup buttonGroupGender = new ButtonGroup();
    private ButtonGroup buttonGroup = new ButtonGroup();

    //final
    public static final int ANOREXIC=15;
    public static final double UNDER_WEIGHT=18.5;
    public static final double NORMAL=24.9;
    public static final double OVER_WEIGHT=29.9;
    public static final int OBESE=30;
    public static final int EXTRA_OBESE=35;
    public static final double SMALL_SLIMNESS=0.9;
    public static final int MEDIUM_SLIMNESS=1;
    public static final double LARGE_SLIMNESS=1.1;

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
        setSize(1000  , 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        buttonGroupGender.add(maleRadioButton);
        buttonGroupGender.add(femaleRadioButton);
        buttonGroup.add(smallRadioButton);
        buttonGroup.add(mediumRadioButton);
        buttonGroup.add(largeRadioButton);

        //clear
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textLastName.setText(null);
                textFirstName.setText(null);
                textAge.setText(null);
                textWeight.setText(null);
                jsliderHeight.setValue(140);
                buttonGroupGender.clearSelection();
                buttonGroup.clearSelection();
            }
        });

        jsliderHeight.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                heightLabel.setText("your height : " + jsliderHeight.getValue() );
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(3);
                double finalBmi= Double.parseDouble(textWeight.getText() ) * 100 * 100 / (jsliderHeight.getValue() * jsliderHeight.getValue());
                labelBmiResult.setText(df.format(finalBmi) +" "+ setBmiStatusLabel(finalBmi));
                double finalIdealWeight = calculateIdealWeight(jsliderHeight.getValue(),Double.parseDouble(textAge.getText()), slimness);
                IdealWeightResult.setText(String.valueOf(df.format(finalIdealWeight) ));
            }
        });

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source==smallRadioButton) {
                    slimness = SMALL_SLIMNESS;
                }else if (source==mediumRadioButton){
                    slimness=MEDIUM_SLIMNESS;
                }else {
                    slimness = LARGE_SLIMNESS;
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
        if (userBmi<ANOREXIC){
            userWeightStatus="Anorexic";
        } else if (userBmi>=ANOREXIC && userBmi<UNDER_WEIGHT){
            userWeightStatus="Underweight ";
        }else if(userBmi>=UNDER_WEIGHT && userBmi<NORMAL){
            userWeightStatus="Normal";
        }else if (userBmi>=NORMAL && userBmi<OVER_WEIGHT){
            userWeightStatus="Overweight";
        }else if(userBmi>=OBESE && userBmi<EXTRA_OBESE){
            userWeightStatus="Obese";
        }else {
            userWeightStatus="Extreme Obese";
        }
        return userWeightStatus;
    }

    private double calculateIdealWeight(double userHeight,double userAge,double slimness){
        return (userHeight-100+(userAge/10))*SMALL_SLIMNESS*slimness;
    }

}