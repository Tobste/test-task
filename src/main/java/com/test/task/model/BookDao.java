package com.test.task.model;

import com.test.task.dao.Dao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public final class BookDao implements Dao<Book, BookQuery> {

    RowMapper<Book> ROW_MAPPER_BOOK = (ResultSet resultSet, int rowNum) -> {
        return new Book(
                new BigInteger(resultSet.getString("id")),
                resultSet.getString("title"),
                resultSet.getString("author"),
                resultSet.getString("description"));
    };

    RowMapper<BookQuery> ROW_MAPPER_BOOK_QUERY = (ResultSet resultSet, int rowNum) -> {
        return new BookQuery(
                resultSet.getInt("count"),
                resultSet.getString("author"));
    };

    private final JdbcTemplate jdbcTemplate;

    public BookDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Book> findAllSortReverseTitle() {
        return jdbcTemplate.query("SELECT * FROM BOOK ORDER BY title DESC", ROW_MAPPER_BOOK);
    }

    @Override
    public List<BookQuery> findAllGroupAuthor() {
        ArrayList<BookQuery> returnList = new ArrayList<>();
        Stream<BookQuery> demonstrationStream =
                jdbcTemplate.query("SELECT AUTHOR, COUNT(*) AS COUNT FROM BOOK GROUP BY AUTHOR",
                        ROW_MAPPER_BOOK_QUERY).stream();
        demonstrationStream.forEach(returnList::add);
        return returnList;

    }

    @Override
    public int save(Book book) {
        return jdbcTemplate.update(
                "INSERT INTO book VALUES (book_seq.NEXTVAL, ?, ?, ?)",
                book.getTitle(),
                book.getAuthor(),
                book.getDescription());
    }
}
