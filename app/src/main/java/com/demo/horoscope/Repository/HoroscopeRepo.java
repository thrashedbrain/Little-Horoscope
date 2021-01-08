package com.demo.horoscope.Repository;

import android.net.Uri;
import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class HoroscopeRepo {
    public interface onDataListener{
        void returnData(String data);
    }

    public static class getData extends AsyncTask<Void, String, String> {

        onDataListener listener;
        String sign, data;

        public getData(String sign){
            this.sign = sign;
        }

        public void setListener(onDataListener listener) {
            this.listener = listener;
        }

        public void setUrl(String data){
            this.data = data;
        }

        @Override
        protected String doInBackground(Void... voids) {
            Uri uri = Uri.parse(data)
                    .buildUpon()
                    .appendQueryParameter("sign", sign)
                    .appendQueryParameter("day", "today")
                    .build();
            try {
                Document document = Jsoup.connect(uri.toString())
                        .ignoreContentType(true)
                        .ignoreHttpErrors(true)
                        .post();
                return document.body().ownText();

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            listener.returnData(s);
        }
    }
}
