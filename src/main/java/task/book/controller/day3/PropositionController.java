package task.book.controller.day3;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import task.book.dao.AuthorDao;
import task.book.dao.BookDao;
import task.book.dao.PublisherDao;
import task.book.entity.Author;
import task.book.entity.Book;
import task.book.entity.Publisher;
import task.validation.ValidationPropositionGroup;

import javax.validation.Valid;
import javax.validation.groups.Default;
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

    @GetMapping("all")
    public String showAll() {
        return "propositionAll";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getForm(Model model) {
        model.addAttribute("book", new Book());
        return "propositionForm";
    }


    @PostMapping("/add")
    public String create(@Validated({ValidationPropositionGroup.class, Default.class}) Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "propositionForm";
        }
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


        return "redirect:/proposition/all";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookDao.findById(id));
        return "propositionEdit";
    }


    @PostMapping(value = "/edit/{id}")
    public String update(@Validated({ValidationPropositionGroup.class, Default.class}) Book book, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "propositionEdit";
        }
        Book bookInDB = bookDao.findById(id);
        bookInDB.setPublisher(book.getPublisher());
        bookInDB.setTitle(book.getTitle());
        bookInDB.setDescription(book.getDescription());
        bookInDB.setRating(book.getRating());
        bookDao.update(bookInDB);

        return "redirect:/proposition/all";
    }

    @GetMapping("/delete/{id}")
    public String question(@PathVariable Long id) {
        bookDao.delete(bookDao.findById(id));
        return "redirect:/proposition/all";
    }


    @ModelAttribute("publishers")
    public List<Publisher> publisherList() {
        return publisherDao.findAll();
    }

    @ModelAttribute("authors")
    public List<Author> authorList() {
        return authorDao.findAll();
    }


    @ModelAttribute("books")
    public List<Book> bookList() {
        return bookDao.findAllProposition();
    }

}
