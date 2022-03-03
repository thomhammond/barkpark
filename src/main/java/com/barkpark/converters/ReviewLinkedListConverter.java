package com.barkpark.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.barkpark.activities.parks.GetParksActivity;
import com.barkpark.dynamodb.models.Review;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * DynamoDBMapper converts lists to {@link java.util.ArrayList}s by default
 * we want to convert to a {@link LinkedList}
 */
public class ReviewLinkedListConverter implements DynamoDBTypeConverter<String, List> {
    private static final Gson GSON = new Gson();
    private static final Logger logger = LoggerFactory.getLogger(GetParksActivity.class);

    @Override
    public String convert(List listToBeConverted) {
        logger.info("Converting: {}", listToBeConverted);

        return GSON.toJson(listToBeConverted);
    }

    @Override
    public List unconvert(String dynamoDbRepresentation) {
        logger.info("Unconverting: {}", dynamoDbRepresentation);

        // need to provide the type parameter of the list to convert correctly
        return GSON.fromJson(dynamoDbRepresentation, new TypeToken<LinkedList<Review>>() { } .getType());
    }
}
