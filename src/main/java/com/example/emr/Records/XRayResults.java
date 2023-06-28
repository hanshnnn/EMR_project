package com.example.emr.Records;

import java.time.LocalDate;

public class XRayResults implements Comparable<XRayResults>{
    private String p_IC;
    private LocalDate p_Doa;
    private String image1p;
    private String image2p;
    private String image3p;
    private String remark;
    private String TimeLog;
    public final static String filename = "XRayTest.csv";
    public XRayResults(String p_IC, LocalDate p_Doa, String timeLog, String image1path, String image2path, String image3path, String remarks){
        this.setP_IC(p_IC);
        this.setP_Doa(p_Doa);
        this.setTimeLog(timeLog);

        this.setImage1p(image1path);
        this.setImage2p(image2path);
        this.setImage3p(image3path);
        this.setRemark(remarks);

    }

    // implements interface Comparable
    @Override
    public int compareTo(XRayResults x) {
        return getP_IC().compareTo(x.getP_IC());
    }

    // getter and setter
    public String getImage1p() {
        return image1p;
    }

    public void setImage1p(String image1p) {
        this.image1p = image1p;
    }

    public String getImage2p() {
        return image2p;
    }

    public void setImage2p(String image2p) {
        this.image2p = image2p;
    }

    public String getImage3p() {
        return image3p;
    }

    public void setImage3p(String image3p) {
        this.image3p = image3p;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
