package task.book.controller.day4;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import task.book.dao.AuthorDao;
import task.book.dao.BookDao;
import task.book.dao.PublisherDao;
import task.book.entity.Author;
import task.book.entity.Book;
import task.book.entity.Category;
import task.book.entity.Publisher;
import task.repository.AuthorRepository;
import task.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/jpa")
public class JpaController {


    private final BookRepository bookRepository;
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final PublisherDao publisherDao;
    private final AuthorRepository authorRepository;

    public JpaController(BookRepository bookRepository, BookDao bookDao, AuthorDao authorDao, PublisherDao publisherDao, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.publisherDao = publisherDao;

        this.authorRepository = authorRepository;
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "test ok po zmianach";
    }


    @GetMapping(value = "/all")
    public String getAll(Model model) {
//        List<Book> books = bookDao.findAll();
//        List<Book> books = bookDao.findAll();
//        List<Book> books = bookRepository.findAll();
//        List<Book> books = bookRepository.findByTitle("2222222");
//
//        List<Book> byCategoryId = bookRepository.findByCategoryId(1L);
//

//        List<Book> books = bookRepository.customFindByTitle("2222222");
//
        Category category = new Category();
        category.setName("Takiej kategorii nie ma :-(");
        category.setId(1L);

//        List<Book> byCategory = bookRepository.findByCategory(category);
        List<Book> byCategory = bookRepository.customFindByCategory(category);
        List<Book> books = new ArrayList<>();

        books.addAll(byCategory);
//        books.addAll(byCategoryId);

        model.addAttribute("books", books);
        return "books";
    }


    @GetMapping("book/author")
    @ResponseBody
    public String bookAuthor() {
        Author author = authorDao.findById(2);
        return bookRepository.findAllByAuthors(author).toString();
    }


    @GetMapping("book/query")
    @ResponseBody
    public List<Book> bookQuery() {
//        zadanie 2.1
        List<Book> allWithRatingBetween3And5 = bookRepository.findAllWithRatingBetween3And5();

//        zadanie 2.2
        Publisher publisherDaoById = publisherDao.findById(1);
        List<Book> bookByPublisher = bookRepository.findBookByPublisher(publisherDaoById);


//        zadanie2.3
        Category category = new Category();
        category.setName("Takiej kategorii nie ma :-(");
        category.setId(1L);

        List<Book> bookByCategory = bookRepository.findBookByCategory(category);


        bookRepository.resetRating(3);


        return bookByCategory;
    }

    @GetMapping("author/query")
    @ResponseBody
    public List<Author> authorQuery() {

//        zadanie 3.1
        List<Author> authorByEmailLike = authorRepository.findAuthorByEmailLike("12");

//        zadanie 3.2
        List<Author> authorByPeselLike = authorRepository.findAuthorByPeselLike("22");


        return authorByPeselLike;
    }


    @GetMapping(value = "/testData")
    public void createTestData() {

        Book book = new Book();
        book.setTitle("Ksiazka z kategoria");
        book.setPages(123);
        book.setDescription("Takie tam, pisadlo");

        Category category = new Category();
        category.setName("Akcja");
        book.setCategory(category);
        book.setPublisher(publisherDao.findAll().get(0));
        bookRepository.save(book);
    }
}
