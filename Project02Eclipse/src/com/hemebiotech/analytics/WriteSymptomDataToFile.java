package com.hemebiotech.analytics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WriteSymptomDataToFile implements ISymptomWriter{

    private String filepath;

    public WriteSymptomDataToFile (String filepath) {
        this.filepath = filepath;
    }
    @Override
    public void writeSymptoms(Map<String, Integer> symptoms) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));

            for(Map.Entry<String, Integer> symptom : symptoms.entrySet()) {
                writer.write(symptom.getKey() + ": " + symptom.getValue());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
