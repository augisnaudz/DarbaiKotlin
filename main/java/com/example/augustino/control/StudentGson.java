package com.example.augustino.control;

import com.example.augustino.DS.Student;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class StudentGson implements JsonSerializer<Student> {
    @Override
    public JsonElement serialize(Student student, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject studentJson = new JsonObject();
        studentJson.addProperty("id", student.getId());
        studentJson.addProperty("login", student.getLogin());
        studentJson.addProperty("pswd", student.getPswd());
        studentJson.addProperty("dateCreated", student.getDateCreated().toString());
        studentJson.addProperty("dateModified", student.getDateModified().toString());
        studentJson.addProperty("name", student.getName());
        studentJson.addProperty("surname", student.getSurname());
        studentJson.addProperty("card", student.getCard());
        studentJson.addProperty("email", student.getEmail());
        return studentJson;
    }

}
