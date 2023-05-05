package com.svalero.reactive.api.task;

import com.svalero.reactive.api.model.Definition;
import com.svalero.reactive.api.service.DictionaryService;

import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class SynonymsTask extends Task<Integer> {

    private String requestedWord;
    private ObservableList<String> results;
    private int counter;

    public SynonymsTask(String requestedWord, ObservableList<String> results){
        this.requestedWord = requestedWord;
        this.results = results;
        this.counter = 0;
    }

    @Override
    protected Integer call() throws Exception {
        DictionaryService dictionaryService = new DictionaryService();
        
        Consumer<String> userSynonyms = (synonym) -> {
            this.counter++;
            Thread.sleep(250);
            updateMessage(String.valueOf(this.counter) + " sinónimos descargados");
            Platform.runLater(() -> this.results.add(synonym)); ;
        };
        
        dictionaryService.getSynonyms(requestedWord).subscribe(userSynonyms);

        return null;
    }
    
}
