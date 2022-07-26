package net.javaguides.bookstore.model;

import java.io.Serializable;


//import javax.annotation.Generated;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

//comment
@Entity
public class BookDetails implements Serializable
{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)



    private String id;
    private String name;
    private int iSBN;
    private String publisher; 
    private String Author;
    private Role.Genre genre;
    private Double price;
    private String yearPublished;
    private Long copiesSold;
    private String bookCode;
    private String description;

    public void bookDetails() 
    {
        
    }
    
    //Book criteria: ISBN(Y), book name(Y), book description(Y), price(Y), author(Y), genre(Y), publisher (Y), year published(Y), copies sold(Y). 
    //For Book Browsing and Sorting: Genre(Y)

    public BookDetails(String name, String id, int iSBN, String description, String publisher , String Author , Role.Genre genre , Double price , String yearPublished, Long copiesSold )
    {

        this.name = name;
        this.id = id;
        this.iSBN = iSBN;
        this.description = description;
        this.publisher = publisher;
        this.Author = Author;
        this.genre = genre;
        this.price = price;
        this.yearPublished = yearPublished;
        this.copiesSold = copiesSold;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getiSBN() {
        return iSBN;
    }

    public void setiSBN(int iSBN) {
        this.iSBN = iSBN;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Role.Genre getGenre() {
        return genre;
    }

    public void setGenre(Role.Genre genre) {
        this.genre = genre;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(String yearPublished) {
        this.yearPublished = yearPublished;
    }
        
    public Long getCopiesSold() {
        return copiesSold;
    }

    public void setCopiesSold(Long copiesSold) {
        this.copiesSold = copiesSold;
    }

    public int getISBN() {
        return this.iSBN;
    }

    public void setISBN(int iSBN) {
        this.iSBN = iSBN;
    }

    public String getBookCode() {
        return this.bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }


    @Override
    public String toString()
    {
        return         
        "Book Details:" + '\'' +
        "Book Name: " + name + '\'' +
        "ID: " +id + '\'' +
        "ISBN: " + iSBN + '\'' +
        "Description: " + description + '\'' +
        "Publisher: " + publisher + '\'' +
        "Author: " + Author + '\'' +
        "Genre: " + genre + '\'' +
        "Price: " + price + '\'' +
        "Year Published: " + yearPublished + '\'' +
        "Copies Sold: " + copiesSold; 
    }

    
}

