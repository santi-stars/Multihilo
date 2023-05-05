package com.svalero.reactive.api;

import com.svalero.reactive.api.model.Change;
import com.svalero.reactive.api.service.CodeService;
import io.reactivex.functions.Consumer;

import java.text.MessageFormat;

public class App {
    public static void main(String[] args) {

        CodeService githubPSP = new CodeService();

        Consumer<Change> bugTracker = (change) -> {
            if (change.getSubject().contains("Bug")) {
                System.out.println(MessageFormat.format("[BUG TRACKER] Bug detected in project {0} (Branch {1})",
                        change.getProject(), change.getBranch()));
                if (change.getBranch().equalsIgnoreCase("master")) {
                    System.out.println(MessageFormat.format("[BUG TRACKER] Critical: Sending an email to {0}",
                            change.getOwner()));
                }
            }
        };

        Consumer<Change> refactorTracker = (change) -> {
            if (change.getSubject().contains("refactor")) {
                System.out.println(MessageFormat.format("[REFACTOR TRACKER] Refactoring detected in project {0} (Branch {1})",
                        change.getProject(), change.getBranch()));
            }
        };

        githubPSP.getChanges("status:open").subscribe(bugTracker);
        githubPSP.getChanges("status:open").subscribe(refactorTracker);
    }
}
