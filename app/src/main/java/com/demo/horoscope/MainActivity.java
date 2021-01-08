package com.demo.horoscope;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import com.demo.horoscope.Adapter.HoroscopeAdapter;
import com.demo.horoscope.Models.HoroscopeModel;
import com.demo.horoscope.Models.HoroscopeResponseModel;
import com.demo.horoscope.ViewModel.HoroscopeViewModel;
import com.demo.horoscope.Views.HoroscopeView;
import com.demo.horoscope.Views.LoadingView;

public class MainActivity extends AppCompatActivity {

    private HoroscopeViewModel viewModel;
    private RecyclerView recyclerView;
    private List<HoroscopeModel> models;
    private RelativeLayout relativeLayout;
    private LoadingView loadingView;
    private HoroscopeView horoscopeView;
    private HoroscopeAdapter adapter;

    private boolean lock = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(HoroscopeViewModel.class);
        viewModel.init();

        relativeLayout = findViewById(R.id.container);
        loadingView = LoadingView.getInstance(this, relativeLayout);
        horoscopeView = HoroscopeView.getInstance(this, relativeLayout);
        horoscopeView.setOnClosedListener(new HoroscopeView.onClosedListener() {
            @Override
            public void close() {
                lock = false;
            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        models = new ArrayList<>();
        for (int i = 1; i < 13; i++){
            models.add(new HoroscopeModel(getResources().getString(getResources().getIdentifier("title" + i, "string", getPackageName())),
                     getResources().getDrawable(getResources().getIdentifier("ic_pic" + i, "drawable", getPackageName())),
                    getResources().getColor(getResources().getIdentifier("color" + i, "color", getPackageName()))));

        }

        adapter = new HoroscopeAdapter(models);
        adapter.setListener(new HoroscopeAdapter.onItemClickListener() {
            @Override
            public void onClick(String sign) {
                if (!lock){
                    lock = true;
                    viewModel.call(sign);
                    loadingView.show();
                }
            }
        });
        recyclerView.setAdapter(adapter);

        viewModel.getData().observe(MainActivity.this, new Observer<HoroscopeResponseModel>() {
            @Override
            public void onChanged(HoroscopeResponseModel horoscopeResponseModel) {
                loadingView.hide();
                horoscopeView.show();
                horoscopeView.setData(horoscopeResponseModel);
            }
        });

    }
}
