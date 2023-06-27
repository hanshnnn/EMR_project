package com.example.emr;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class PatientDiagnosis {
    public String p_ic;
    public String p_doa;
    public ArrayList<String> p_symptoms;
    public String p_remark;
    public String practitioner;
    public String diagnosis_code;

    public static String filename = "diagnosis.csv";
    public PatientDiagnosis(){}


    public PatientDiagnosis(String ic , String doa , ArrayList<String> symptoms, String remark , String practitioner, String diagnosis_code){
        this.p_ic = ic;
        this.p_doa = doa;
        this.p_symptoms = symptoms;
        this.p_remark = remark;
        this.practitioner = practitioner;
        this.diagnosis_code = diagnosis_code;
    }

    public String getP_ic() {
        return p_ic;
    }

    public void setP_ic(String p_ic) {
        this.p_ic = p_ic;
    }

    public String getP_doa() {
        return p_doa;
    }

    public void setP_doa(String p_doa) {
        this.p_doa = p_doa;
    }

    public ArrayList<String> getP_symptoms() {
        return p_symptoms;
    }

    public void setP_symptoms(ArrayList<String> p_symptoms) {
        this.p_symptoms = p_symptoms;
    }

    public String getP_remark() {
        return p_remark;
    }

    public void setP_remark(String p_remark) {
        this.p_remark = p_remark;
    }

    public String getPractitioner() {
        return practitioner;
    }

    public void setPractitioner(String practitioner) {
        this.practitioner = practitioner;
    }

    public String getDiagnosis_code() {
        return diagnosis_code;
    }

    public void setDiagnosis_code(String diagnosis_code) {
        this.diagnosis_code = diagnosis_code;
    }

    public int compareTo(PatientDiagnosis p) {
        if(getP_ic() == null || p.getP_ic() == null){
            return 0;
        }
        return getP_ic().compareTo(p.getP_ic());
    }
    public int compare(PatientDiagnosis a , PatientDiagnosis b){
        return a.p_doa.compareTo(b.p_doa);
    }
}
