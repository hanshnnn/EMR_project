package com.example.emr;

import com.example.emr.Records.salivaResults;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class salivaCSV {
    private final String delimiter = ",";
    private final String newline = "\n";
    private final String header = "p_ID,p_Doa,p_timeLog,salivaPH,IgA,IgG,IgM,CytokineLvl,CortisolLvl,SodiumLvl,PotassiumLvl,CalciumLvl,Remarks";

    public salivaCSV(){}

    public void writeSaliva(List<salivaResults> salivaResultss, String filename, String timeLog) {
        FileWriter filewriter = null;
        try {
            File file = new File(filename);
            filewriter = new FileWriter(file);
            filewriter.append(header);
            filewriter.append(newline);

            for (salivaResults salivaResults : salivaResultss) {
                filewriter.append(salivaResults.getP_IC());
                filewriter.append(delimiter);
                filewriter.append(salivaResults.getP_Doa().toString());
                filewriter.append(delimiter);
                filewriter.append(salivaResults.getTimeLog());
                filewriter.append(delimiter);
                filewriter.append(String.valueOf(salivaResults.getSaliva_ph()));
                filewriter.append(delimiter);
                filewriter.append(String.valueOf(salivaResults.getIga()));
                filewriter.append(delimiter);
                filewriter.append(String.valueOf(salivaResults.getIgg()));
                filewriter.append(delimiter);
                filewriter.append(String.valueOf(salivaResults.getIgg()));
                filewriter.append(delimiter);
                filewriter.append(String.valueOf(salivaResults.getIgm()));
                filewriter.append(delimiter);
                filewriter.append(String.valueOf(salivaResults.getCytokinelvl()));
                filewriter.append(delimiter);
                filewriter.append(String.valueOf(salivaResults.getCortisollvl()));
                filewriter.append(delimiter);
                filewriter.append(String.valueOf(salivaResults.getSodiumlvl()));
                filewriter.append(delimiter);
                filewriter.append(String.valueOf(salivaResults.getPotassiumlvl()));
                filewriter.append(delimiter);
                filewriter.append(String.valueOf(salivaResults.getCalciumlvl()));
                filewriter.append(delimiter);
                filewriter.append(salivaResults.getRemarks());
                filewriter.append(newline);

            }
        } catch (Exception e) {
            // handle any potential exception
            e.printStackTrace();
        } finally {
            try{
                filewriter.flush();
                filewriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<salivaResults> readSaliva(String filename){
        BufferedReader bReader = null;
        File file = new File(filename);
        List<salivaResults> saliva_results = new ArrayList<salivaResults>();
        try {
            String line;
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null){
                String[] tokens = line.split(delimiter);

                //convert date...
                LocalDate doa = LocalDate.parse(tokens[1]);

                // to convert all the strings back to double...
                double saliva_ph = Double.parseDouble(tokens[3]);
                double iga = Double.parseDouble(tokens[4]);
                double igg = Double.parseDouble(tokens[5]);
                double igm = Double.parseDouble(tokens[6]);
                double cytokinelvl = Double.parseDouble(tokens[7]);
                double cortisollvl = Double.parseDouble(tokens[8]);
                double sodiumlvl = Double.parseDouble(tokens[9]);
                double potassiumlvl = Double.parseDouble(tokens[10]);
                double calciumlvl = Double.parseDouble(tokens[11]);

                if (tokens.length > 0){
                    salivaResults salivaLog = new salivaResults(tokens[0], doa, tokens[2], saliva_ph, iga, igg, igm, cytokinelvl,
                            cortisollvl, sodiumlvl, potassiumlvl, calciumlvl, tokens[11]);
                    saliva_results.add(salivaLog);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            // handle exception
            e.printStackTrace();
        }
        return saliva_results;
    }
}
