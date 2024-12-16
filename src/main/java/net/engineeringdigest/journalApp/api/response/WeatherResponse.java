package net.engineeringdigest.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {

    public Current current;

    @Getter
    @Setter
    public class Current {
        @JsonProperty("observation_time")
        public String observationTime;
        public int temperature;
        public int wind_speed;
        public int feelslike;
    }
}

