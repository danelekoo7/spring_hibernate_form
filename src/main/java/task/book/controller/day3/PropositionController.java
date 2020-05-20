package task.book.controller.day3;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import task.book.dao.AuthorDao;
import task.book.dao.BookDao;
import task.book.dao.PublisherDao;
import task.book.entity.Author;
import task.book.entity.Book;
import task.book.entity.Publisher;
import task.validation.ValidationPropositionGroup;

import java.util.List;

@Controller
@RequestMapping("/proposition")
public class PropositionController {

    private final PublisherDao publisherDao;
    private final BookDao bookDao;
    private final AuthorDao authorDao;

    public PropositionController(PublisherDao publisherDao, BookDao bookDao, AuthorDao authorDao) {
        this.publisherDao = publisherDao;
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getForm(Model model) {
        model.addAttribute("book", new Book());
        return "propositionForm";
    }


    @PostMapping
    public String create(@Validated({ValidationPropositionGroup.class}) Book book, BindingResult result, Model model){
//        if (result.hasErrors())

        Book newBook = new Book();
        newBook.setDescription(book.getDescription());
        newBook.setTitle(book.getTitle());
        newBook.setPublisher(book.getPublisher());
        newBook.setPages(book.getPages());
        newBook.setAuthors(book.getAuthors());
        newBook.setRating(book.getRating());
        newBook.setProposition(true);
        bookDao.saveBook(newBook);

        List<Book> books = bookDao.findAllProposition();
        model.addAttribute("books", books);

        return "propositionAll";
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

    @ModelAttribute("authors")
    public List<Author> authorList() {
        return authorDao.findAll();
    }
}
