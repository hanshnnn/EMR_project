package com.example.emr.Records;

import java.time.LocalDate;

public class physicalResults implements Comparable<physicalResults>{

    private String p_IC;
    private LocalDate p_Doa;
    private double bloodPressure;
    private double heartRate;
    private double bodyTemp;
    private double Height;
    private String height_index;
    private double BMI;
    private double Weight;
    private String weight_index;
    private String Remark;
    private String TimeLog;
    public final static String filename = "physicalTest.csv";

    public physicalResults(String p_IC,
                           LocalDate p_Doa,
                           String timeLog,
                           double bloodPressure,
                           double heartRate,
                           double bodyTemp,
                           double Height,
                           String height_index,
                           double BMI,
                           double Weight,
                           String weight_index,
                           String Remark) {
        this.setP_IC(p_IC);
        this.setP_Doa(p_Doa);
        this.setTimeLog(timeLog);

        this.setBloodPressure(bloodPressure);
        this.setHeartRate(heartRate);
        this.setBodyTemp(bodyTemp);
        this.setHeight(Height);
        this.setHeight_index(height_index);
        this.setBMI(BMI);
        this.setWeight(Weight);
        this.setWeight_index(weight_index);
        this.setRemark(Remark);
    }

    // implements interface Comparable
    @Override
    public int compareTo(physicalResults p) {
        return getP_IC().compareTo(p.getP_IC());
    }

    //getter setter
    public double getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(double bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public double getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(double heartRate) {
        this.heartRate = heartRate;
    }

    public double getBodyTemp() {
        return bodyTemp;
    }

    public void setBodyTemp(double bodyTemp) {
        this.bodyTemp = bodyTemp;
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(double height) {
        Height = height;
    }

    public String getHeight_index() {
        return height_index;
    }

    public void setHeight_index(String height_index) {
        this.height_index = height_index;
    }

    public double getBMI() {
        return BMI;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }

    public String getWeight_index() {
        return weight_index;
    }

    public void setWeight_index(String weight_index) {
        this.weight_index = weight_index;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
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
