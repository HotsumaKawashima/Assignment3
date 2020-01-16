package com.derrick.park.assignment3_contacts.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.derrick.park.assignment3_contacts.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final Intent intent = new Intent();
        final EditText name = (EditText)findViewById(R.id.editTextName);
        final EditText phone = (EditText)findViewById(R.id.editTextPhone);
        Button btn = (Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strName = name.getText().toString();
                String strPhone = phone.getText().toString();

                if(strName.split("\\s").length != 2) {
                    Toast.makeText(EditActivity.this, "invalid name", Toast.LENGTH_SHORT).show();
                    return;
                }

                Pattern p = Pattern.compile("^[0-9]*$");
                Matcher m = p.matcher(strPhone);



                if(strPhone.length() != 10 || !m.find()) {
                    Toast.makeText(EditActivity.this, "invalid phone", Toast.LENGTH_SHORT).show();
                    return;
                }

                intent.putExtra("name", name.getText().toString());
                intent.putExtra("phone", phone.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
