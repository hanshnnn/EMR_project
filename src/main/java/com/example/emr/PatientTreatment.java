package com.example.emr;

import java.time.LocalDate;

public class PatientTreatment {
    public String diagnosis_code;
    public LocalDate appointment_date;
    private String code;
    private String date;
    private String description;
    private String price;

    public PatientTreatment(String dc, LocalDate ad) {
        this.diagnosis_code = dc;
        this.appointment_date = ad;
    }

    public PatientTreatment() {

    }

    public String getDiagnosis_code() {
        return diagnosis_code;
    }

    public void setDiagnosis_code(String diagnosis_code) {
        this.diagnosis_code = diagnosis_code;
    }

    public LocalDate getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(LocalDate appointment_date) {
        this.appointment_date = appointment_date;
    }}

