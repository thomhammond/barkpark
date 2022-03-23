package com.barkpark.models.requests.parks;

import java.util.Objects;

public class GetParksRequest {
    /**
     * Partition key used to scan for subsequent pages of results
     * Will be null if first page is being requested
     */
    private String exclusiveStartKey;

    // No-args constructor required for AWS Lambda
    public GetParksRequest() {}

    private GetParksRequest(Builder builder) {
        this.exclusiveStartKey = builder.exclusiveStartKey;
    }

    public String getExclusiveStartKey() {
        return exclusiveStartKey;
    }

    public void setExclusiveStartKey(String exclusiveStartKey) {
        this.exclusiveStartKey = exclusiveStartKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetParksRequest request = (GetParksRequest) o;
        return Objects.equals(getExclusiveStartKey(), request.getExclusiveStartKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExclusiveStartKey());
    }

    @Override
    public String toString() {
        return "GetParksRequest{" +
                "exclusiveStartKey='" + exclusiveStartKey + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String exclusiveStartKey;

        private Builder() {}

        public Builder withExclusiveStartKey(String exclusiveStartKey) {
            this.exclusiveStartKey = exclusiveStartKey;
            return this;
        }
        public GetParksRequest build() {
            return new GetParksRequest(this);
        }
    }
}
