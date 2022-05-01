package com.example.swe311projecta.Core;

import com.example.swe311projecta.ViewModel.EditUserInfoViewModel;
import com.example.swe311projecta.ViewModel.StartUpViewModel;
import com.example.swe311projecta.ViewModel.UserViewModel;


public class ViewModelFactory {
    private ModelFactory mf;

    public ViewModelFactory(ModelFactory mf) {
        this.mf = mf;
    }
    
    public UserViewModel getUserViewModel() {
        return new UserViewModel(mf.getUser(), mf.getFileIO());
    }
    
    public StartUpViewModel getStartUpViewModel() {
        return new StartUpViewModel(this, mf.getFileIO(), mf.getUser());
    }
    
    public EditUserInfoViewModel getEditUserInfoViewModel() {
        return new EditUserInfoViewModel(this, mf.getFileIO(), mf.getUser());
    }
    
    
    public ModelFactory getModelFactory() {
        return mf;
    }
}
