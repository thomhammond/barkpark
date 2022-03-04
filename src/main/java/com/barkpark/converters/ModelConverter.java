package com.barkpark.converters;

import com.barkpark.dynamodb.models.Park;
import com.barkpark.models.ParkModel;

import java.util.ArrayList;
import java.util.List;

public class ModelConverter {
    /**
     * Converts a provided {@link Park} into a {@link ParkModel} representation.
     * @param park the park to convert
     * @return the converted parkModel
     */
    public static ParkModel toParkModel(Park park) {

        return ParkModel.builder()
                .withId(park.getId())
                .withName(park.getName())
                .withDescription(park.getDescription())
                .withLocation(park.getLocation())
                .withRating(park.getRating())
                .build();
    }

    /**
     * Converts a provided {@link List<Park>} into a {@link List<ParkModel>} representation.
     * @param parkList the list of parks to be converted
     * @return the converted parkModelList
     */
    public static List<ParkModel> toParkModelList(List<Park> parkList) {
        List<ParkModel> parkModelList = new ArrayList<>();

        for (Park park : parkList) {
            parkModelList.add(toParkModel(park));
        }

        return parkModelList;
    }
}
