package com.svalero.reactive.api.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;
import com.svalero.reactive.api.model.Definition;
import com.svalero.reactive.api.service.DictionaryService;
import com.svalero.reactive.api.task.DictionaryTask;
import com.svalero.reactive.api.task.SynonymsTask;
import com.svalero.reactive.api.util.R;
import com.svalero.reactive.api.util.ZipFile;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.VBox;

public class AppController {

    @FXML
    private TextField wordInput;
    @FXML
    private Button btSearch;
    @FXML
    private Button btSynonyms;
    @FXML
    private TabPane tpDictionary;

    @FXML
    public void searchDefintions(ActionEvent event) {
        String requestedWord = wordInput.getText();
        wordInput.requestFocus();
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("results.fxml"));
        DefinitionsController definitionsController = new DefinitionsController(requestedWord);
        loader.setController(definitionsController);
        try{
            VBox dictionaryBox = loader.load();
            tpDictionary.setTabClosingPolicy(TabClosingPolicy.ALL_TABS);
            tpDictionary.getTabs().add(new Tab(requestedWord, dictionaryBox));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void searchSynonyms(ActionEvent event) {
        String requestedWord = this.wordInput.getText();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("results.fxml"));
        this.wordInput.requestFocus();
        SynonymsController synonymsController = new SynonymsController(requestedWord);
        loader.setController(synonymsController);
        try{
            VBox dictionaryBox = loader.load();
            tpDictionary.setTabClosingPolicy(TabClosingPolicy.ALL_TABS);
            tpDictionary.getTabs().add(new Tab(requestedWord, dictionaryBox));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
