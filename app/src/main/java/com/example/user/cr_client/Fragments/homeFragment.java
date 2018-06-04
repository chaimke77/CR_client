package com.example.user.cr_client.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.user.cr_client.R;
import com.example.user.cr_client.controller.MainActivity;


public class homeFragment extends Fragment  implements View.OnClickListener  {
    private TextView tel;
    private TextView mail;
    public homeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().findViewById(R.id.BranchFilter).setVisibility(View.GONE);
        getActivity().findViewById(R.id.spinnerBrunch).setVisibility(View.GONE);
        View view =  inflater.inflate( R.layout.a, container, false);
        tel =(TextView)view.findViewById(R.id.tel);
        tel.setOnClickListener(this);
        mail =(TextView)view.findViewById(R.id.mail);
        mail.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v==tel)
        {
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "0552230150", null)));
        }
        if(v==mail)
        {
            String[] TO = {"chaimke77@gmail.com"};

            Intent emailIntent = new Intent(Intent.ACTION_SEND);

            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");


            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.setType("message/rfc822");
            try {

                startActivity(Intent.createChooser(emailIntent, "Send mail..."));

            } catch (android.content.ActivityNotFoundException ex) {

            }
        }

    }

}
