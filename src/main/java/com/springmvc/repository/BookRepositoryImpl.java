package com.springmvc.repository;

import com.springmvc.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private List<Book> listOfBooks = new ArrayList<>();


    BookRepositoryImpl() {
        Book book1 = new Book("1", "defaultBook01", 1000);
        book1.setAuthor("author01");
        book1.setDescription("description of book01");
        book1.setCategory("category1");
        book1.setPublisher("publisher1");
        Book book2 = new Book("2", "defaultBook02", 2000);
        book2.setAuthor("author02");
        book2.setDescription("description of book02");
        book2.setCategory("카테고리2");
        book2.setPublisher("publisher2");
        listOfBooks.add(book1);
        listOfBooks.add(book2);
    }

    @Override
    public List<Book> getAllBookList() {
        return listOfBooks;
    }

    @Override
    public List<Book> getBookListByCategory(String category) {
        List<Book> booksByCategory = new ArrayList<Book>();
        for (Book book : listOfBooks) {
            if (category.equalsIgnoreCase(book.getCategory())) {
                booksByCategory.add(book);
            }
        }
        return booksByCategory;
    }

    @Override
    public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
        Set<Book> booksByPublisher = new HashSet<>();
        Set<Book> booksByCategory = new HashSet<>();
        Set<String> booksByFilter = filter.keySet();

        if (booksByFilter.contains("publisher")) {
            for (String publisherName : filter.get("publisher")) {
                for (Book book : listOfBooks) {
                    if (publisherName.equalsIgnoreCase(book.getPublisher())) {
                        booksByPublisher.add(book);
                    }
                }
            }
        }

        if (booksByFilter.contains("category")) {
            for (String category : filter.get("category")) {
                booksByCategory.addAll(getBookListByCategory(category));
            }
        }

        booksByCategory.retainAll(booksByPublisher);
        return booksByCategory;
    }

    @Override
    public Book getBookByID(final String bookId) {
        return listOfBooks.stream()
                .filter(book -> book.getBookId().equals(bookId))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("도서 Id가" + bookId + "인 도서를 찾을 수 없음"));
    }
}

