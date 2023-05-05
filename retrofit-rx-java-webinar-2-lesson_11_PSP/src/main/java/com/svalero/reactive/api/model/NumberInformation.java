package com.svalero.reactive.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumberInformation {
    String text;
    int number;
    boolean found;
    String type;
}
