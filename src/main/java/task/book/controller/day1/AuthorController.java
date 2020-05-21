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
        return "authorsAll";
    }

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


    @GetMapping("/form/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorDao.findById(id));
        return "authorEdit";
    }

    @PostMapping(value = "/form/edit/{id}")
    public String update(@Valid Author author, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "authorEdit";
        }
        Author authorInDB = authorDao.findById(id);
        authorInDB.setPesel(author.getPesel());
        authorInDB.setEmail(author.getEmail());
        authorInDB.setFirstName(author.getFirstName());
        authorInDB.setLastName(author.getLastName());
        authorInDB.setYearOfBirth(author.getYearOfBirth());
        authorDao.update(authorInDB);
        return "redirect:/authors/all";
    }


    @GetMapping("/form/delete/{id}")
    public String question(@PathVariable Long id) {
        authorDao.delete(authorDao.findById(id));
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
