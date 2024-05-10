package com.example.project;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
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
            object.remove("type");
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

    public String getEachKeyAndValue(String substring) throws JSONException {

        StringBuilder resultKeyAndValue = new StringBuilder();

        for (int i = 0; i < jsonObjects.size(); i++)
        {
            JSONObject object = jsonObjects.get(i);

            List<String> objectKeysAndValues = new ArrayList<>();
            for (int j = 0; j < object.length(); j++) {
                String key = object.names().get(j).toString();
                String value = object.get(key).toString();
                objectKeysAndValues.add(key + ": " + value);
            }

            // Check if the key contains the substring
            if (Objects.equals(substring, "ShowAll"))
            {
                // Append key-value pair to the keyAndValue string
                resultKeyAndValue.append(objectKeysAndValues).append("\n");
            }
            else if (objectKeysAndValues.contains(substring))
            {
                // Append key-value pair to the keyAndValue string
                resultKeyAndValue.append(objectKeysAndValues).append("\n").append("\n");
            }
        }

        // Return the key-value pairs
        return resultKeyAndValue.toString();
    }
}