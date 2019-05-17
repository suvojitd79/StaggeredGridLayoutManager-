package com.example.StaggeredGridLayoutManager;

import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.search_query)
    TextInputEditText search_query;
    @BindView(R.id.coordinatorView)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.recyclerView)
    CustomRecyclerView customRecyclerView;

    private ApiEndPoints apiEndPoints;

    private Retrofit retrofit;

    private String API_KEY, BASE_URL;

    private final String TAG = "tag";

    private CustomRecyclerViewAdapter customRecyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        API_KEY = getResources().getString(R.string.API_KEY);
        BASE_URL = getResources().getString(R.string.BASE_URL);

        TextListener textListener = new TextListener();
        search_query.addTextChangedListener(textListener);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //define the interface
        apiEndPoints
                = retrofit.create(ApiEndPoints.class);


        customRecyclerViewAdapter =
                new CustomRecyclerViewAdapter();


        customRecyclerView.setAdapter(customRecyclerViewAdapter);

      //  customRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        //customRecyclerView.setHasFixedSize(false);


       StaggeredGridLayoutManager staggeredGridLayoutManager
               = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL);

       customRecyclerView.setLayoutManager(staggeredGridLayoutManager);



    }

    class TextListener implements TextWatcher {

        //prevent the infinite loop
        boolean _ignore = false;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (_ignore)
                return;
            _ignore = true;
            String query = s.toString();
            search_query.setSelection(query.length());
            //call the api
            new Async().execute(search_query.getText().toString());

            _ignore = false;


        }

        @Override
        public void afterTextChanged(Editable s) {

        }


    }


    class Async extends AsyncTask<String, Integer, Void> {


        @Override
        protected Void doInBackground(String... voids) {

            if (!TextUtils.isEmpty(voids[0])) {

                Call<com.example.StaggeredGridLayoutManager.Response> responseCall
                        = apiEndPoints.getAll(0, voids[0].trim(), API_KEY,2000);


                responseCall.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {


                        if (response.isSuccessful()) {

                            if (response.body() != null) {

                                customRecyclerViewAdapter.setImageModels(response.body().getResults());


                            }

                        }

                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {

                        Log.d(TAG, t + "");

                    }

                });


            }

            return null;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }


    }
}
