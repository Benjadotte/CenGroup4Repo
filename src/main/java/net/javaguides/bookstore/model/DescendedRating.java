package net.javaguides.bookstore.model;

import java.util.Comparator;

public class DescendedRating implements Comparator<BookRating>{
    @Override
    public int compare(BookRating r1, BookRating r2) {
        // TODO Auto-generated method stub
        return r2.getValue() - r1.getValue();
    }

}