package com.barkpark.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.barkpark.dynamodb.models.Park;
import com.barkpark.exceptions.NoParksFoundException;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkDao {
    private final DynamoDBMapper dynamoDbMapper;

    @Inject
    public ParkDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    public List<Park> getParks() {
        final Map<String, String> expressionAttributeNames = new HashMap<>();
        expressionAttributeNames.put("#projectedName", "name");
        expressionAttributeNames.put("#projectedID", "id");
        expressionAttributeNames.put("#projectedDescription", "description");
        expressionAttributeNames.put("#projectedLocation", "location");
        expressionAttributeNames.put("#projectedRating", "rating");

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withExpressionAttributeNames(expressionAttributeNames)
                .withProjectionExpression("#projectedName, #projectedID, #projectedDescription, #projectedLocation, #projectedRating")
                .withLimit(20);

        List<Park> parkList = dynamoDbMapper.scan(Park.class, scanExpression);

        // Consider case when parkList.isEmpty()... How to handle?
        if (parkList == null) {
            throw new NoParksFoundException("No parks found in the database");
        }

        return parkList;
    }

}
