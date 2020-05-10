package task.book.controller.day3;


import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import task.book.entity.Author;
import task.book.entity.Book;
import task.book.entity.Publisher;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Controller
@RequestMapping("/validate")
public class ValidationController {

    private final Validator validator;

    public ValidationController(Validator validator) {
        this.validator = validator;
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String validateBook(Model model){

        Book book = new Book();
        book.setTitle("a");
        book.setRating(101);
        book.setPages(-1000);
        book.setPublisher(new Publisher());


        Publisher publisher = new Publisher();
        publisher.setNip("12");
        publisher.setRegon("11");


        Author author = new Author();
        author.setEmail("aaa");
        author.setPesel("4528152");


        Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);

        Set<ConstraintViolation<Author>> constraintViolations1 = validator.validate(author);

        Set<ConstraintViolation<Publisher>> constraintViolations2 = validator.validate(publisher);

        model.addAttribute("book", constraintViolations);
        model.addAttribute("author", constraintViolations1);
        model.addAttribute("publisher", constraintViolations2);



//        for( ConstraintViolation<Book> bookConstraintViolation : constraintViolations){
//            sb.append(bookConstraintViolation.getPropertyPath());
//            sb.append(" ");
//            sb.append(bookConstraintViolation.getMessage());
//            sb.append("<br>");
//        }

        return "validation";
    }
}