package com.hfad.dzienniczekseniora;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.hfad.dzienniczekseniora.changeReferenceValues.BloodPressure;
import com.hfad.dzienniczekseniora.changeReferenceValues.Glucose;
import com.hfad.dzienniczekseniora.changeReferenceValues.SingkeEdditLayoutClass;

public class ChangeReferenceValues extends BaseActivity {
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
     * Class that define Adapter for list view
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
         * Method define every part of single_eddit_layout
         *
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.single_eddit_layyout, null);
            switch (position) {
                case 0:
                    SingkeEdditLayoutClass singkeEdditLayoutClass = new BloodPressure(view, position,
                            getApplicationContext());
                    break;
                case 1:
                    SingkeEdditLayoutClass singkeEdditLayoutClass2 = new Glucose(view, position,
                            getApplicationContext());
                    break;

            }
            return view;
        }

    }
}
