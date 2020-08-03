package com.hps.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CountRange {
    @JsonProperty("startNumber")
    String startNumber;

    @JsonProperty("endNumber")
    String endNumber;

    @JsonProperty("count")
    Integer count;

    public CountRange(String start, String end, int count) {
       this.startNumber = start;
       this.endNumber = end;
       this.count = count;
    }
}
