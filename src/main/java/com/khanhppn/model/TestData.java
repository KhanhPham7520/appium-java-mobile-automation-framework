package com.khanhppn.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(setterPrefix = "set")
public class TestData {

    private LoginData loginData;
    private SearchData searchData;
}
