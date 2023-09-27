package com.hemebiotech.analytics;


import java.util.*;

public class AnalyticsCounter {
	private ISymptomReader iSymptomReader;
	private ISymptomWriter iSymptomWriter;

	public AnalyticsCounter(ISymptomReader iSymptomReader, ISymptomWriter iSymptomWriter) {
		this.iSymptomReader = iSymptomReader;
		this.iSymptomWriter = iSymptomWriter;
	}

	public List<String> getSymptoms() {
		return iSymptomReader.GetSymptoms();
	}

	public Map<String, Integer> countSymptoms(List<String> symptoms) {
		Map<String, Integer> symptomsCount = new HashMap<>();
		for( String symptom : symptoms){
			Integer count = symptomsCount.get(symptom);
			if (count == null) {
				count = 0;
			}
			symptomsCount.put(symptom, count +1);
		}
		return symptomsCount;
	}

	public Map<String, Integer> sortSymptoms(Map<String, Integer> symptoms) {
		Map<String, Integer> sortedSymptoms = new TreeMap<>(symptoms);
		return sortedSymptoms;
	}

	public void writeSymptoms(Map<String, Integer> symptoms) {
		iSymptomWriter.writeSymptoms(symptoms);
	}

	public static void main(String[] args) throws Exception {
		ISymptomReader symptomReader = new ReadSymptomDataFromFile("symptoms.txt");
		ISymptomWriter symptomWriter = new WriteSymptomDataToFile("result.out");
		AnalyticsCounter counter = new AnalyticsCounter(symptomReader, symptomWriter);

		List<String> symptoms = counter.getSymptoms();
		Map<String, Integer> countedSymptoms = counter.countSymptoms(symptoms);
		Map<String, Integer> sortedSymptoms = counter.sortSymptoms(countedSymptoms);
		counter.writeSymptoms(sortedSymptoms);
	}
}
