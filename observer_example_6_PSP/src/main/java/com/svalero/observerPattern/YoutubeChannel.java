package com.svalero.observerPattern;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase YoutubeChannel
 * Es la clase Observable. Los cambios que se produzcan serán
 * observados inmediatamente por las clases Observer que se registren
 * desde ésta
 */
public class YoutubeChannel {

    private List<Video> videos;
    private List<String> posts;

    private PropertyChangeSupport support;

    public YoutubeChannel(){
        this.videos = new ArrayList<Video>();
        this.posts = new ArrayList<String>();
        this.support = new PropertyChangeSupport(this);
    }

    public void attach(PropertyChangeListener observer){
        this.support.addPropertyChangeListener(observer);
    }

    public void detach(PropertyChangeListener observer){
        this.support.removePropertyChangeListener(observer);
    }

    public void addVideo(Video video){
        this.support.firePropertyChange("videos", "", video.getTitle() + " " + video.getDescription());
        this.videos.add(video);
    }

    public void addPost(String post){
        this.support.firePropertyChange("post", "", post);
        this.posts.add(post);
    }
 
}