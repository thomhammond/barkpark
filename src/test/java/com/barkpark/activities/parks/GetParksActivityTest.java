//package com.barkpark.activities.parks;
//
//import com.amazonaws.services.lambda.runtime.Context;
//import com.barkpark.dynamodb.ParkDao;
//import com.barkpark.dynamodb.models.Park;
//import com.barkpark.exceptions.NoParksFoundException;
//import com.barkpark.helpers.ParkTestHelper;
//import com.barkpark.models.requests.parks.GetParksRequest;
//import com.barkpark.models.results.parks.GetParksResult;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.mockito.MockitoAnnotations.openMocks;
//
//public class GetParksActivityTest {
//    @InjectMocks
//    private GetParksActivity getParksActivity;
//
//    @Mock
//    private ParkDao parkDao;
//
//    @Mock
//    Context context;
//
//    @Mock
//    GetParksRequest request;
//
//    @BeforeEach
//    private void setUp() {
//        openMocks(this);
//    }
//
//    @Test
//    public void handleRequest_parksExist_returnsParkModelListInResult() {
//        // GIVEN - A valid GetParksRequest and a list of parks
//        List<Park> parkList = ParkTestHelper.generateParkListWithNParks(3);
//
//        when(parkDao.getParks()).thenReturn(parkList);
//
//        // WHEN
//        GetParksResult result = getParksActivity.handleRequest(request, context);
//
//        // THEN
//        verify(parkDao).getParks();
//
//        assertEquals(3, result.getParkModelList().size(), "Expected ParkModelList.size() in Result to match ParkList.size()");
//
//        ParkTestHelper.assertParkListEqualsParkModelList(parkList, result.getParkModelList());
//    }
//
//    @Test
//    public void handleRequest_NoParksExist_throwsNoParksFoundException() {
//        // GIVEN - A valid GetParksRequest and no parks in table
//
//        when(parkDao.getParks()).thenThrow(new NoParksFoundException("No parks found in the database"));
//
//        // WHEN & THEN
//        NoParksFoundException thrown = assertThrows(
//                NoParksFoundException.class,
//                () -> getParksActivity.handleRequest(request, context),
//                "Expected getParks to throw a NoParksFoundException"
//        );
//
//        assertEquals(thrown.getMessage(), "No parks found in the database");
//    }
//}
