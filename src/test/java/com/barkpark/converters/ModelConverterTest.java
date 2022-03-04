package com.barkpark.converters;

import com.barkpark.dynamodb.models.Park;
import com.barkpark.helpers.ParkTestHelper;
import com.barkpark.models.ParkModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


public class ModelConverterTest {
    Park park;
    List<Park> parkList;

    @BeforeEach
    public void setUp() {
        park = ParkTestHelper.generatePark(1);
        parkList = ParkTestHelper.generateParkListWithNParks(3);
    }

    @Test
    public void toParkModel_withPark_returnsCorrectParkModel() {
        // GIVEN - a park

        // WHEN
        ParkModel parkModel = ModelConverter.toParkModel(park);

        // THEN
        ParkTestHelper.assertParkEqualsParkModel(park, parkModel);
    }

    @Test
    public void toParkModelList_withParkList_returnsCorrectParkModelList() {
        // GIVEN - a list of parks

        // WHEN
        List<ParkModel> parkModelList = ModelConverter.toParkModelList(parkList);

        // THEN
        ParkTestHelper.assertParkListEqualsParkModelList(parkList, parkModelList);
    }
}
