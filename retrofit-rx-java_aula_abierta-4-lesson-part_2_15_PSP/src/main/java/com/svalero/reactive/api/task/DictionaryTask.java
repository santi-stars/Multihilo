package com.svalero.reactive.api.task;

import com.svalero.reactive.api.model.Definition;
import com.svalero.reactive.api.service.DictionaryService;

import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class DictionaryTask extends Task<Integer> {

    private String requestedWord;
    private ObservableList<String> results;
    private int counter;

    public DictionaryTask(String requestedWord, ObservableList<String> results){
        this.requestedWord = requestedWord;
        this.results = results;
        this.counter = 0;
    }

    @Override
    protected Integer call() throws Exception {
        DictionaryService dictionaryService = new DictionaryService();

        Consumer<Definition> user = (defintion) -> {
            this.counter++;
            Thread.sleep(250);
            updateMessage(String.valueOf(this.counter) + " definiciones descargados");
            Platform.runLater(() -> this.results.add(defintion.getDefinition())); ;
        };
        dictionaryService.getDefintions(requestedWord).subscribe(user);

        return null;
        
    }
    
}
