package com.example.emr.Records;

import java.time.LocalDate;

public class bloodResults implements Comparable<bloodResults> {
    private String p_IC;
    private LocalDate p_Doa;
    private double rbc;
    private double platelets_count;
    private double bloodGluLev;
    private double t4Lev;
    private double tshLev;
    private double haemogLev;
    private double choles_Lev;
    private double t3Lev;
    private double wbc;
    private String remarks;
    private String TimeLog;
    public final static String filename = "bloodTest.csv";


    public bloodResults(String p_IC, LocalDate p_Doa, String timeLog, double rbc, double platelets_count, double bloodGluLev, double t4Lev,
                        double tshLev, double haemogLev, double choles_Lev, double t3Lev, double wbc, String remarks){

        this.setP_IC(p_IC);
        this.setP_Doa(p_Doa);
        this.setTimeLog(timeLog);

        this.setRbc(rbc);
        this.setPlatelets_count(platelets_count);
        this.setBloodGluLev(bloodGluLev);
        this.setT4Lev(t4Lev);
        this.setTshLev(tshLev);
        this.setHaemogLev(haemogLev);
        this.setCholes_Lev(choles_Lev);
        this.setT3Lev(t3Lev);
        this.setWbc(wbc);
        this.setRemarks(remarks);

    }

    // implements interface Comparable
    @Override
    public int compareTo(bloodResults o) {
        return getP_IC().compareTo(o.getP_IC());
    }

    // getter & setter
    public double getRbc() {
        return rbc;
    }

    public void setRbc(double rbc) {
        this.rbc = rbc;
    }

    public double getPlatelets_count() {
        return platelets_count;
    }

    public void setPlatelets_count(double platelets_count) {
        this.platelets_count = platelets_count;
    }

    public double getBloodGluLev() {
        return bloodGluLev;
    }

    public void setBloodGluLev(double bloodGluLev) {
        this.bloodGluLev = bloodGluLev;
    }

    public double getT4Lev() {
        return t4Lev;
    }

    public void setT4Lev(double t4Lev) {
        this.t4Lev = t4Lev;
    }

    public double getTshLev() {
        return tshLev;
    }

    public void setTshLev(double tshLev) {
        this.tshLev = tshLev;
    }

    public double getHaemogLev() {
        return haemogLev;
    }

    public void setHaemogLev(double haemogLev) {
        this.haemogLev = haemogLev;
    }

    public double getCholes_Lev() {
        return choles_Lev;
    }

    public void setCholes_Lev(double choles_Lev) {
        this.choles_Lev = choles_Lev;
    }

    public double getT3Lev() {
        return t3Lev;
    }

    public void setT3Lev(double t3Lev) {
        this.t3Lev = t3Lev;
    }

    public double getWbc() {
        return wbc;
    }

    public void setWbc(double wbc) {
        this.wbc = wbc;
    }

    public String getP_IC() {
        return p_IC;
    }

    public void setP_IC(String p_IC) {
        this.p_IC = p_IC;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
