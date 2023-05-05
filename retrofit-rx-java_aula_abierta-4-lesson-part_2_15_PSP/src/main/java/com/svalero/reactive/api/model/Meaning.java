package com.svalero.reactive.api.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meaning {
    String partOfSpeech;
    List<Definition> definitions;
    List<String> synonyms;
    List<String> antonyms;
}
