package com.barkpark.dynamodb;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
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
    public List<Park> getParks(String exclusiveStartKey) throws NoParksFoundException {

        // Name and Location are Reserved Words in DynamoDB, we must project them for the Lambda function to work correctly
        final Map<String, String> expressionAttributeNames = new HashMap<>();
        expressionAttributeNames.put("#projectedName", "name");
        expressionAttributeNames.put("#projectedLocation", "location");

        Map<String, AttributeValue> exclusiveStartKeyMap = null;
        if(exclusiveStartKey != null) {
            exclusiveStartKeyMap = new HashMap<>();
            exclusiveStartKeyMap.put("id", new AttributeValue().withS(exclusiveStartKey));
        }

        //TODO: set withLimit and handle subsequent calls with LastEvaluatedKey and withExclusiveStartKey
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withExpressionAttributeNames(expressionAttributeNames)
                .withExclusiveStartKey(exclusiveStartKeyMap)
                .withLimit(1)
                .withProjectionExpression("#projectedName, id, description, #projectedLocation, rating");

        ScanResultPage<Park> parkScanResults = dynamoDbMapper.scanPage(Park.class, scanExpression);
        List<Park> parkList = parkScanResults.getResults();

        if (parkList == null || parkList.isEmpty()) {
            throw new NoParksFoundException("No parks found in the database");
        }

        return parkList;
    }

}
