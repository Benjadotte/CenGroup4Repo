package net.javaguides.bookstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import net.javaguides.bookstore.service.RateService;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;




public class BookRating {
/*
    String userid, String bookid, int value, String Date, String comment
 */

@Id
private String id;

@Indexed
@Field("userid")
private String userid;

@Indexed
@Field("bookid")
private String bookid;

@Field("value")
private int value;

@Field("date")
private String comment;

@Field("comment")
private String date;

public BookRating () {

}

public BookRating(String userid, String bookid, int value, String comment) {
    this.userid = userid;
    this.bookid = bookid;
    this.value = value;
    this.date = LocalDateTime.now().toString();
    this.comment = comment;
}

 public void setId() {
    this.userid = userid;
}

public String getID() {
    return userid;
}

public String getBookid() {
    return bookid;
}

public void setBookid(String bookid) {
    this.bookid = bookid;
}

public String getDate (){
    return date;
}

public void setDate (String date) {
    this.date = date;
}

public String getComment() {
    return comment;
}

public void setComment(String comment) {
    this.comment = comment;
}

public int getValue() {
    return value;
}

public void setValue(int value) {
    this.value = value;
}


    
}