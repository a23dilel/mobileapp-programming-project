package com.example.project;

import android.util.Log;

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

    public Data(JSONObject object) throws JSONException {
        fields = new HashMap<>();
        keyOrder = new ArrayList<>();

        for (int j = 0; j < object.length(); j++)
        {
            String key = object.names().getString(j);
            String value = object.get(key).toString();

            Log.d("oooo", "KEY:" + key + " and value " + value);
            fields.put(key, value);
            keyOrder.add(key);
        }
    }

    public String getField(String key)
    {
        return key + ": " + fields.get(key);
    }

    public List<String> getAllKeysInOrder()
    {
        return keyOrder;
    }

    @Override
    public String toString() {
        return "Data{" +
                "fields=" + fields +
                '}';
    }
}