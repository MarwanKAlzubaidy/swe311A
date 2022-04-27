package com.example.swe311projecta.Core;

import com.example.swe311projecta.View.StartUp.StartUpViewModel;
import com.example.swe311projecta.View.UserViewModel;
import com.example.swe311projecta.model.FileIO;

public class ViewModelFactory {
    private ModelFactory mf;

    public ViewModelFactory(ModelFactory mf) {
        this.mf = mf;
    }
    public UserViewModel getUserViewModel(){
        return new UserViewModel(mf.getUser(),mf.getFileIO());
    }
    public StartUpViewModel getStartUpViewModel(){return new StartUpViewModel(this,mf.getFileIO(),mf.getUser());
    }

    public ModelFactory getMf() {
        return mf;
    }
}
