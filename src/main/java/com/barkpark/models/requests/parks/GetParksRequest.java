package com.barkpark.models.requests.parks;

public class GetParksRequest {

    // No-args constructor required for AWS Lambda
    public GetParksRequest() {}

    private GetParksRequest(Builder builder) {

    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        public GetParksRequest build() {
            return new GetParksRequest(this);
        }
    }
}
