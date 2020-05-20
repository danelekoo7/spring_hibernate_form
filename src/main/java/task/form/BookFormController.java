package task.form;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import task.book.dao.AuthorDao;
import task.book.dao.BookDao;
import task.book.dao.PublisherDao;
import task.book.entity.Author;
import task.book.entity.Book;
import task.book.entity.Publisher;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/bookForm")
public class BookFormController {

    private final PublisherDao publisherDao;
    private final BookDao bookDao;
    private final AuthorDao authorDao;


    public BookFormController(PublisherDao publisherDao, BookDao bookDao, AuthorDao authorDao) {
        this.publisherDao = publisherDao;
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getForm(Model model) {
        model.addAttribute("book", new Book());
        return "bookForm";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String create(@Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "bookForm";
        }
        bookDao.saveBook(book);
        return "redirect:/all";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookDao.findById(id));
        return "bookEdit";
    }

    @PostMapping(value = "/edit/{id}")
    public String update(@Valid Book book, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "bookEdit";
        }

        Book bookInDB = bookDao.findById(id);
        bookInDB.setPublisher(book.getPublisher());
        bookInDB.setTitle(book.getTitle());
        bookInDB.setDescription(book.getDescription());
        bookInDB.setRating(book.getRating());
        bookDao.update(bookInDB);
        return "redirect:/all";
    }


    @GetMapping("/delete/{id}")
    public String question(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookDao.findById(id));
        return "bookDelete";
    }

    @GetMapping(value = "/deleteConfirm/{confirm}/{id}")
    @ResponseBody
    public RedirectView update(@PathVariable boolean confirm, @PathVariable Long id) {
        if (confirm) {
            bookDao.delete(bookDao.findById(id));
        }
        return new RedirectView("/all");
    }


    @ModelAttribute("publishers")
    public List<Publisher> publisherList() {
        return publisherDao.findAll();
    }

    @ModelAttribute("authors")
    public List<Author> authorList() {
        return authorDao.findAll();
    }
}