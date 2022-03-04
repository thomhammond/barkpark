package com.barkpark.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class BarkParkUtils {
    // package private for testing
    static final int PARK_ID_LENGTH = 6;

    /**
     * Generates a random park id of length equal to PARK_ID_LENGTH
     * @return the generated park id
     */
    public static String generateParkId() {
        return RandomStringUtils.randomAlphanumeric(PARK_ID_LENGTH);
    }

    /**
     * Generates a random park rating value between 1.0 and 5.0
     * @return the generated park rating
     */
    public static Double generateParkRating() {
        Random r = new Random();
        double randomRating =  1.0 + (5.0 - 1.0) * r.nextDouble();
        return (double) Math.round(randomRating * 10d) / 10d;
    }
}
