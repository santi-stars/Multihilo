package com.svalero.reactive.api.model;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Change {
    String subject;
    String project;
    String branch;
    HashMap<String, String> owner;
}
