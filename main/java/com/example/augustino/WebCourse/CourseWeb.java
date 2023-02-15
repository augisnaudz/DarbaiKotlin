package com.example.augustino.WebCourse;

import com.example.augustino.DS.Course;
import com.example.augustino.HibBontrol.CourseHib;
import com.example.augustino.HibBontrol.UserHib;
import com.example.augustino.control.CourseListGson;
import com.example.augustino.control.CourseeGson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Type;
import java.util.List;

@Controller
public class CourseWeb {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SystemCourse");
    CourseHib courseHib = new CourseHib(entityManagerFactory);

    @RequestMapping(value = "/course/getAll", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllProjects() {

        List<Course> allCourses = courseHib.getAllCourses();

        GsonBuilder gson = new GsonBuilder();
        Type projectList = new TypeToken<List<Course>>() {
        }.getType();
        gson.registerTypeAdapter(Course.class, new CourseeGson()).registerTypeAdapter(projectList, new CourseListGson());
        Gson parser = gson.create();
        return parser.toJson(allCourses);
    }

    @RequestMapping(value = "/course/deleteCourse/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteProject(@PathVariable(name = "id") String id) {

        courseHib.removeCourse(Integer.parseInt(id));

        Course course = courseHib.getCourseById(Integer.parseInt(id));
        if (course == null) {
            return "Success";
        } else {
            return "Not deleted";
        }

    }
}
