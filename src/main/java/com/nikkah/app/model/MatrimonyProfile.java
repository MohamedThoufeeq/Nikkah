package com.nikkah.app.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="MatrimonyProfile")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class MatrimonyProfile {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;
	
    @Column(name = "Name")
    private String name;
    @Column(name = "DOB")
    private String dateOfBirth;
    @Column(name = "Gender")
    private String gender;
    @Column(name = "Marital_Status")
    private String maritalStatus;
    @Column(name = "Language")
    private String language;
    @Column(name = "City")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "Education")
    private String education;
    @Column(name = "Occupation")
    private String occupation;
    @Column(name = "Income")
    private String income;
    @Column(name = "About_Me")
    private String aboutMe;
    
    @Column(name = "Mobile")
    private String mobileNumber;
    
    @Column(name = "Email")
    private String email;
    @Column(name = "Password")
    private String password;
    
    @Column(name = "Age")
    private String age;
    
    @Lob
    @Column(name = "Image", columnDefinition = "bytea")
//    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] image;
    
    public String getAge() {
        calculateAge();
        return age;
    }

    @PostLoad
    @PrePersist
    private void calculateAge() {
        if (dateOfBirth != null && !dateOfBirth.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthDate = LocalDate.parse(dateOfBirth, formatter);
            LocalDate currentDate = LocalDate.now();

            Period period = Period.between(birthDate, currentDate);
            int years = period.getYears();
            int months = period.getMonths();
            int days = period.getDays();

            age = years + " years, " + months + " months, " + days + " days";
        } else {
            age = "N/A";
        }
    }
}
