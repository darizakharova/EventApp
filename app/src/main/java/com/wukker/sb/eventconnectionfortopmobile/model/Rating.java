package com.wukker.sb.eventconnectionfortopmobile.model;

import java.io.Serializable;

/**
 * Created by sb on 11.11.15.
 */
public class Rating extends Base implements Serializable {
    Long userId;
    Integer rating;
    String comment;

    public long getUserId() {
        return userId;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public Rating(int rating, long userId) {
        this.rating = rating;
        this.userId = userId;
    }

    public Rating(long userId, String comment) {
        this.userId = userId;
        this.comment = comment;
    }

    public Rating(long userId, int rating, String comment) {
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }
}
