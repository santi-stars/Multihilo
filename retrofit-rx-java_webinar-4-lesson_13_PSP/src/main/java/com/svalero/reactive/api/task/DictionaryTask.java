package com.svalero.reactive.api.task;

import com.svalero.reactive.api.model.Definition;
import com.svalero.reactive.api.service.DictionaryService;

import io.reactivex.functions.Consumer;
import javafx.concurrent.Task;

public class DictionaryTask extends Task<Integer> {

    private String requestedWord;
    private Consumer<Definition> user;

    public DictionaryTask(String requestedWord, Consumer<Definition> user){
        this.requestedWord = requestedWord;
        this.user = user;
    }

    @Override
    protected Integer call() throws Exception {
        DictionaryService dictionaryService = new DictionaryService();
        dictionaryService.getDefintions(requestedWord).subscribe(user);

        return null;
        
    }
    
}
