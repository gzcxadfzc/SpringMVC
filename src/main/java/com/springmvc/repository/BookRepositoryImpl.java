package com.springmvc.repository;

import com.springmvc.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private List<Book> listOfBooks = new ArrayList<>();


    BookRepositoryImpl() {
        Book book1 = new Book("1", "defaultBook01", 1000);
        book1.setAuthor("author01");
        book1.setDescription("description of book01");
        book1.setCategory("category1");
        Book book2 = new Book("2", "defaultBook02", 2000);
        book2.setAuthor("author02");
        book2.setDescription("description of book02");
        book2.setCategory("카테고리2");
        listOfBooks.add(book1);
        listOfBooks.add(book2);
    }

    @Override
    public List<Book> getAllBookList() {
        return listOfBooks;
    }

    public List<Book> getBookListByCategory(String category) {
        List<Book> booksByCategory = new ArrayList<Book>();
        for (Book book : listOfBooks) {
            if(category.equalsIgnoreCase(book.getCategory())) {
                booksByCategory.add(book);
            }
        }
        return booksByCategory;
    }
}
