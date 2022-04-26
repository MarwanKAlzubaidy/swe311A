package com.example.swe311projecta;

import com.example.swe311projecta.Core.ModelFactory;
import com.example.swe311projecta.Core.ViewHandler;
import com.example.swe311projecta.Core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class PTPChat extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ModelFactory mf = new ModelFactory();
        ViewModelFactory vmf = new ViewModelFactory(mf);
        ViewHandler viewHandler = new ViewHandler(vmf);
        viewHandler.start();
    }
    @Override
    public void stop(){
        System.out.println("Stage is closing");
        // Save file
    }
}
