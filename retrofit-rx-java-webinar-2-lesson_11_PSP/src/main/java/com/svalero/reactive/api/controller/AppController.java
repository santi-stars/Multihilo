package com.svalero.reactive.api.controller;

import com.svalero.reactive.api.service.NumbersInformationService;

import io.reactivex.functions.Consumer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AppController {

    public TextField inputNumber;
    public TextField inputType;
    public Button btInformation;
    public TextArea numberInformationResult;
    
    public AppController(){

    }

    @FXML
    public void getNumberInformation(ActionEvent event){
        String requestedNumber = inputNumber.getText();
        String requestedType = inputType.getText();
        inputNumber.clear();
        inputType.clear();
        inputNumber.requestFocus();

        NumbersInformationService numbersInformationService = new NumbersInformationService();
        Consumer<String> user = (informationText) -> {
            System.out.println(informationText);
            numberInformationResult.setText(informationText);
        };

        numbersInformationService.getInformation(requestedNumber, requestedType).subscribe(user);
    }
}
