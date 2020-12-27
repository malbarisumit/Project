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
public class Patient {
    
// Patient/Receiver Attributes   
    private String receiverID;
    private String name;
    private Date dob;
    private int age;
    private String gender;
    private String bloodGroup;
    private Date covidDiagnosedDate;
    //private Date covidCuredDate;
    private String streetAddress;
    private String city;
    private String state;
    private int zipCode;
    private int contact;
    private String emailID;
    private String status;
    //private Date  lastDonationDate;
    private boolean labConfirmation;
    //private boolean symptoms;
    //private boolean lastDaySymptoms;
    //private boolean followUpTest;
    private boolean legalApproval;
    private boolean emergencyStatus;
    private Date treatmentDate; // Date of plasma treatment done 
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
    public String getName() {        return name;    }
    public void setName(String name) {        this.name = name;    }
    public int getContact() {        return contact;    }
    public void setContact(int contact) {        this.contact = contact;    }
    public String getReceiverID() {return receiverID;  }
    public void setReceiverID(String receiverID) {        this.receiverID = receiverID;    }
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
    public String getStreetAddress() {        return streetAddress;    }
    public void setStreetAddress(String streetAddress) {        this.streetAddress = streetAddress;    }
    public String getCity() {        return city;    }
    public void setCity(String city) {        this.city = city;    }
    public String getState() {        return state;    }
    public void setState(String state) {        this.state = state;    }
    public int getZipCode() {        return zipCode;    }
    public void setZipCode(int zipCode) {        this.zipCode = zipCode;    }
    public String getEmailID() {        return emailID;    }
    public void setEmailID(String emailID) {        this.emailID = emailID;    }
    public String getStatus() {        return status;    }
    public void setStatus(String status) {        this.status = status;    }
    public boolean isLabConfirmation() {        return labConfirmation;    }
    public void setLabConfirmation(boolean labConfirmation) {        this.labConfirmation = labConfirmation;    }
    public boolean isLegalApproval() {        return legalApproval;    }
    public void setLegalApproval(boolean legalApproval) {        this.legalApproval = legalApproval;    }
    public boolean isEmergencyStatus() {        return emergencyStatus;    }
    public void setEmergencyStatus(boolean emergencyStatus) {        this.emergencyStatus = emergencyStatus;    }
    public Date getTreatmentDate() {        return treatmentDate;    }
    public void setTreatmentDate(Date treatmentDate) {       this.treatmentDate = treatmentDate;    }
    
    
    
    
    
    @Override
    public String toString() {
        return receiverID;
    }
    
 }
