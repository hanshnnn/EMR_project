package com.example.emr.Records;

import java.time.LocalDate;

public class urineResults implements Comparable<urineResults>{

    private String p_IC;
    private LocalDate p_Doa;
    private double glucConcent;
    private double Gravity;
    private String Nitrities;
    private String Protein;
    private String RBC;
    private String Remarks;
    private String UrineClarity;
    private String UrineColour;
    private String UrineOdour;
    private String TimeLog;

    public final static String filename = "urineTest.csv";

    public urineResults(
                        String p_IC,
                        LocalDate p_Doa,
                        String timeLog,
                        double glucConcent,
                        double Gravity,
                        String Nitrities,
                        String Protein,
                        String RBC,
                        String Remarks,
                        String UrineClarity,
                        String UrineColour,
                        String UrineOdour) {
        this.setP_IC(p_IC);
        this.setP_Doa(p_Doa);
        this.setTimeLog(timeLog);

        this.setGlucConcent(glucConcent);
        this.setGravity(Gravity);
        this.setNitrities(Nitrities);
        this.setProtein(Protein);
        this.setRBC(RBC);
        this.setRemarks(Remarks);
        this.setUrineClarity(UrineClarity);
        this.setUrineColour(UrineColour);
        this.setUrineOdour(UrineOdour);
    }

    // implements interface Comparable
    @Override
    public int compareTo(urineResults u) {
        return getP_IC().compareTo(u.getP_IC());
    }

    // getter & setter
    public void setGlucConcent(double glucConcent) {
        this.glucConcent = glucConcent;
    }

    public void setGravity(double gravity) {
        Gravity = gravity;
    }

    public void setNitrities(String nitrities) {
        Nitrities = nitrities;
    }

    public void setProtein(String protein) {
        Protein = protein;
    }

    public void setRBC(String RBC) {
        this.RBC = RBC;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public void setUrineClarity(String urineClarity) {
        UrineClarity = urineClarity;
    }

    public void setUrineColour(String urineColour) {
        UrineColour = urineColour;
    }

    public void setUrineOdour(String urineOdour) {
        UrineOdour = urineOdour;
    }

    public double getGlucConcent() {
        return glucConcent;
    }

    public double getGravity() {
        return Gravity;
    }

    public String getNitrities() {
        return Nitrities;
    }

    public String getProtein() {
        return Protein;
    }

    public String getRBC() {
        return RBC;
    }

    public String getRemarks() {
        return Remarks;
    }

    public String getUrineClarity() {
        return UrineClarity;
    }

    public String getUrineColour() {
        return UrineColour;
    }

    public String getUrineOdour() {
        return UrineOdour;
    }

    public String getP_IC() {
        return p_IC;
    }

    public void setP_IC(String p_IC) {
        this.p_IC = p_IC;
    }

    public LocalDate getP_Doa() {
        return p_Doa;
    }

    public void setP_Doa(LocalDate p_Doa) {
        this.p_Doa = p_Doa;
    }

    public String getTimeLog() {
        return TimeLog;
    }

    public void setTimeLog(String timeLog) {
        TimeLog = timeLog;
    }
}
