package com.svalero.reactive.api;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.svalero.reactive.api.model.Change;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class AppLocal {
    public static void main(String[] args) {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put("_account_id", "12345");
        List<Change> GithubPSP = new ArrayList<Change>(Arrays.asList(
                new Change("Bug: Las Descargas terminan bien.", "PSP AA1", "develop", user),
                new Change("refactor: Clase Descarga añadida.", "PSP AA1", "master", user),
                new Change("Bug: Clase Descarga añadida.", "PSP AA1", "master", user),
                new Change("Refactor: Notación Lambda optimizada.", "PSP AA2", "master", user)));

        // Creamos un Observable con la iteración de la lista "GithubPSP"
        Observable<Change> observableGithubPSP = Observable.fromIterable(GithubPSP);

        Consumer<Change> bugTracker = (change) -> {
            if (change.getSubject().contains("Bug")) {
                System.out.println(
                        MessageFormat.format("[BUG TRACKER] Bug detected in {0} (Branch: {1})",
                                change.getProject(), change.getBranch()));
                if (change.getBranch().equalsIgnoreCase("master")) {
                    System.out.println(MessageFormat.format(
                            "[BUG TRACKER] Critical: Sending an email to user: {0}",
                            change.getOwner().get("_account_id")));
                }
            }
        };
        Consumer<Change> refactorTracker = (change) -> {
            if (change.getSubject().contains("refactor")) {
                System.out.println(
                        MessageFormat.format(
                                "[REFACTOR TRACKER] Refactoring detected in {0} (Branch: {1})",
                                change.getProject(), change.getBranch()));
            }
        };
        observableGithubPSP.subscribe(bugTracker);          // Subscribimos el observador al observable creado
        observableGithubPSP.subscribe(refactorTracker);
    }
}

