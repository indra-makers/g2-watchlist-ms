package com.co.indra.coinmarketcap.watchlist.models.Response;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Notification implements Serializable {

    private String userId;
    private String typeOf;
    private String subject;
    private String message;
    private Date sentAt;

    public Notification(){}

    public Notification(String userId, String typeOf, String subject, String message, Date sentAt){
        this.userId = userId;
        this.typeOf = typeOf;
        this.subject = subject;
        this.message = message;
        this.sentAt = sentAt;
    }
    public Notification(String userId, String typeOf, String subject, String message){
        this.userId = userId;
        this.typeOf = typeOf;
        this.subject = subject;
        this.message = message;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTypeOf() {
        return typeOf;
    }

    public void setTypeOf(String typeOf) {
        this.typeOf = typeOf;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }
}
