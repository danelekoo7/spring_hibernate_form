package task.converter;

import org.springframework.core.convert.converter.Converter;
import task.book.dao.AuthorDao;
import task.book.entity.Author;

public class AuthorConverter implements Converter<String, Author> {

    private AuthorDao authorDao;

    @Override
    public Author convert(String s) {

        return authorDao.findById(Long.parseLong(s));
    }
}
