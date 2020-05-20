package task.book.controller.day1;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import task.book.dao.AuthorDao;
import task.book.dao.BookDao;
import task.book.entity.Author;
import task.book.entity.Book;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("authors")
public class AuthorController {

    private final AuthorDao authorDao;
    private final BookDao bookDao;

    public AuthorController(AuthorDao authorDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
    }


    @RequestMapping("/add-temp")
    @ResponseBody
    public String hello() {
        Author author = new Author();
        author.setFirstName("Dan");
        author.setLastName("Jed");
        authorDao.saveAuthor(author);
        return "Id dodanego autora to:"
                + author.getId();
    }


    @RequestMapping("/get/{id}")
    @ResponseBody
    public String getAuthor(@PathVariable long id) {
        Author author = authorDao.findById(id);
        return author.toString();
    }

    @RequestMapping("/update/{id}/{firstName}")
    @ResponseBody
    public String updateAuthor(@PathVariable long id, @PathVariable String firstName) {
        Author author = authorDao.findById(id);
        author.setFirstName(firstName);
        authorDao.update(author);
        return author.toString();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String deleteAuthor(@PathVariable long id) {
        Author author = authorDao.findById(id);
        authorDao.delete(author);
        return "deleted";
    }

    //day 2

    @GetMapping("/all")
    public String showAll() {
//        List<Author> authors = authorDao.findAll();
//        model.addAttribute("authors", authors);
        return "authorsAll";
    }

//    @GetMapping("/all")
//    @ResponseBody
//    public List<Author> showAll() {
//        List<Author> authors = authorDao.findAll();
//        return authors;
//    }

    @GetMapping("/add")
    public String addAuthor(Model model) {
        model.addAttribute("author", new Author());
        return "addAuthor";
    }


    @PostMapping("/add")
    public String saveAuthor(@Valid Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "addAuthor";
        }
        authorDao.saveAuthor(author);
        return "redirect:/authors/all";
    }

    @ModelAttribute("books")
    public List<Book> findAllBookd() {
        return bookDao.findAll();
    }

    @ModelAttribute("authors")
    public List<Author> findAllAuthors() {
        return authorDao.findAll();
    }
}
