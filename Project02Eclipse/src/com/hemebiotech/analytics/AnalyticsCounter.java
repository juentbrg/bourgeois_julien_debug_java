package com.hemebiotech.analytics;

import java.util.*;

/**
 * This class provides functionalities to read, count, sort, and write symptoms.
 * It leverages the capabilities provided by implementations of ISymptomReader and ISymptomWriter.
 */
public class AnalyticsCounter {

	/** The reader used to obtain symptoms. */
	private ISymptomReader iSymptomReader;

	/** The writer used to output counted symptoms. */
	private ISymptomWriter iSymptomWriter;

	/**
	 * Constructs an AnalyticsCounter with the specified reader and writer.
	 *
	 * @param iSymptomReader the symptom reader to be used
	 * @param iSymptomWriter the symptom writer to be used
	 */
	public AnalyticsCounter(ISymptomReader iSymptomReader, ISymptomWriter iSymptomWriter) {
		this.iSymptomReader = iSymptomReader;
		this.iSymptomWriter = iSymptomWriter;
	}

	/**
	 * Retrieves the list of symptoms from the reader.
	 *
	 * @return a list of symptoms
	 */
	public List<String> getSymptoms() {
		return iSymptomReader.getSymptoms();
	}

	/**
	 * Counts the occurrences of each symptom in the provided list.
	 *
	 * @param symptoms the list of symptoms to count
	 * @return a map of symptom names to their counts
	 */
	public Map<String, Integer> countSymptoms(List<String> symptoms) {
		Map<String, Integer> symptomsCount = new HashMap<>();
		for (String symptom : symptoms) {
			Integer count = symptomsCount.get(symptom);
			if (count == null) {
				count = 0;
			}
			symptomsCount.put(symptom, count + 1);
		}
		return symptomsCount;
	}

	/**
	 * Sorts the provided map of symptoms alphabetically based on symptom names.
	 *
	 * @param symptoms the map of symptoms to sort
	 * @return a sorted map of symptoms
	 */
	public Map<String, Integer> sortSymptoms(Map<String, Integer> symptoms) {
		return new TreeMap<>(symptoms);
	}

	/**
	 * Writes the provided map of symptoms using the writer.
	 *
	 * @param symptoms the map of symptoms to write
	 */
	public void writeSymptoms(Map<String, Integer> symptoms) {
		iSymptomWriter.writeSymptoms(symptoms);
	}

	/**
	 * The main entry point to the program. Reads symptoms, counts them, sorts them, and then writes the sorted symptoms.
	 *
	 * @param args command line arguments (not used)
	 * @throws Exception if any error occurs during processing
	 */
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
