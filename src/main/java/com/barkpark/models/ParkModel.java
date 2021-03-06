package com.barkpark.models;

import java.util.Objects;

/**
 * Represents a park as defined by the BarkPark API
 */
public class ParkModel {
    private String id;
    private String name;
    private String description;
    private String location;
    private Double rating;

    private ParkModel(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.location = builder.location;
        this.rating = builder.rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkModel parkModel = (ParkModel) o;
        return getId().equals(parkModel.getId()) && getName().equals(parkModel.getName()) && Objects.equals(getDescription(), parkModel.getDescription()) && getLocation().equals(parkModel.getLocation()) && Objects.equals(getRating(), parkModel.getRating());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getLocation(), getRating());
    }

    @Override
    public String toString() {
        return "ParkModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", rating=" + rating +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String id;
        private String name;
        private String description;
        private String location;
        private Double rating;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder withRating(Double rating) {
            this.rating = rating;
            return this;
        }

        public ParkModel build() {
            return new ParkModel(this);
        }
    }
}
