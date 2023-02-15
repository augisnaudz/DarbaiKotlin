package com.example.augustino.control;

import com.example.augustino.DS.Course;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class CourseListGson implements JsonSerializer<List<Course>> {
    @Override
    public JsonElement serialize(List<Course> courseList, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonArray = new JsonArray();
        Gson parser = new GsonBuilder().create();

        for (Course p : courseList) {
            jsonArray.add(parser.toJson(p));
        }
        System.out.println(jsonArray);
        return jsonArray;
    }
}
