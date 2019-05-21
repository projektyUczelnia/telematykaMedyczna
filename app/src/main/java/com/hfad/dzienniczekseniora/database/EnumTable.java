package com.hfad.dzienniczekseniora.database;

import java.util.ArrayList;
import java.util.List;


public enum EnumTable {

    GLUCOSE() {
        @Override
        public List returnTableConstValues() {
            List<String> glucose = new ArrayList<>();
            glucose.add("GLUCOSE_TABLE");
            glucose.add("GLUCOSE_DATE");
            glucose.add("GLUCOSE_TIME");
            glucose.add("GLUCOSE_VALUE");
            return glucose;
        }
    },
    TEMPERATURE() {
        @Override
        public List returnTableConstValues() {
            List<String> temperature = new ArrayList<>();
            temperature.add("TEMPERATURE_TABLE");
            temperature.add("TEMPERATURE_DATE");
            temperature.add("TEMPERATURE_TIME");
            temperature.add("TEMPERATURE_VALUE");
            return temperature;
        }
    },
    PRESSURE() {
        @Override
        public List returnTableConstValues() {
            List<String> pressure = new ArrayList<>();
            pressure.add("PRESSURE_TABLE");
            pressure.add("GLUCOSE_DATE");
            pressure.add("PRESSURE_TIME");
            pressure.add("PRESSURE_VALUE");
            return pressure;
        }
    },
    VISIT() {
        @Override
        public List returnTableConstValues() {
            List<String> weight = new ArrayList<>();
            weight.add("WEIGHT_TABLE");
            weight.add("WEIGHT_DATE");
            weight.add("WEIGHT_TIME");
            weight.add("WEIGHT_VALUE");
            return weight;
        }
    },
    WEIGHT() {
        @Override
        public List returnTableConstValues() {
            List<String> visit = new ArrayList<>();
            visit.add("VISIT_TABLE");
            visit.add("VISIT_DATE");
            visit.add("VISIT_TIME");
            visit.add("VISIT_VALUE");
            return visit;
        }
    },
    OTHER() {
        @Override
        public List returnTableConstValues() {
            List<String> others = new ArrayList<>();
            others.add("OTHER_TABLE");
            others.add("OTHER_DATE");
            others.add("OTHER_TIME");
            others.add("OTHER_VALUE");
            return others;
        }
    };

    /**
     * Method returns const values of tables
     * @return
     */
    public abstract List returnTableConstValues();


}
