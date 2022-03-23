package com.barkpark.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.barkpark.converters.ReviewLinkedListConverter;

import java.util.List;
import java.util.Objects;

/**
 * Represents a record in the parks table
 */
@DynamoDBTable(tableName = "parks")
public class Park {

    //TODO: Make HashKey = name & RangeKey = location, update classes accordingly
    //TODO: Add geometry instance variable to hold lat/long
    private String id;
    private String name;
    private String description;
    private String location;
    private Double rating;
    private List<Review> reviewList;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBAttribute(attributeName = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @DynamoDBAttribute(attributeName = "rating")
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @DynamoDBTypeConverted(converter = ReviewLinkedListConverter.class)
    @DynamoDBAttribute(attributeName = "review-list")
    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Park park = (Park) o;
        return getId().equals(park.getId()) && getName().equals(park.getName()) && Objects.equals(getDescription(), park.getDescription()) && getLocation().equals(park.getLocation()) && Objects.equals(getRating(), park.getRating()) && Objects.equals(getReviewList(), park.getReviewList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getLocation(), getRating(), getReviewList());
    }

    @Override
    public String toString() {
        return "Park{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", rating=" + rating +
                ", reviewList=" + reviewList +
                '}';
    }
}
