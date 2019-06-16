package com.hfad.dzienniczekseniora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.hfad.dzienniczekseniora.asharedPrefereces.BMISharedPreferences;

public class InformationPerson extends BaseActivity {
    Double heightValue;
    String sex;
    CheckBox women;
    CheckBox men;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_person);
        final EditText height = findViewById(R.id.Height);
        women = findViewById(R.id.checkBoxWomen);
        men = findViewById(R.id.checkBoxMen);
        Button button = findViewById(R.id.button);
        final BMISharedPreferences bmiSharedPreferences = new BMISharedPreferences(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxChecker()) {
                    heightValue = Double.parseDouble(height.getText().toString());
                    if (women.isChecked()) {
                        sex = "women";
                    }
                    if (men.isChecked()) {
                        sex = "men";
                    }
                    bmiSharedPreferences.saveData(heightValue, sex);
                    Intent intent = new Intent(InformationPerson.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    public boolean checkBoxChecker() {
        if ((women.isChecked() && men.isChecked()) || (!women.isChecked() && !men.isChecked())) {
            Toast.makeText(this, "błędnie zaznaczona płeć", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }
}
