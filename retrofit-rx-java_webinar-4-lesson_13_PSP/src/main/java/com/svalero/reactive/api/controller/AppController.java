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

import io.reactivex.functions.Consumer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AppController {

    @FXML
    private TextField wordInput;
    @FXML
    private Button btSearch;
    @FXML
    private Button btDelete;
    @FXML
    private Button btExport;
    @FXML
    private TextField deleteInput;
    @FXML
    private TextArea definitionsArea;

    private DictionaryTask dictionaryTask;
    private SynonymsTask synonymsTask;
    private List<String> definitions;

    private String lastSearch;

    @FXML
    public void exportCSV(ActionEvent event) {
        File outputFile = new File(System.getProperty("user.dir") + System.getProperty("file.separator")
                + this.lastSearch + "_defintions.csv");
        
        try {
            FileWriter writer = new FileWriter(outputFile);
            CSVWriter csvWriter = new CSVWriter(writer);
            List<String[]> data = new ArrayList<String[]>();
            for (String definition : this.definitions){
                data.add(new String[] {definition, String.valueOf(Math.random())});
            }
            csvWriter.writeAll(data);
            csvWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void searchDefintions(ActionEvent event) {
        this.definitions = new ArrayList<String>();
        String requestedWord = wordInput.getText();
        this.lastSearch = requestedWord;
        wordInput.clear();
        wordInput.requestFocus();
        definitionsArea.setText("");

        Consumer<Definition> user = (defintion) -> {
            String previousText;
            previousText = definitionsArea.getText() + "\n";
            Thread.sleep(10);
            this.definitionsArea.setText(previousText + defintion.getDefinition());
            this.definitions.add(defintion.getDefinition());
        };

        this.dictionaryTask = new DictionaryTask(requestedWord, user);
        new Thread(dictionaryTask).start();
    }

    @FXML
    public void deleteDefinition(ActionEvent event) {
        int definitionIndex = Integer.parseInt(deleteInput.getText());
        this.definitions.remove(definitionIndex);
        this.definitionsArea.setText("");
        for (String defintion : this.definitions) {
            this.definitionsArea.setText(definitionsArea.getText() + "\n" + defintion);
        }
    }

    @FXML
    public void searchSynonyms(ActionEvent event) {
        String requestedWord = this.wordInput.getText();
        this.wordInput.clear();
        this.wordInput.requestFocus();
        this.definitionsArea.setText("");

        Consumer<String> userSynonyms = (synonym) -> {
            this.definitionsArea.setText(this.definitionsArea.getText() + "\n" + synonym);
        };

        this.synonymsTask = new SynonymsTask(requestedWord, userSynonyms);
        new Thread(this.synonymsTask).start();

    }

}
