package com.example.project;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("WeakerAccess")
public class Data {
    private JSONArray jsonArray;
    private List<JSONObject> jsonObjects = new ArrayList<>();

    public Data(String json) throws JSONException {

        // Take JSON-data and turn into an array
        jsonArray = new JSONArray(json);

        // Each object get string of specific name and add each name on constructor from data.
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject object = jsonArray.getJSONObject(i);
            jsonObjects.add(object);
        }
    }

    public JSONArray getJSONArray()
    {
        return jsonArray;
    }

    public List<JSONObject> getJSONObjects()
    {
        return jsonObjects;
    }

    public String getEachKeyAndValue(int indexOfObject, String substring) throws JSONException {

        StringBuilder keyAndValue = new StringBuilder();

        if (indexOfObject >= 0 && indexOfObject < jsonObjects.size())
        {
            JSONObject object = jsonObjects.get(indexOfObject);

            for (int i = 0; i < object.length(); i++)
            {
                String key = object.names().get(i).toString().trim().replace("\n", "");
                String value = object.get(key).toString().trim().replace("\n", "");

                // Check if the key contains the substring
                if (Objects.equals(substring, "ShowAll") || value.contains(substring))
                {
                    // Append key-value pair to the keyAndValue string
                    keyAndValue.append(key).append(": ").append(value).append("\n");
                }
            }
        }

        // Return the key-value pairs
        return keyAndValue.toString();
    }
}