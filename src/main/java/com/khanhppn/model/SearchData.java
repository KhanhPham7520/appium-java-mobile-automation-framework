package com.khanhppn.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(setterPrefix = "set")
public class SearchData {

    private String searchText;
}
