package com.example.user.cr_client.Fragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.cr_client.R;


public class FragmentB extends Fragment {
    ProgressBar progressBar ;
    TextView txtProgress;

    public FragmentB() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.b, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        txtProgress = (TextView) view.findViewById(R.id.txtProgress);
        txtProgress.setVisibility(View.GONE);

        final Button button = (Button) view.findViewById(R.id.btnFragmentB);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            public void onClick(View v) {

                new AsyncTask<Integer, Integer, String>() {

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        progressBar.setVisibility(View.VISIBLE);
                        txtProgress.setVisibility(View.VISIBLE);
                        txtProgress.setText("10");
                    }

                    @Override
                    protected String doInBackground(Integer... integers) {
                        int counter = integers[0];
                        while (counter > 0){
                            try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
                            counter--;
                            publishProgress(counter);
                        }
                        return "";
                    }

                    @Override
                    protected void onProgressUpdate(Integer... progress)
                    {
                        progressBar.setProgress(progress[0]);
                        txtProgress.setText(progress[0].toString());
                    }

                    @Override
                    protected void onPostExecute(String result)
                    {
                        progressBar.setVisibility(View.GONE);
                        txtProgress.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "finished progrsss", Toast.LENGTH_SHORT).show();
                    }


                }.execute(10);
            }
        });
        return view;
    }

}
