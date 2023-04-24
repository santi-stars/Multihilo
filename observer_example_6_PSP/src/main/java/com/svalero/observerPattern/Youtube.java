package com.svalero.observerPattern;

import java.util.ArrayList;
import java.util.List;

public class Youtube {
    public static void main(String[] args) {
        YoutubeChannel youtubeChannel = new YoutubeChannel(); // Observable
        List<String> topics1 = new ArrayList<String>();
        topics1.add("Música");
        User user1 = new User(topics1);
        
        youtubeChannel.attach(user1);

        youtubeChannel.addVideo(new Video("10 mejores canciones de Bach", "Música Clásica"));
        youtubeChannel.addVideo(new Video("Mejores partidas de ajedrez", "Kasparov VS Karpov"));
        youtubeChannel.addPost("Gracias comunidad. Ya somos 10.000.");

    }
}