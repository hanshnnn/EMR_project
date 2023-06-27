package com.example.emr.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MedicalHistoryController extends  RegistrationAccountController implements Initializable {

    @FXML
    public ToggleButton tb1;
    @FXML
    public ToggleButton tb2;
    @FXML
    public ToggleButton tb3;
    @FXML
    public ToggleButton tb4;
    @FXML
    public ToggleButton tb5;
    @FXML
    public ToggleButton tb6;
    @FXML
    public ToggleButton tb7;
    @FXML
    public ToggleButton tb8;
    @FXML
    public ToggleButton tb9;

    @FXML
    public RadioButton rb1;
    @FXML
    public RadioButton rb2;
    @FXML
    public RadioButton rb3;
    @FXML
    public RadioButton rb4;
    @FXML
    public RadioButton rb5;
    @FXML
    public RadioButton rb6;
    @FXML
    public RadioButton rb7;
    @FXML
    public RadioButton rb8;
    @FXML
    public RadioButton rb9;
    @FXML
    public RadioButton rb10;
    @FXML
    public RadioButton rb11;
    @FXML
    public RadioButton rb12;
    @FXML
    public RadioButton rb13;
    @FXML
    public RadioButton rb14;
    @FXML
    public RadioButton rb15;
    @FXML
    public RadioButton rb16;
    @FXML
    public RadioButton rb17;
    @FXML
    public RadioButton rb18;
    @FXML
    public RadioButton rb19;
    @FXML
    public RadioButton rb20;
    @FXML
    public RadioButton rb21;
    @FXML
    public RadioButton rb22;
    @FXML
    public RadioButton rb23;
    @FXML
    public RadioButton rb24;
    @FXML
    public Button save_Btn;

    private String delimiter = ",";
    private String newline = "\n";
    //need delete id from the header
    private String header = "ic ,doa ,q1 ,q2 ,q3 ,q4 ,q5 ,q6, q7 ,q8 ,q9 ,q10";

    private String selectedToggleButtonText;
    private String selectedRadioButtonText;
    private List<String> radioButtonTexts = new ArrayList<>();

    private String ic;
    private String doa;


    //private String ic;
    //private String doa;


    //togglegroup1... represent ques2 and so on q3,4,5 for other group
    public ToggleGroup toggleGroup1;
    public ToggleGroup toggleGroup2;
    public ToggleGroup toggleGroup3;
    public ToggleGroup toggleGroup4;
    public ToggleGroup toggleGroup5;
    public ToggleGroup toggleGroup6;
    public ToggleGroup toggleGroup7;
    public ToggleGroup toggleGroup8;

    public ToggleGroup toggleGroup = new ToggleGroup();


    //crystal: pls don't touch this class's code, havent done yet. Medical history.fxml aso don't touch, it's linked

    /*public void setPatientInfo(String ic, String doa) {
        this.ic = ic;
        this.doa = doa;
    }*/

    public MedicalHistoryController(){}
    public MedicalHistoryController(String header, String delimiter, String newline) {
        this.header = header;
        this.delimiter = delimiter;
        this.newline = newline;
    }

    public MedicalHistoryController(String ic, String doa){
        this.ic = ic;
        this.doa = doa;
    }

    public String getIc() {
        return ic;
    }

    public String getDoa() {
        return doa;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public void setDoa(String doa) {
        this.doa = doa;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tb1.setToggleGroup(toggleGroup);
        tb2.setToggleGroup(toggleGroup);
        tb3.setToggleGroup(toggleGroup);
        tb4.setToggleGroup(toggleGroup);
        tb5.setToggleGroup(toggleGroup);
        tb6.setToggleGroup(toggleGroup);
        tb7.setToggleGroup(toggleGroup);
        tb8.setToggleGroup(toggleGroup);
        tb9.setToggleGroup(toggleGroup);

        toggleGroup1 = new ToggleGroup();
        toggleGroup2 = new ToggleGroup();
        toggleGroup3 = new ToggleGroup();
        toggleGroup4 = new ToggleGroup();
        toggleGroup5 = new ToggleGroup();
        toggleGroup6 = new ToggleGroup();
        toggleGroup7 = new ToggleGroup();
        toggleGroup8 = new ToggleGroup();

        rb1.setToggleGroup(toggleGroup1);
        rb2.setToggleGroup(toggleGroup1);
        rb3.setToggleGroup(toggleGroup1);

        rb4.setToggleGroup(toggleGroup2);
        rb5.setToggleGroup(toggleGroup2);
        rb6.setToggleGroup(toggleGroup2);

        rb7.setToggleGroup(toggleGroup3);
        rb8.setToggleGroup(toggleGroup3);
        rb9.setToggleGroup(toggleGroup3);

        rb10.setToggleGroup(toggleGroup4);
        rb11.setToggleGroup(toggleGroup4);
        rb12.setToggleGroup(toggleGroup4);

        rb13.setToggleGroup(toggleGroup5);
        rb14.setToggleGroup(toggleGroup5);
        rb15.setToggleGroup(toggleGroup5);

        rb16.setToggleGroup(toggleGroup6);
        rb17.setToggleGroup(toggleGroup6);
        rb18.setToggleGroup(toggleGroup6);

        rb19.setToggleGroup(toggleGroup7);
        rb20.setToggleGroup(toggleGroup7);
        rb21.setToggleGroup(toggleGroup7);

        rb22.setToggleGroup(toggleGroup8);
        rb23.setToggleGroup(toggleGroup8);
        rb24.setToggleGroup(toggleGroup8);

        save_Btn.setOnAction(e -> {
            try {
                saveSelectedDataToCSV();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

    }

    @FXML
    private void handleRadioButtonClick(ActionEvent event) {
        RadioButton clickedRadioButton = (RadioButton) event.getSource();
        selectedRadioButtonText = clickedRadioButton.getText();
        radioButtonTexts.add(selectedRadioButtonText); // Add to the list
        System.out.println("Selected radio button text: " + selectedRadioButtonText);
    }

    @FXML
    private void handleToggleButtonClick(ActionEvent event) {
        ToggleButton clickedButton = (ToggleButton) event.getSource();
        if (clickedButton.isSelected()) {
            selectedToggleButtonText = clickedButton.getText();
            System.out.println("Selected toggle button text: " + selectedToggleButtonText);
        } else {
            selectedToggleButtonText = null;
            System.out.println("No toggle button selected");
        }
    }

    @FXML
    public void saveSelectedDataToCSV() throws IOException {

        String ic_text = getIc();
        String doa_text = getDoa();

        try {
            File file = new File("medicalHistory.csv");
            FileWriter writer = new FileWriter(file);

            // Write header
            writer.append(header);
            writer.append(newline);

            System.out.println("passed ic " + ic_text);
            System.out.println("passed ic " + doa_text);
            //get from registration form
            /*String ic_text = ic;
            String doa_text = doa;*/

            // Write selected data
            writer.append(ic_text);
            writer.append(delimiter);
            writer.append(doa_text);
            writer.append(delimiter);
            writer.append(selectedToggleButtonText);
            writer.append(delimiter);
            for(String radioButtonText : radioButtonTexts){
                writer.append(radioButtonText);
                writer.append(delimiter);
            }
            writer.append(newline);

            writer.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("Medical history saved!");
            alert.show();
            System.out.println("Medical history saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
