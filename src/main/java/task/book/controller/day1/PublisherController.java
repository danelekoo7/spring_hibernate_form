package task.book.controller.day1;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import task.book.dao.BookDao;
import task.book.dao.PublisherDao;
import task.book.entity.Author;
import task.book.entity.Book;
import task.book.entity.Publisher;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("publishers")
public class PublisherController {

    private final PublisherDao publisherDao;
    private final BookDao bookDao;

    public PublisherController(PublisherDao publisherDao, BookDao bookDao) {
        this.publisherDao = publisherDao;
        this.bookDao = bookDao;
    }


    @RequestMapping("/publisher/add")
    @ResponseBody
    public String hello() {
        Publisher publisher = new Publisher();
        publisher.setName("PWN");
        publisherDao.savePublisher(publisher);
        return "Id dodanego wydawnictwa to:"
                + publisher.getId();
    }


    @RequestMapping("/publisher/get/{id}")
    @ResponseBody
    public String getPublisher(@PathVariable long id) {
        Publisher publisher = publisherDao.findById(id);
        return publisher.toString();
    }

    @RequestMapping("/publisher/update/{id}/{name}")
    @ResponseBody
    public String updatePublisher(@PathVariable long id, @PathVariable String name) {
        Publisher publisher = publisherDao.findById(id);
        publisher.setName(name);
        publisherDao.update(publisher);
        return publisher.toString();
    }

    @RequestMapping("/publisher/delete/{id}")
    @ResponseBody
    public String deletePublisher(@PathVariable long id) {
        Publisher publisher = publisherDao.findById(id);
        publisherDao.delete(publisher);
        return "deleted";
    }


//    day 2

    @GetMapping("/all")
    public String showAll() {
        return "publishersAll";
    }

//    @GetMapping("/all")
//    @ResponseBody
//    public List<Publisher> showAll() {
//        return publisherDao.findAll();
//    }

    @GetMapping("/add")
    public String addPublisher(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "addPublisher";
    }


    @PostMapping("/add")
    public String savePublisher(@Valid Publisher publisher, BindingResult result) {
        if (result.hasErrors()) {
            return "addPublisher";
        }
        publisherDao.savePublisher(publisher);
        return "redirect:/publishers/all";
    }


    @GetMapping("/form/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("publisher", publisherDao.findById(id));
        return "publisherEdit";
    }

    @PostMapping(value = "/form/edit/{id}")
    public String update(@Valid Publisher publisher, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "publisherEdit";
        }
        Publisher publisherInDB = publisherDao.findById(id);
        publisherInDB.setName(publisher.getName());
        publisherInDB.setNip(publisher.getNip());
        publisherInDB.setRegon(publisher.getRegon());
        publisherDao.update(publisherInDB);
        return "redirect:/publishers/all";
    }


    @GetMapping("/form/delete/{id}")
    public String delete(@PathVariable Long id) {
        publisherDao.delete(publisherDao.findById(id));
        return "redirect:/publishers/all";
    }

    @ModelAttribute("books")
    public List<Book> findAllBookd() {
        return bookDao.findAll();
    }

    @ModelAttribute("publishers")
    public List<Publisher> findAllPublishers() {
        return publisherDao.findAll();
    }

}


