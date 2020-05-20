package task.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import task.book.dao.BookDao;
import task.book.entity.Book;

public class BookConverter implements Converter<String, Book> {

    @Autowired
    private BookDao bookDao;

    @Override
    public Book convert(String s) {
        return bookDao.findById(Long.parseLong(s));
    }
}
