package com.svalero.reactive.api.task;

import com.svalero.reactive.api.model.Definition;
import com.svalero.reactive.api.service.DictionaryService;

import io.reactivex.functions.Consumer;
import javafx.concurrent.Task;

public class SynonymsTask extends Task<Integer> {

    private String requestedWord;
    private Consumer<String> user;

    public SynonymsTask(String requestedWord, Consumer<String> user){
        this.requestedWord = requestedWord;
        this.user = user;
    }

    @Override
    protected Integer call() throws Exception {
        DictionaryService dictionaryService = new DictionaryService();
        dictionaryService.getSynonyms(requestedWord).subscribe(user);

        return null;
        
    }
    
}
