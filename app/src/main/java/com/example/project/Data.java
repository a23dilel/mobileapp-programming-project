package com.example.project;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class Data {
    private Map<String, String> fields;
    private List<String> keyOrder;
    private List<String> valueOrder;

    public Data(JSONObject object) throws JSONException {
        fields = new HashMap<>();
        keyOrder = new ArrayList<>();
        valueOrder = new ArrayList<>();

        for (int j = 0; j < object.length(); j++)
        {
            String key = object.names().getString(j);
            String value = object.get(key).toString();

            fields.put(key, value);
            keyOrder.add(key);
            valueOrder.add(value);
        }
    }

    public String getKeyAndValue(String key)
    {
        return key + ": " + fields.get(key);
    }
    public String getSpecificValue(String key)
    {
        return fields.get(key);
    }
    public List<String> getAllKeysInOrder()
    {
        return keyOrder;
    }
    public List<String> getAllValuesInOrder()
    {
        return valueOrder;
    }

    @Override
    public String toString() {
        return "Data{" +
                "fields=" + fields +
                '}';
    }
}