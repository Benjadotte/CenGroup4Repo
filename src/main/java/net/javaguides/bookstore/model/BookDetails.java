package net.javaguides.bookstore.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class BookDetails implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;

    //private String iSBN;
    private String publisher;
    private String Author;

    private Genre genre;
    private Double price;
    private String yearPublished;
    private Long copiesSold;
    private String bookCode;
    private String description;


    public BookDetails() {
    }

    public void bookDetails()
    {

    }

    //Book criteria: ISBN(Y), book name(Y), book description(Y), price(Y), author(Y), genre(Y), publisher (Y), year published(Y), copies sold(Y).
    //For Book Browsing and Sorting: Genre(Y)

    public BookDetails(String name, Long id, String description, String publisher , String Author, Genre genre, Double price , String yearPublished, Long copiesSold )
    {

        this.name = name;
        this.id = id;
        //this.iSBN = iSBN;
        this.description = description;
        this.publisher = publisher;
        this.Author = Author;
        this.genre = genre;
        this.price = price;
        this.yearPublished = yearPublished;
        this.copiesSold = copiesSold;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/*
private String iSBN;
    public int getiSBN() {
        return iSBN;
    }

    public void setiSBN(int iSBN) {
        this.iSBN = iSBN;
    }
*/
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
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
                        "ISBN: " + "iSBN(Placeholder)" + '\'' +
                        "Description: " + description + '\'' +
                        "Publisher: " + publisher + '\'' +
                        "Author: " + Author + '\'' +
                        "Genre: " + genre + '\'' +
                        "Price: " + price + '\'' +
                        "Year Published: " + yearPublished + '\'' +
                        "Copies Sold: " + copiesSold;
    }

}
