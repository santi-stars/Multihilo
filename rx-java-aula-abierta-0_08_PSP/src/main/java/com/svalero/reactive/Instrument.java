package com.svalero.reactive;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Instrument {
    private String type;
    private String brand;
    private int price;
}
