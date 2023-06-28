package com.example.emr;

import com.example.emr.Records.XRayResults;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class XRayCSV {
    private final String delimiter = ",";
    private final String newline = "\n";
    private final String header = "p_ID,p_Doa,p_timeLog,image1,image2,image3,Remarks";

    public XRayCSV(){

    }

    public void writeXRay(List<XRayResults> XRayResultss, String fileName) {
        FileWriter fileWriter = null;
        try {
            File file = new File(fileName);
            fileWriter = new FileWriter(file);
            fileWriter.append(header);
            fileWriter.append(newline);

            for (XRayResults XRAyResult : XRayResultss) {
                fileWriter.append(XRAyResult.getP_IC());
                fileWriter.append(delimiter);
                fileWriter.append(XRAyResult.getP_Doa().toString());
                fileWriter.append(delimiter);
                fileWriter.append(XRAyResult.getTimeLog());
                fileWriter.append(delimiter);
                fileWriter.append(XRAyResult.getImage1p());
                fileWriter.append(delimiter);
                fileWriter.append(XRAyResult.getImage2p());
                fileWriter.append(delimiter);
                fileWriter.append(XRAyResult.getImage3p());
                fileWriter.append(delimiter);
                fileWriter.append(XRAyResult.getRemark());
                fileWriter.append(newline);
            }

        } catch (Exception e) {
            // handle any potential exception
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<XRayResults> readXRay(String fileName){
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<XRayResults> xray_results = new ArrayList<XRayResults>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);

                //convert date...
                LocalDate doa = LocalDate.parse(tokens[1]);

                // none datatype conversion needed

                if (tokens.length > 0){
                    XRayResults XRayLog = new XRayResults(tokens[0], doa, tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
                    xray_results.add(XRayLog);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            // handle exception
            e.printStackTrace();
        }
        return xray_results;
    }

}


