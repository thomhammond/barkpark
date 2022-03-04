package com.barkpark.dynamodb;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.barkpark.dynamodb.models.Park;
import com.barkpark.exceptions.NoParksFoundException;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Accesses data for parks using {@link Park} to represent the model in DynamoDB.
 */
public class ParkDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates a ParkDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the parks table
     */
    @Inject
    public ParkDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Returns a list of {@link Park}s.
     *
     * If no parks are found, throws NoParksFoundException
     *
     * @return a paginated list of stored Parks.
     */
    public List<Park> getParks() throws NoParksFoundException {

        final Map<String, String> expressionAttributeNames = new HashMap<>();
        expressionAttributeNames.put("#projectedName", "name");
        expressionAttributeNames.put("#projectedID", "id");
        expressionAttributeNames.put("#projectedDescription", "description");
        expressionAttributeNames.put("#projectedLocation", "location");
        expressionAttributeNames.put("#projectedRating", "rating");

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withExpressionAttributeNames(expressionAttributeNames)
                .withProjectionExpression("#projectedName, #projectedID, #projectedDescription, #projectedLocation, #projectedRating");

        //TODO: Need to consider AWS exception handling...
        List<Park> parkList = dynamoDbMapper.scan(Park.class, scanExpression);

        if (parkList == null || parkList.isEmpty()) {
            throw new NoParksFoundException("No parks found in the database");
        }

        return parkList;
    }

}
