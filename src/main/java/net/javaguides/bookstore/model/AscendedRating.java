package net.javaguides.bookstore.model;

import java.util.Comparator;

public class AscendedRating implements Comparator<BookRating>{
    @Override
    public int compare(BookRating v1, BookRating v2) {
        // TODO Auto-generated method stub
        return v1.getValue() - v2.getValue();
    }

}