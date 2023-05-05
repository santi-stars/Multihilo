package com.svalero.reactive.api.controller;

import com.svalero.reactive.api.model.Definition;
import com.svalero.reactive.api.task.DictionaryTask;

import io.reactivex.functions.Consumer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AppController {

    public TextField wordInput;
    public Button btSearch;
    public Button btDelete;
    public TextField deleteInput;
    public TextArea definitionsArea;

    private DictionaryTask dictionaryTask;

    @FXML
    public void searchDefintions(ActionEvent event){
        String requestedWord = wordInput.getText();
        wordInput.clear();
        wordInput.requestFocus();
        definitionsArea.setText("");

        Consumer<Definition> user = (defintion) -> {
            definitionsArea.setText(definitionsArea.getText() + "\n" + defintion.getDefinition());
        };

        dictionaryTask = new DictionaryTask(requestedWord, user);
        new Thread(dictionaryTask).start();
    }

    @FXML
    public void deleteDefinition(ActionEvent event){
 
    }
    
}
