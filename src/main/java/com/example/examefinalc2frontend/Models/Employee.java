package com.example.examefinalc2frontend.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Employee {
    // Getters y setters
    @Setter
    @Getter
    private int id;
    @Setter
    @Getter
    private String firstName;
    @Setter
    @Getter
    private String lastName;
    @Setter
    @Getter
    private boolean active;
    @Setter
    @Getter
    private boolean gender;
    @Setter
    @Getter
    private Date hireDate;
    @Setter
    @Getter
    private Date terminationDate;
    @Setter
    @Getter
    private String phoneNumber;
    @Setter
    @Getter
    private String address;
    @Setter
    @Getter
    private String documentNumber;
    @Getter
    private int documentTypeId;
    @Getter
    @Setter
    private Date createdAt;
    @Setter
    private User user;
    private Area area;
    private int pensionId;
    private Boolean hasLoan;

    public User getUser() { return user; }

    public void setDocumentTypeId(int documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Boolean getHasLoan() {
        return hasLoan;
    }

    public void setHasLoan(Boolean hasLoan) {
        this.hasLoan = hasLoan;
    }

    public int getPensionId() {
        return pensionId;
    }

    public void setPensionId(int pensionId) {
        this.pensionId = pensionId;
    }
}
