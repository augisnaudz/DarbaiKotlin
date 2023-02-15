package com.example.augustino.control;

import com.example.augustino.DS.Company;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class CompanyGson implements JsonSerializer<Company> {
    @Override
    public JsonElement serialize(Company company, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject companyJson = new JsonObject();
        companyJson.addProperty("id", company.getId());
        companyJson.addProperty("login", company.getLogin());
        companyJson.addProperty("pswd", company.getPswd());
        companyJson.addProperty("dateCreated", company.getDateCreated().toString());
        companyJson.addProperty("dateModified", company.getDateModified().toString());
        companyJson.addProperty("groupName", company.getGroupName());
        companyJson.addProperty("groupRep", company.getGroupRep());
        companyJson.addProperty("groupEmail", company.getGroupEmail());
        companyJson.addProperty("groupAdress", company.getGroupAdress());
        return companyJson;
    }
}
