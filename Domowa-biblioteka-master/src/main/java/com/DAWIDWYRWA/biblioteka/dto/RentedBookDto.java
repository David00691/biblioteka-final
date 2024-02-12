package com.DAWIDWYRWA.biblioteka.dto;

import java.util.Date;

public class RentedBookDto {
    private String title;
    private String borrowerFullName;
    private Date loanDate;

    // Getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBorrowerFullName() {
        return borrowerFullName;
    }

    public void setBorrowerFullName(String borrowerFullName) {
        this.borrowerFullName = borrowerFullName;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

}
