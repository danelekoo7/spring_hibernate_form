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
import task.repository.PublisherRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/jpa")
public class JpaController {


    private final BookRepository bookRepository;
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final PublisherDao publisherDao;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;


    public JpaController(BookRepository bookRepository, BookDao bookDao, AuthorDao authorDao, PublisherDao publisherDao, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.publisherDao = publisherDao;

        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
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


    @GetMapping("task3")
    @ResponseBody
    public List<Book> bookAuthor() {

//        task 3.1
        Author author = authorDao.findById(2);
        List<Book> allByAuthors = bookRepository.findAllByAuthors(author);


//        task 3.2
        Publisher publisherDaoById = publisherDao.findById(1L);
        List<Book> bookByPublisher = bookRepository.findByPublisher(publisherDaoById);


//        task 3.3
        List<Book> byRatingBetween = bookRepository.findByRatingBetween(2, 6);

        return byRatingBetween;
    }

    @GetMapping("task34")
    @ResponseBody
    public Book firstBookByCategory() throws Exception {
        Category category = new Category();
        category.setName("Takiej kategorii nie ma :-(");
        category.setId(1L);
        Optional<Book> firstByCategoryOrderByTitle = bookRepository.findFirstByCategoryOrderByTitle(category);
        Book book = firstByCategoryOrderByTitle.orElseThrow(Exception::new);

        return book;
    }


    @GetMapping("task4")
    @ResponseBody
    public Publisher publisher() throws Exception {
        Optional<Publisher> firstByNip = publisherRepository.findFirstByNip("777777");
        Publisher publisher1 = firstByNip.orElseThrow(Exception::new);

        Optional<Publisher> firstByRegon = publisherRepository.findFirstByRegon("1111111111");
        Publisher publisher2 = firstByRegon.orElseThrow(Exception::new);

        return publisher2;

    }


    @GetMapping("task5")
    @ResponseBody
    public Author author() throws Exception {

        Optional<Author> firstByEmail = authorRepository.findFirstByEmail("11@11");
        Author author = firstByEmail.orElseThrow(Exception::new);


        Optional<Author> firstByPesel = authorRepository.findFirstByPesel("2224444");
        Author author1 = firstByPesel.orElseThrow(Exception::new);

        return author1;
    }

    @GetMapping("task53")
    @ResponseBody
    public List<Author> authorList() {
        List<Author> dd = authorRepository.findByLastName("dd");
        return dd;
    }

    @GetMapping("book/query")
    @ResponseBody
    public List<Book> bookQuery() {
//        zadanie 2.1
        List<Book> allWithRatingBetween3And5 = bookRepository.findAllWithRatingBetween3And5();

//        zadanie 2.2
        Publisher publisherDaoById = publisherDao.findById(1L);
        List<Book> bookByPublisher = bookRepository.customizedFindBookByPublisher(publisherDaoById);


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
