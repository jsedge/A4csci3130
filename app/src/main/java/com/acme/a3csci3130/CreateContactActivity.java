package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateContactActivity extends Activity {

    private Button submitButton;
    private EditText nameField, numberField, businessField, addressField, provinceField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business_activity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        numberField = (EditText) findViewById(R.id.number);
        businessField = (EditText) findViewById(R.id.business);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);
    }

    /**
     * Builds a new Business object and puts it in the database
     * @param v the view where the method was triggered
     */
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String number = numberField.getText().toString();
        String business = businessField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();
        Business person = new Business(personID,number, name,business,address,province);

        appState.firebaseReference.child(personID).setValue(person);

        finish();

    }
}
