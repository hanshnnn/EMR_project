package com.example.emr.Controllers;

import com.example.emr.PatientRecord;
import com.example.emr.ViewModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class pProfileController implements Initializable {
    public Rectangle rectangle_image;
    public Label label_name1;
    public Label label_pastmedicalrecord1;
    public Label label_dob1;
    public Label label_anyallergies1;
    public Label label_gender1;
    public Label label_dateofadmission1;
    public Label label_address1;
    public Tab analysis_tab;
    public Tab diagnosis_tab;
    public Button diagnosis_button;
    public ImageView imageview_image;
    public Label label_age1;

    private PatientRecord rowData;

    public static class profileController {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        diagnosis_tab.setOnSelectionChanged(Event -> {
            ViewModel.showDiagnosisForm(rowData);
        });
    }

    public void displayinfo(PatientRecord rowData) {
        this.rowData = rowData;
        label_name1.setText(rowData.getP_Name());
        label_dob1.setText(rowData.getP_Dob());
        label_address1.setText(rowData.getP_Address());
        label_anyallergies1.setText(rowData.getP_Allergies());
        label_dateofadmission1.setText(rowData.getP_Doa());
        label_gender1.setText(rowData.getP_Gender());
        label_pastmedicalrecord1.setText(rowData.getP_Pmr());

        String Ic_number = rowData.getP_Ic();
        int year = Integer.parseInt(Ic_number.substring(0, 2));
        int month = Integer.parseInt(Ic_number.substring(2, 4));
        int day = Integer.parseInt(Ic_number.substring(4, 6));
        int century = 1900 + (year <=23 ? 100 : 0);
        int birthYear = century + year;
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = LocalDate.of(birthYear, month, day);
        Period period = Period.between(birthDate, currentDate);
        int age = period.getYears();
        label_age1.setText(String.valueOf(age));

        String imagePath = rowData.getImagePath();
        if (imagePath != null) {
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                Image img = new Image(imageFile.toURI().toString());
                imageview_image.setImage(img);
            }
        }

    }
}
