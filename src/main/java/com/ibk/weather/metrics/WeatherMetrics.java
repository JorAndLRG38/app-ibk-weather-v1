package com.ibk.weather.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class WeatherMetrics {

    private final Counter apiCallsTotal;
    private final Counter apiCallsSuccess;
    private final Counter apiCallsError;

    public WeatherMetrics(MeterRegistry meterRegistry) {
        this.apiCallsTotal = Counter.builder("weather.api.calls.total")
            .description("Total API calls to OpenWeather")
            .register(meterRegistry);

        this.apiCallsSuccess = Counter.builder("weather.api.calls.success")
            .description("Successful API calls")
            .register(meterRegistry);

        this.apiCallsError = Counter.builder("weather.api.calls.error")
            .description("Failed API calls")
            .register(meterRegistry);
    }

    public void recordSuccess() {
        apiCallsTotal.increment();
        apiCallsSuccess.increment();
    }

    public void recordError() {
        apiCallsTotal.increment();
        apiCallsError.increment();
    }
}
