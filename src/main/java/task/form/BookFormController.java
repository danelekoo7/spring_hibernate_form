package task.form;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import task.book.dao.BookDao;
import task.book.dao.PublisherDao;
import task.book.entity.Book;
import task.book.entity.Publisher;

import java.util.List;

@Controller
@RequestMapping("/bookForm")
public class BookFormController {

    private final PublisherDao publisherDao;
    private final BookDao bookDao;


    public BookFormController(PublisherDao publisherDao, BookDao bookDao) {
        this.publisherDao = publisherDao;
        this.bookDao = bookDao;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getForm(Model model) {
        model.addAttribute("book", new Book());
        return "bookForm";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public RedirectView create(@ModelAttribute Book book) {
        bookDao.saveBook(book);
        return new RedirectView("/all");
    }


    @PostMapping(value = "/edit/{id}")
    public RedirectView update(@ModelAttribute Book bookEdit, @PathVariable Long id) {
        Book bookInDB = bookDao.findById(id);
        bookInDB.setPublisher(bookEdit.getPublisher());
        bookInDB.setTitle(bookEdit.getTitle());
        bookInDB.setDescription(bookEdit.getDescription());
        bookInDB.setRating(bookEdit.getRating());
        bookDao.update(bookInDB);
        return new RedirectView("/all");
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("bookEdit", bookDao.findById(id));
        return "bookEdit";
    }

    @GetMapping("/delete/{id}")
    public String question(@PathVariable Long id, Model model) {
        model.addAttribute("oneBook", bookDao.findById(id));
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
}