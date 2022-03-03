package com.barkpark.dependency;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class MapperModule {

    @Singleton
    @Provides
    public DynamoDBMapper provideDynamoDBMapper() {

        return new DynamoDBMapper(AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .withRegion(Regions.US_WEST_1)
                .build());
    }
}
