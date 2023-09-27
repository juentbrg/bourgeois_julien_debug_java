package com.hemebiotech.analytics;

import java.util.Map;

/**
 * An interface for writing symptoms and their occurrences.
 * Implementations of this interface should provide a mechanism to output
 * or store the symptom data.
 */
public interface ISymptomWriter {

    /**
     * Writes the provided map of symptoms and their occurrences.
     * How and where the symptoms are written is determined by the implementing class.
     *
     * @param symptoms a map where each key represents a symptom and its value represents the occurrence count
     */
    void writeSymptoms(Map<String, Integer> symptoms);
}