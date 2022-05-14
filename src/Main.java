import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private JLabel warningLabel1;
    private JLabel warningLabel2;
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
    public static final String INVALID_CHARACTERS="[!@#$%&*()_+=|<>?{}\\[\\]~-]";
    public static final int CONVERSION_CENTIMETER_TO_METER=100;

    public static void main(String[] args) {
        try {
            Main myFrame = new Main();
            myFrame.setResizable(false);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Main() {
        setContentPane(mainPanel);
        setTitle("BMI calculator");
        setSize(1000  , 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        buttonGroupGender.add(maleRadioButton);
        buttonGroupGender.add(femaleRadioButton);
        buttonGroup.add(smallRadioButton);
        buttonGroup.add(mediumRadioButton);
        buttonGroup.add(largeRadioButton);

        //clear listener
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
                submitButton.setEnabled(true);
                clearButton.setEnabled(false);
                labelBmiResult.setText(null);
                IdealWeightResult.setText(null);
                clearButton.setEnabled(false);
                submitButton.setEnabled(true);
                warningLabel1.setVisible(false);
                warningLabel2.setVisible(false);
            }
        });

        jsliderHeight.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                heightLabel.setText("your height : " + jsliderHeight.getValue() );
            }
        });

        //submit listener
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(3);
                double finalIdealWeight = calculateIdealWeight(jsliderHeight.getValue(),textAge, slimness);
                double finalBmi= calculateBmiResult(jsliderHeight,textWeight);
                if (checkIfInputValid(finalIdealWeight,finalBmi,isNameEmpty(textFirstName,textLastName),isGenderButtonPressed(buttonGroupGender)) && slimness!=0){
                    labelBmiResult.setText(df.format(finalBmi) +" "+ setBmiStatusLabel(finalBmi));
                    IdealWeightResult.setText(String.valueOf(df.format(finalIdealWeight) ));
                } else {
                    warningLabel1.setVisible(true);
                    warningLabel2.setVisible(true);
                    warningLabel1.setText("your input isn't valid,");
                    warningLabel2.setText("please press clear to reset.");
                    warningLabel1.setForeground(Color.RED);
                    warningLabel2.setForeground(Color.RED);

                }
                submitButton.setEnabled(false);
                clearButton.setEnabled(true);

            }
        });

        // add listener for body
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

    private double calculateIdealWeight(double userHeight,JTextField userAge,double slimness){
        double idealWeight=0;
        if (!isValidInput(userAge)){
            idealWeight= (userHeight-100+(Double.parseDouble(userAge.getText())/10))*0.9*slimness;
        }
        return idealWeight;
    }

    //check if age or weight is empty or contain characters
    private boolean isValidInput(JTextField textField){
        boolean isValidInput=false;
        //check for special characters like *, !, etc
        Pattern special = Pattern.compile (INVALID_CHARACTERS);
        Matcher hasSpecial = special.matcher(textField.getText());
        if (textField.getText().isEmpty() || hasSpecial.find()){
            isValidInput=true;
        }
        for (char character:textField.getText().toCharArray()) {
            if (Character.isAlphabetic(character) ){
                isValidInput=true;
                break;
            }
        }
        return isValidInput;
    }

    //check if ideal or bmi is 0 in this case the user enter something wrong
    private boolean checkIfInputValid(double idealWeightForUser,double finalBmi,boolean isNameEmpty,boolean isGenderPressed){
        boolean valid=true;
        if (idealWeightForUser==0 || finalBmi==0 || isNameEmpty || !isGenderPressed) {
            valid=false;
        }
        return valid;
    }

    private double calculateBmiResult(JSlider userHeightSlider,JTextField userWeightText){
        double realWeight,realHeight,bmi=0;
        if (!isValidInput(userWeightText)){
            realWeight=Double.parseDouble(userWeightText.getText());
            realHeight=userHeightSlider.getValue();
            //note: due to the fact that the user insert height in cmd we multiplied by 100 to get right bmi
            bmi=realWeight * CONVERSION_CENTIMETER_TO_METER * CONVERSION_CENTIMETER_TO_METER / (realHeight * realHeight);
        }
        return bmi;
    }

    //check if first or last name is empty
    private boolean isNameEmpty(JTextField firstNameText, JTextField lastNameText){
        boolean isEmpty=false;
        if (firstNameText.getText().isEmpty() || lastNameText.getText().isEmpty()){
            isEmpty=true;
        }

        return isEmpty;
    }

    //check if button is pressed
    private boolean isGenderButtonPressed(ButtonGroup genderButtonGroup){
        boolean pressed=true;

        if (genderButtonGroup.getSelection()==null){
            pressed=false;
        }
        return pressed;
    }

}