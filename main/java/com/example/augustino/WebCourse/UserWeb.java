package com.example.augustino.WebCourse;

import com.example.augustino.DS.Company;
import com.example.augustino.DS.Student;
import com.example.augustino.DS.User;
import com.example.augustino.HibBontrol.UserHib;
import com.example.augustino.control.CompanyGson;
import com.example.augustino.control.LocalDateGson;
import com.example.augustino.control.StudentGson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Properties;

@Controller
public class UserWeb {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SystemCourse");
    UserHib userHib = new UserHib(entityManagerFactory);

    @RequestMapping(value = "/users/userLogin", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String login(@RequestBody String temp){

        Gson parser = new Gson();
        Properties data = parser.fromJson(temp, Properties.class);
        GsonBuilder gson = new GsonBuilder();
        Student student = null;
        Company company = null;

        if (data.getProperty("type").equals("S")) {
            student = (Student) userHib.getUserByLoginData(data.getProperty("login"), data.getProperty("pswd"));
            gson.registerTypeAdapter(Student.class, new StudentGson()).registerTypeAdapter(LocalDate.class, new LocalDateGson());
        } else if (data.getProperty("type").equals("C")) {
            company = (Company) userHib.getUserByLoginData(data.getProperty("login"), data.getProperty("pswd"));
            gson.registerTypeAdapter(Company.class, new CompanyGson()).registerTypeAdapter(LocalDate.class, new LocalDateGson());
        }

        if (student == null && company == null) {
            return "Wrong credentials or no such user";
        }

        Gson builder = gson.create();
        return student != null ? builder.toJson(student) : builder.toJson(company);
    }

    @RequestMapping(value = "/users/createStudent", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String createStudent(@RequestBody String temp){
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(LocalDate.class, new LocalDateGson()).registerTypeAdapter(User.class, new StudentGson());
        Gson parser = gson.create();
        Properties data = parser.fromJson(temp, Properties.class);
        userHib.createUser(new Student(data.getProperty("login"), data.getProperty("pswd"), data.getProperty("name"), data.getProperty("surname"), data.getProperty("email"), data.getProperty("card")));
        User user = userHib.getUserByLoginData(data.getProperty("login"), data.getProperty("pswd"));
        if (user == null) {
            return "User not created";
        }
        return parser.toJson(user);
    }

    @RequestMapping(value = "/users/createCompany", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String createCompany(@RequestBody String temp){
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(LocalDate.class, new LocalDateGson()).registerTypeAdapter(User.class, new CompanyGson());
        Gson parser = gson.create();
        Properties data = parser.fromJson(temp, Properties.class);
        userHib.createUser(new Company(data.getProperty("login"), data.getProperty("pswd"), data.getProperty("groupName"), data.getProperty("groupRep"), data.getProperty("groupEmail"), data.getProperty("groupAdress")));
        User user = userHib.getUserByLoginData(data.getProperty("login"), data.getProperty("pswd"));
        if (user == null) {
            return "User not created";
        }
        return parser.toJson(user);
    }

    @RequestMapping(value = "/users/getUser/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getUser(@PathVariable(name = "id") String id) {

        User user = userHib.getUserById(Integer.parseInt(id));
        GsonBuilder gson = new GsonBuilder();
        if (user.getClass() == Student.class) {
            gson.registerTypeAdapter(LocalDate.class, new LocalDateGson()).registerTypeAdapter(Student.class, new StudentGson());
        } else if (user.getClass() == Company.class)
            gson.registerTypeAdapter(LocalDate.class, new LocalDateGson()).registerTypeAdapter(Company.class, new CompanyGson());
        Gson parser = gson.create();

        if (user == null) {
            return "No user by given ID";
        }
        return parser.toJson(user);
    }


}
