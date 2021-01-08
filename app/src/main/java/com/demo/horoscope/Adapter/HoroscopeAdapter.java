package com.demo.horoscope.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.demo.horoscope.Models.HoroscopeModel;
import com.demo.horoscope.R;

public class HoroscopeAdapter extends RecyclerView.Adapter<HoroscopeAdapter.HoroscopeViewHolder> {

    private List<HoroscopeModel> models;
    private onItemClickListener listener;

    public HoroscopeAdapter(List<HoroscopeModel> models){
        this.models = models;
    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public HoroscopeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item,parent,false);
        return new HoroscopeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoroscopeViewHolder holder, final int position) {
        holder.imageView.setImageDrawable(models.get(position).drawable);
        holder.textView.setText(models.get(position).title);
        holder.relativeLayout.setBackgroundColor(models.get(position).color);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(models.get(position).title);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class HoroscopeViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        RelativeLayout relativeLayout;

        public HoroscopeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.holderImg);
            textView = itemView.findViewById(R.id.holderTitle);
            relativeLayout = itemView.findViewById(R.id.itemContainer);
        }
    }
    public interface onItemClickListener{
        void onClick(String sign);
    }
}
