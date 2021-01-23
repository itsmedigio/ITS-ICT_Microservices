package org.digiovanni.borrow.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Borrow {

    @Id
    private String borrowId;

    private String customerId;

    private String bookId;

    private String notifyToPhoneNr;

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getNotifyToPhoneNr() {
        return notifyToPhoneNr;
    }

    public void setNotifyToPhoneNr(String notifyToPhoneNr) {
        this.notifyToPhoneNr = notifyToPhoneNr;
    }
}
