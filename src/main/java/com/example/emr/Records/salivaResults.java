package com.example.emr.Records;

import java.time.LocalDate;

public class salivaResults implements Comparable<salivaResults>{
    private String p_IC;
    private LocalDate p_Doa;
    private double saliva_ph;
    private double iga;
    private double igg;
    private double igm;
    private double cytokinelvl;
    private double cortisollvl;
    private double sodiumlvl;
    private double potassiumlvl;
    private double calciumlvl;
    private String remarks;
    private String TimeLog;

    public final static String filename = "salivaTest.csv";

    public salivaResults(
            String p_IC,
            LocalDate p_Doa,
            String timeLog,
            double saliva_ph,
            double iga,
            double igg,
            double igm,
            double cytokinelvl,
            double cortisollvl,
            double sodiumlvl,
            double potassiumlvl,
            double calciumlvl,
            String remarks){
        this.setP_IC(p_IC);
        this.setP_Doa(p_Doa);
        this.setTimeLog(timeLog);

        this.setSaliva_ph(saliva_ph);
        this.setIga(iga);
        this.setIgg(igg);
        this.setIgm(igm);
        this.setCytokinelvl(cytokinelvl);
        this.setCortisollvl(cortisollvl);
        this.setSodiumlvl(sodiumlvl);
        this.setPotassiumlvl(potassiumlvl);
        this.setCalciumlvl(calciumlvl);
        this.setRemarks(remarks);
    }

    // implements interface Comparable
    @Override
    public int compareTo(salivaResults s) {
        return getP_IC().compareTo(s.getP_IC());
    }

    //getters setters
    public double getSaliva_ph() {
        return saliva_ph;
    }

    public void setSaliva_ph(double saliva_ph) {
        this.saliva_ph = saliva_ph;
    }

    public double getIga() {
        return iga;
    }

    public void setIga(double iga) {
        this.iga = iga;
    }

    public double getIgg() {
        return igg;
    }

    public void setIgg(double igg) {
        this.igg = igg;
    }

    public double getIgm() {
        return igm;
    }

    public void setIgm(double igm) {
        this.igm = igm;
    }

    public double getCytokinelvl() {
        return cytokinelvl;
    }

    public void setCytokinelvl(double cytokinelvl) {
        this.cytokinelvl = cytokinelvl;
    }

    public double getCortisollvl() {
        return cortisollvl;
    }

    public void setCortisollvl(double cortisollvl) {
        this.cortisollvl = cortisollvl;
    }

    public double getSodiumlvl() {
        return sodiumlvl;
    }

    public void setSodiumlvl(double sodiumlvl) {
        this.sodiumlvl = sodiumlvl;
    }

    public double getPotassiumlvl() {
        return potassiumlvl;
    }

    public void setPotassiumlvl(double potassiumlvl) {
        this.potassiumlvl = potassiumlvl;
    }

    public double getCalciumlvl() {
        return calciumlvl;
    }

    public void setCalciumlvl(double calciumlvl) {
        this.calciumlvl = calciumlvl;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
