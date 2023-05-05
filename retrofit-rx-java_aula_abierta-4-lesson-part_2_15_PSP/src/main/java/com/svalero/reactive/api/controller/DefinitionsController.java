package com.svalero.reactive.api.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;
import com.svalero.reactive.api.task.DictionaryTask;
import com.svalero.reactive.api.util.ZipFile;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DefinitionsController {

    @FXML
    private Button btDelete;
    @FXML
    private Button btExport;
    @FXML
    private TextField deleteInput;
    @FXML
    private ListView<String> resultsListView;
    @FXML
    private Label lbStatus;

    private ObservableList<String> results;
    private DictionaryTask dictionaryTask;

    private String requestedWord;

    public DefinitionsController(String requestedWord){
        this.requestedWord = requestedWord;
        this.results = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize(){
        this.results.clear();
        this.resultsListView.setItems(this.results);
        this.dictionaryTask = new DictionaryTask(requestedWord, this.results);
        this.dictionaryTask.messageProperty().addListener((observableValue, oldValue, newValue) -> this.lbStatus.setText(newValue));
        new Thread(dictionaryTask).start();
    }


    @FXML
    public void deleteEntry(ActionEvent event) {
        int definitionIndex = Integer.parseInt(deleteInput.getText());
        this.results.remove(definitionIndex);
    }

    @FXML
    public void exportCSV(ActionEvent event) {
        String outputFileName = System.getProperty("user.dir") + System.getProperty("file.separator")
        + this.requestedWord + "_defintions.csv";
        
        File outputFile = new File(outputFileName);
        try {
            FileWriter writer = new FileWriter(outputFile);
            CSVWriter csvWriter = new CSVWriter(writer);
            List<String[]> data = new ArrayList<String[]>();
            for (String definition : this.results){
                data.add(new String[] {definition, String.valueOf(Math.random())});
            }
            csvWriter.writeAll(data);
            csvWriter.close();
            ZipFile.createZipFile(outputFileName);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
}
