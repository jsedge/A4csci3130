package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DetailViewActivity extends Activity {

    private EditText nameField, numberField,addressField,businessField,provinceField;
    Business receivedPersonInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Business)getIntent().getSerializableExtra("Business");
        appState = ((MyApplicationData) getApplicationContext());

        nameField = (EditText) findViewById(R.id.name);
        numberField = (EditText) findViewById(R.id.number);
        businessField = (EditText) findViewById(R.id.business);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            numberField.setText(receivedPersonInfo.number);
            businessField.setText(receivedPersonInfo.business);
            addressField.setText(receivedPersonInfo.address);
            provinceField.setText(receivedPersonInfo.province);
        }
    }

    /**
     * Updates the information for a business and pushes it to firebase
     * @param v the view where the method was triggered
     */
    public void updateContact(View v){
        String id = receivedPersonInfo.uid;

        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String number = numberField.getText().toString();
        String business = businessField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();
        Business person = new Business(personID,number, name,business,address,province);

        appState.firebaseReference.child(id).setValue(person);
        finish();
    }

    /**
     * Removes a business from the database
     * @param v the view where the method was triggered
     */
    public void eraseContact(View v)
    {
        String id = receivedPersonInfo.uid;
        appState.firebaseReference.child(id).removeValue();
        finish();
    }
}
