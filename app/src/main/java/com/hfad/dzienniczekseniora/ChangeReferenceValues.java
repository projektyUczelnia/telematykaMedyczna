package com.hfad.dzienniczekseniora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeReferenceValues extends AppCompatActivity {
    /**
     * Activity that allows us to change reference values
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_reference_values);
        ListView calueToChangrList = findViewById(R.id.valueTochangeList);
        calueToChangrList.setAdapter(new CustomAdapter());

    }

    /**
     * clast that define Adapter forList view
     */
    class CustomAdapter extends BaseAdapter {
        int[] sectionName = {R.string.glucose, R.string.pressure};

        @Override
        public int getCount() {
            return sectionName.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * method define every part of single_eddit_layout
         *
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.single_eddit_layyout, null);
            Button changeValueButton = view.findViewById(R.id.changeValueButton);
            TextView lastValues = view.findViewById(R.id.lastValueTextView);
            TextView sectionName = view.findViewById(R.id.sectionName);
            final EditText updateValues = view.findViewById(R.id.updateValueEditText);
            //todo obecny przediał zamienić na obecny przedział
            lastValues.setText("obecny przedział");
            sectionName.setText(this.sectionName[position]);
            changeValueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //todo rozważam tutaj inna opcje tez ale nie wiem jeszcze jaka
                    // w zależności od pozycji bedzie sie zmieniać funconalność klaiwsza
                    switch (position) {
                        case 0:
                            //onClick dla poziomu cukry
                            Toast.makeText(getApplicationContext(), "cukier", Toast.LENGTH_LONG).show();
                            //todo metoa uaktualniajaca poziomu cukru
                            break;
                        case 1:
                            //onClick dla cisnienia
                            Toast.makeText(getApplicationContext(), "cisnienie", Toast.LENGTH_LONG).show();
                            //todo metoa uaktualniajaca cisnienie
                            break;
                    }
                }
            });
            return view;
        }
    }
}
