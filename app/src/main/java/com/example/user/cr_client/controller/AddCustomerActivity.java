package com.example.user.cr_client.controller;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.cr_client.R;
import com.example.user.cr_client.backend.DBManagerFactory;
import com.example.user.cr_client.entities.Customer;

public class AddCustomerActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText lastName,firstName,id,phoneNumber,email,creditCard;
    private Button addCostumer;

    private void findViews() {

        addCostumer = (Button) findViewById(R.id.OK_addCostumer_btn);
        lastName=(EditText) findViewById(R.id.editLastName);
        firstName=(EditText) findViewById(R.id.editFirstName);
        id=(EditText) findViewById(R.id.editId);
        phoneNumber=(EditText) findViewById(R.id.editPhoneNumber);
        email=(EditText) findViewById(R.id.editEmail);
        creditCard=(EditText) findViewById(R.id.editCreditCard);


        addCostumer.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        findViews();
    }

    @Override
    public void onClick(View v)
    {
        if(v==addCostumer)
        {
            if (TextUtils.isEmpty(firstName.getText().toString()))
            {
            firstName.setError("Field must be full");
            firstName.setHintTextColor(Color.RED);
            }

            else
            if (TextUtils.isEmpty(lastName.getText().toString()))
            {
                lastName.setError("Field must be full");
                lastName.setHintTextColor(Color.RED);
            }
            else
            if (TextUtils.isEmpty(id.getText().toString())) {
                id.setError("Field must be full");
                id.setHintTextColor(Color.RED);}
            else
            if (TextUtils.isEmpty(id.getText().toString())) {
                phoneNumber.setError("Field must be full");
                phoneNumber.setHintTextColor(Color.RED);}
            else
            if (TextUtils.isEmpty(email.getText().toString())) {
                email.setError("Field must be full");
                email.setHintTextColor(Color.RED);}
            else
            if (TextUtils.isEmpty(creditCard.getText().toString()))
            {
                creditCard.setError("Field must be full");
                creditCard.setHintTextColor(Color.RED);
            }
            else
            {
                AddCostumer();
                sendEmail();
                Intent intent = new Intent(this, LogIn.class);
                startActivity(intent);
                finish();
            }
        }


    }

    private Customer NewCustomer()
    {
        String first_name = firstName.getText().toString();
        String last_name = lastName.getText().toString();
        String _id = id.getText().toString();
        String phone_number = phoneNumber.getText().toString();
        String _email = email.getText().toString();
        long _creditCard = Long.parseLong(creditCard.getText().toString());
        return new Customer(last_name,first_name,_id,phone_number,_email,_creditCard);
    }
    private void AddCostumer() {
        try {

            new AsyncTask<Void,Void,Boolean>(){

                @Override
                protected void onPostExecute(Boolean aLong) {
                    super.onPostExecute(aLong);
                    if(aLong==true)
                        Toast.makeText( getBaseContext(), "Costumer "+id.getText().toString()+" Added OK", Toast.LENGTH_SHORT ).show();
                    else
                        Toast.makeText(getBaseContext()," ID is Exsist  ",Toast.LENGTH_SHORT ).show();
                }

                @Override
                protected Boolean doInBackground(Void... params) {
                    return DBManagerFactory.getManager().addCustomer(NewCustomer());
                }
            }.execute();


        }
        catch (Exception e){

        }


    }
    protected void sendEmail() {


        String[] TO = { email.getText().toString()};

        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.setType("message/rfc822");
        try {

            startActivity(Intent.createChooser(emailIntent, "Send mail..."));

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(AddCustomerActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}