package com.ibk.weather.models;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForecastResponse {

    private @Nullable String city;
    private @Nullable Object raw;
    @Valid
    private List<@Valid ForecastResponseDetail> forecasts = new ArrayList<>();

    public ForecastResponse addForecastsItem(ForecastResponseDetail forecastsItem) {
        if (this.forecasts == null) {
            this.forecasts = new ArrayList<>();
        }
        this.forecasts.add(forecastsItem);
        return this;
    }
}

