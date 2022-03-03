package com.barkpark.models.results.parks;

import com.barkpark.models.ParkModel;

import java.util.List;

public class GetParksResult {
    private List<ParkModel> parkModelList;

    // No-args constructor required for AWS Lambda
    public GetParksResult() {}

    private GetParksResult(Builder builder) {
        this.parkModelList = builder.parkModelList;
    }

    public List<ParkModel> getParkModelList() {
        return parkModelList;
    }

    public void setParkModelList(List<ParkModel> parkModelList) {
        this.parkModelList = parkModelList;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<ParkModel> parkModelList;

        public Builder withParkList(List<ParkModel> parkModelList) {
            this.parkModelList = parkModelList;
            return this;
        }

        public GetParksResult build() {
            return new GetParksResult(this);
        }
    }
}
