package com.test.task.model;

import lombok.Data;

@Data
public class BookQuery {

    private Integer count;

    private String author;

    public BookQuery(Integer count, String author) {
        this.count = count;
        this.author = author;
    }
}
