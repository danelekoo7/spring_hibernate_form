package task.form;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {

    @ModelAttribute("hobbies")
    public List<String> hobbiesOptions() {
        String[] a = new String[] {"Programowanie", "rower", "muzyka","kino"};
        return Arrays.asList(a);
    }

    @ModelAttribute("countryItems")
    public List<String> countryOptions() {
        String[] a = new String[] {"Polska", "USA", "Niemcy"};
        return Arrays.asList(a);
    }

    @ModelAttribute("programmingSkills")
    public List<String> programmingOptions() {
        String[] a = new String[] {"Java", "JS", "Python", "RoR"};
        return Arrays.asList(a);
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getForm(Model model){
        model.addAttribute("student", new Student());
        return "student";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public Student createPerson(@ModelAttribute Student student){
        return student;
    }

}
