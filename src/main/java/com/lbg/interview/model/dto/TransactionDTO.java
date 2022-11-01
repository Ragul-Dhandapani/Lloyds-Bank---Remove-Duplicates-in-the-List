package com.lbg.interview.model.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class TransactionDTO {

    private long id;
    private LocalDateTime dateTime;
    private String transactionType;
    private double amount;
    private String text;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDTO that = (TransactionDTO) o;
        return Double.compare(that.amount , amount) == 0 && dateTime.equals(that.dateTime) && transactionType.equals(that.transactionType) && text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime , transactionType , amount , text);
    }
}
