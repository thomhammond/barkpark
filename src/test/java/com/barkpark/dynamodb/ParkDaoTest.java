//package com.barkpark.dynamodb;
//
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
//import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
//import com.barkpark.dynamodb.models.Park;
//import com.barkpark.exceptions.NoParksFoundException;
//import com.barkpark.helpers.ParkTestHelper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.internal.stubbing.defaultanswers.ForwardsInvocations;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.*;
//import static org.mockito.MockitoAnnotations.openMocks;
//
//public class ParkDaoTest {
//    @Mock
//    private DynamoDBMapper dynamoDBMapper;
//
//    ParkDao parkDao;
//
//    @BeforeEach
//    public void setUp() {
//        openMocks(this);
//        parkDao = new ParkDao(dynamoDBMapper);
//    }
//
//    @Test
//    public void getParks_threeParksExist_returnsListWithThreeParks() {
//        // GIVEN
//        List<Park> parkList = ParkTestHelper.generateParkListWithNParks(3);
//
//        PaginatedScanList paginatedScanList = mock(PaginatedScanList.class, withSettings().defaultAnswer(new ForwardsInvocations(parkList)));
//
//        when(dynamoDBMapper.scan(eq(Park.class), any(DynamoDBScanExpression.class))).thenReturn(paginatedScanList);
//
//        // WHEN
//        List<Park> resultParkList = parkDao.getParks();
//
//        // THEN
//        assertEquals(
//                parkList.size(),
//                resultParkList.size(),
//                String.format("Expected parkList (%s) and resultParkList (%s) to be the be the same size",
//                        parkList, resultParkList)
//        );
//
//        for (int i = 0; i < parkList.size(); i++) {
//            assertEquals(
//                    parkList.get(i),
//                    resultParkList.get(i),
//                    String.format("Expected %dth park in parkList (%s) to match resultParkList (%s)",
//                            i, parkList.get(i), resultParkList.get(i))
//            );
//        }
//    }
//
//    @Test
//    public void getParks_NoParksExist_throwsNoParksFoundException() {
//        // GIVEN - no parks in table
//
//        when(dynamoDBMapper.scan(eq(Park.class), any(DynamoDBScanExpression.class))).thenReturn(null);
//
//        // WHEN & THEN
//        NoParksFoundException thrown = assertThrows(
//                NoParksFoundException.class,
//                () ->  parkDao.getParks(),
//                "Expected getParks to throw a NoParksFoundException"
//        );
//
//        assertEquals(thrown.getMessage(), "No parks found in the database");
//    }
//}
