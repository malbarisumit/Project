/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.People;

import java.util.Date;

/**
 *
 * @author Akshay
 */
public class DonorRequest {
    
    
    // Donor Attributes
    private String donorID;
    private String name;
    private Date dob;
    private int age;
    private String gender;
    private String bloodGroup;
    private Date covidDiagnosedDate;
    private Date covidCuredDate;
    private String streetAddress;
    private String city;
    private String state;
    private int zipCode;
    private int contact;
    private String emailID;
    private String status;
    private Date  lastDonationDate;
    private boolean labConfirmation;
    private boolean symptoms;
    private boolean lastDaySymptoms;
    private boolean followUpTest;
    private String imagePath;
    private byte[] dP;

    public byte[] getdP() {
        return dP;
    }

    public void setdP(byte[] dP) {
        this.dP = dP;
    }
    
    
    
    
    

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    
    
// Getter Setter Functions
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getContact() {return contact;}
    public void setContact(int contact) {this.contact = contact;}
    public String getDonorID() {        return donorID;    }
    public void setDonorID(String donorID) {        this.donorID = donorID;    }
    public Date getDob() {        return dob;    }
    public void setDob(Date dob) {        this.dob = dob;    }
    public int getAge() {        return age;    }
    public void setAge(int age) {        this.age = age;    }
    public String getGender() {        return gender;    }
    public void setGender(String gender) {        this.gender = gender;    }
    public String getBloodGroup() {        return bloodGroup;    }
    public void setBloodGroup(String bloodGroup) {        this.bloodGroup = bloodGroup;    }
    public Date getCovidDiagnosedDate() {        return covidDiagnosedDate;    }
    public void setCovidDiagnosedDate(Date covidDiagnosedDate) {        this.covidDiagnosedDate = covidDiagnosedDate;    }
    public Date getCovidCuredDate() {        return covidCuredDate;    }    
    public void setCovidCuredDate(Date covidCuredDate) {        this.covidCuredDate = covidCuredDate;    }
    public String getStreetAddress() {        return streetAddress;    }
    public void setStreetAddress(String streetAddress) {        this.streetAddress = streetAddress;    }
    public String getCity() {        return city;    }
    public void setCity(String city) {        this.city = city;    }
    public String getState() {        return state;    }
    public void setState(String state) {        this.state = state;    }
    public int getZipCode() {        return zipCode;    }
    public void setZipCode(int zipCode) {this.zipCode = zipCode;}
    public String getEmailID() {return emailID;}
    public void setEmailID(String emailID) {this.emailID = emailID;}
    public String getStatus() {return status;}
    public void setStatus(String status) {        this.status = status;}
    public Date getLastDonationDate() {        return lastDonationDate;}
    public void setLastDonationDate(Date lastDonationDate) {        this.lastDonationDate = lastDonationDate;    }
    public boolean isLabConfirmation() {        return labConfirmation;}
    public void setLabConfirmation(boolean labConfirmation) {        this.labConfirmation = labConfirmation;    }
    public boolean isSymptoms() {        return symptoms;    }
    public void setSymptoms(boolean symptoms) {        this.symptoms = symptoms;    }
    public boolean isLastDaySymptoms() {        return lastDaySymptoms;    }
    public void setLastDaySymptoms(boolean lastDaySymptoms) {        this.lastDaySymptoms = lastDaySymptoms;    }
    public boolean isFollowUpTest() {return followUpTest;}
    public void setFollowUpTest(boolean followUpTest) {        this.followUpTest = followUpTest;    }
          
    @Override
    public String toString() {
        return donorID;
    }
    
}
