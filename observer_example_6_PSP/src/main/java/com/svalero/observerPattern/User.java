package com.svalero.observerPattern;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase User
 * En este caso es la clase que observa (Observer)
 * Observa los cambios que se produzcan en otra clase, a la que se conoce como
 * Observable
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements PropertyChangeListener{
    private List<String> topics;

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("videos")){
            String newInfoVideo = (String) event.getNewValue();
            for (String topic: topics){
                if (newInfoVideo.contains(topic)){
                    System.out.println("Vídeo sobre: " + topic + ". Ver después.");
                }
            }
        }else{
            System.out.println("No me interesa este evento");
        }
        
    }

}