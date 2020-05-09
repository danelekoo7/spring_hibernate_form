package task.book.controller.day1;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import task.book.dao.PersonDao;
import task.book.dao.PersonDetailsDao;
import task.book.entity.Person;
import task.book.entity.PersonDetails;

@Controller
public class PersonController {

    private final PersonDao personDao;
    private final PersonDetailsDao personDetailsDao;


    public PersonController(PersonDao personDao, PersonDetailsDao personDetailsDao) {
        this.personDao = personDao;
        this.personDetailsDao = personDetailsDao;
    }


    //    day2
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getForm(Model model) {
        model.addAttribute("person", new Person());
        return "personForm";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public Person createPerson(@ModelAttribute Person person) {
        return person;
    }


    //    day1
    @RequestMapping("/addPerson")
    @ResponseBody
    public String addPerson() {

        PersonDetails personDetails = new PersonDetails();

        personDetails.setFirstName("Daniel");
        personDetails.setLastName("jed");
        personDetails.setCity("slask");
        personDetails.setStreet("fiolkowa");
        personDetails.setStreetNumber(77);

        personDetailsDao.savePersonDetails(personDetails);

        Person person = new Person();
        person.setEmail("d@g");
        person.setLogin("daniel");
        person.setPassword("aaa");
        person.setPersonDetails(personDetails);


        personDao.savePerson(person);


        return "Id dodanej osoby to:"
                + person.getId();
    }

}
