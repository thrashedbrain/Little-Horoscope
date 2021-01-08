package com.demo.horoscope.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import com.demo.horoscope.Models.HoroscopeResponseModel;
import com.demo.horoscope.Repository.HoroscopeRepo;

public class HoroscopeViewModel extends ViewModel {
    private MutableLiveData<HoroscopeResponseModel> modelLiveData;

    public void init(){
        modelLiveData = new MutableLiveData<>();
    }

    public void call(String sign){
       HoroscopeRepo.getData repo = new HoroscopeRepo.getData(sign);
       repo.setUrl("https://aztro.sameerkumar.website");
       repo.setListener(new HoroscopeRepo.onDataListener() {
           @Override
           public void returnData(String data) {
               try {
                   JSONObject jsonObject = new JSONObject(data);
                   modelLiveData.postValue(new HoroscopeResponseModel(
                           jsonObject.getString("description"),
                           jsonObject.getString("compatibility"),
                           jsonObject.getString("mood"),
                           jsonObject.getString("color"),
                           jsonObject.getString("lucky_number"),
                           jsonObject.getString("lucky_time")
                   ));
               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }
       });
       repo.execute();
    }

    public LiveData<HoroscopeResponseModel> getData(){
        return modelLiveData;
    }
}
