package com.example.emr;

public class medicineRecord implements Comparable<medicineRecord>{




    private  String P_ic;
    private  String P_Doa;
    private String chosenMC;
    private String duration;
    private String quantity;
    private String chosenDoc;
    private String remarks;
    public static String filename = "medicineForm.csv";


    public medicineRecord() {}

    public medicineRecord(String ic, String doa,String chosenMC, String duration, String quantity, String chosenDoc, String remarks) {
        this.P_ic = ic;
        this.P_Doa = doa;
        this.chosenMC = chosenMC;
        this.duration = duration;
        this.quantity = quantity;
        this.chosenDoc = chosenDoc;
        this.remarks = remarks;
    }


    public medicineRecord(String chosenMC, String duration, String quantity, String chosenDoc, String remarks) {
        this.chosenMC = chosenMC;
        this.duration = duration;
        this.quantity = quantity;
        this.chosenDoc = chosenDoc;
        this.remarks = remarks;
    }


    public String getP_ic() {
        return P_ic;
    }
    public void setP_ic (String P_ic ){this.P_ic = P_ic;}


    public String getP_Doa() {
        return P_Doa;
    }
    public void setP_Doa (String P_Doa ){this.P_Doa = P_Doa;}


    public String getDuration() {
        return duration;
    }
    public void setDuration (String duration){this.duration = duration;}


    public String getChosenMC() {
        return chosenMC;
    }
    public void setChosenMC (String chosenMC){this.chosenMC = chosenMC;}


    public String getQuantity() {
        return quantity;
    }
    public void setQuantity (String quantity){this.quantity = quantity;}


    public String getChosenDoc() {
        return chosenDoc;
    }
    public void setChosenDoc (String chosenDoc){this.chosenDoc = chosenDoc;}


    public String getRemarks() {
        return remarks;
    }
    public void setRemarks (String remarks){this.remarks = remarks;}


    @Override
    public int compareTo (medicineRecord o){
        if (getChosenMC() == null || o.getChosenMC() == null) {
            return 0;
        }
        return getChosenMC().compareTo(o.chosenMC);
    }


    public int compare(medicineRecord a , medicineRecord b ){
        return a.P_ic.compareTo(b.P_ic);
    }




}




