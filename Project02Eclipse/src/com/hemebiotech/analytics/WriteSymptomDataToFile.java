package com.hemebiotech.analytics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Implementation of {@link ISymptomWriter} that writes symptom data to a file.
 * Each symptom and its count are written on a new line in the format: "symptom: count".
 */
public class WriteSymptomDataToFile implements ISymptomWriter{
    private String filePath;
    public WriteSymptomDataToFile (String filePath) {
        this.filePath = filePath;
    }
    @Override
    public void writeSymptoms(Map<String, Integer> symptoms) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for(Map.Entry<String, Integer> symptom : symptoms.entrySet()) {
                writer.write(symptom.getKey() + ": " + symptom.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
