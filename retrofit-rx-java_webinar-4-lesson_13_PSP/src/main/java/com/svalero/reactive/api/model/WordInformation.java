package com.svalero.reactive.api.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordInformation {
    String word;
    String phonetic;
    List<PhoneticsInfo> phonetics;
    String origin;
    List<Meaning> meanings;
}
