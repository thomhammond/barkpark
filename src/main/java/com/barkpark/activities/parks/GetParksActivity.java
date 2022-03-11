package com.barkpark.activities.parks;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.converters.ModelConverter;
import com.barkpark.dynamodb.ParkDao;
import com.barkpark.dynamodb.models.Park;
import com.barkpark.exceptions.NoParksFoundException;
import com.barkpark.models.ParkModel;
import com.barkpark.models.requests.parks.GetParksRequest;
import com.barkpark.models.results.parks.GetParksResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the GetParksActivity for the BarkPark's GetParks API.
 *
 * This API allows the client to get a list of parks.
 */
public class GetParksActivity implements RequestHandler<GetParksRequest, GetParksResult> {
    private static final Logger logger = LoggerFactory.getLogger(GetParksActivity.class);
    private final ParkDao parkDao;

    /**
     * Instantiates a new GetParksActivity object.
     *
     * @param parkDao {@link ParkDao} object to access the parks table
     */
    @Inject
    public GetParksActivity(ParkDao parkDao) {
        this.parkDao = parkDao;
    }

    // TODO: Should this method and the provider throw exceptions in the method signature?
    @Override
    public GetParksResult handleRequest(GetParksRequest request, Context context) throws NoParksFoundException {
        logger.info("Received GetParksRequest {}", request);

        List<Park> parkList = parkDao.getParks();

        List<ParkModel> parkModelList = ModelConverter.toParkModelList(parkList);

        return GetParksResult.builder()
                .withParkList(parkModelList)
                .build();
    }
}
