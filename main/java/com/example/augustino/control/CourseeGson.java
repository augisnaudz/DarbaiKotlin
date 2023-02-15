package com.example.augustino.control;

import com.example.augustino.DS.Course;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class CourseeGson implements JsonSerializer<Course> {
    @Override
    public JsonElement serialize(Course course, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject personJson = new JsonObject();
        personJson.addProperty("id", course.getId());
        personJson.addProperty("title", course.getTitle());
        personJson.addProperty("descCourse", course.getDesc());
        personJson.addProperty("dateCreated", course.getDateCreated().toString());
        return personJson;
    }
}
