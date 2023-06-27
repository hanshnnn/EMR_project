package com.example.emr;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;

import java.io.File;
import java.time.LocalDate;

public class PatientRecord implements Comparable<PatientRecord>{
    private String p_Name;
    private String p_Ic;
    private String p_Dob;
    private String p_Gender;
    private String p_Address;
    private String p_Allergies;
    private String p_Doa;
    private String p_Pmr;
private Button del_Btn;
private Hyperlink view_text;

    private String imagePath;
    public static String filename = "patient.csv";
    PatientAccHandler pah = new PatientAccHandler();
    private Image img;

    public PatientRecord(){}
    public PatientRecord(String name, String ic, LocalDate dob, String gender, String address, String allergies, LocalDate doa , String pmr){
        this.p_Name = name;
        this.p_Ic = ic;
        this.p_Dob = dob.toString();
        this.p_Address = address;
        this.p_Allergies = allergies;
        this.p_Doa = doa.toString();
        this.p_Gender = gender;
        this.p_Pmr = pmr;
        this.del_Btn = new Button("Delete");
        this.view_text = new Hyperlink("View");
        this.imagePath = "ImageFile/" + p_Name + ".jpg";
    }


    public String getP_Name(){
        return p_Name;
    }

    public void setP_Name(String name){
       this.p_Name = name;
    }
    public String getP_Ic(){
        return p_Ic;
    }

    public void setP_Ic(String ic){
        this.p_Ic = ic;
    }

    public String getP_Dob(){
        return p_Dob;
    }

    public void setP_Dob(String dob){
        this.p_Dob = dob;
    }

    public String getP_Address(){
        return p_Address;
    }

    public void setP_Address(String address){
        this.p_Address = address;
    }

    public String getP_Allergies(){
        return p_Allergies;
    }

    public void setP_Allergies(String allergies){
       this.p_Allergies = allergies;
    }

    public String getP_Doa(){
        return p_Doa;
    }

    public void setP_Doa(String doa){
        this.p_Doa = doa;
    }

    public String getP_Pmr(){
        return p_Pmr;
    }

    public void setP_Pmr(String pmr){
        this.p_Pmr = pmr;
    }

    public String getP_Gender(){
        return p_Gender;
    }

    public void setP_Gender(String gender){
        this.p_Gender = gender;
    }

    public Button getDel_Btn(){
        return del_Btn;
    }

    public void setButton(Button button){
        this.del_Btn = button;
    }

    public Hyperlink getView_text(){
        return view_text;
    }

    public void setView_text(Hyperlink text){
        this.view_text = text;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
        if (imagePath != null) {
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                this.img = new Image(imageFile.toURI().toString());
            } else {
                this.img = null;
            }
        } else {
            this.img = null;
        }
    }

    @Override
    public int compareTo(PatientRecord o) {
        if(getP_Ic() == null || o.getP_Ic() == null){
            return 0;
        }
        return getP_Ic().compareTo(o.getP_Ic());
    }
public int compare(PatientRecord a , PatientRecord b ){
    return a.p_Name.compareTo(b.p_Name);
}
}
