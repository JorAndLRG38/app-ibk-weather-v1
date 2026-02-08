package com.ibk.weather.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
@RequiredArgsConstructor
public class Error {

    private @Nullable Integer code;
    private @Nullable String message;
}
