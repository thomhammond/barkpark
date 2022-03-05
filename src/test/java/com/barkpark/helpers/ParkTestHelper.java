package com.barkpark.helpers;

import com.barkpark.dynamodb.models.Park;
import com.barkpark.dynamodb.models.Review;
import com.barkpark.models.ParkModel;
import com.barkpark.utils.BarkParkUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkTestHelper {
    public static Park generatePark(int sequenceNumber) {
        String id = BarkParkUtils.generateParkId();
        String name = "park" + sequenceNumber;
        String description = "description" + sequenceNumber;
        String location = "location" + sequenceNumber;
        Double rating = BarkParkUtils.generateParkRating();
        List<Review> reviewList = new LinkedList<>();

        Park park = new Park();
        park.setId(id);
        park.setName(name);
        park.setDescription(description);
        park.setLocation(location);
        park.setRating(rating);
        park.setReviewList(reviewList);

        return park;
    }

    public static List<Park> generateParkListWithNParks(int numParks) {
        List<Park> parkList = new ArrayList<>();

        for (int i = 0; i < numParks; i++) {
            parkList.add(generatePark(i));
        }

        return parkList;
    }

    public static void assertParkListEqualsParkModelList(List<Park> parkList, List<ParkModel> parkModelList) {
        assertEquals(
                parkList.size(),
                parkModelList.size(),
                String.format("Expected parkList (%s) and parkModelList (%s) to be the same size",
                        parkList, parkModelList)
        );

        for (int i = 0; i < parkList.size(); i++) {
            assertParkEqualsParkModel(
                    parkList.get(i),
                    parkModelList.get(i),
                    String.format("Expected %dth park (%s) to match corresponding parkModel (%s)",
                            i, parkList.get(i), parkModelList.get(i))
            );
        }
    }

    public static void assertParkEqualsParkModel(Park park, ParkModel parkModel) {
        String message = String.format("Expected park (%s) to match parkModel (%s)", park, parkModel);
        assertParkEqualsParkModel(park, parkModel, message);
    }

    public static void assertParkEqualsParkModel(Park park, ParkModel parkModel, String message) {
        assertEquals(park.getId(), parkModel.getId(), message);
        assertEquals(park.getName(), parkModel.getName(), message);
        assertEquals(park.getDescription(), parkModel.getDescription(), message);
        assertEquals(park.getLocation(), parkModel.getLocation(), message);
        assertEquals(park.getRating(), parkModel.getRating(), message);
    }

}
