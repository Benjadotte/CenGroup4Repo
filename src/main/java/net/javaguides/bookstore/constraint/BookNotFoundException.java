package net.javaguides.bookstore.constraint;

public class BookNotFoundException extends RuntimeException
{

 public BookNotFoundException(String message)
 {
    super(message);
 }
    
}