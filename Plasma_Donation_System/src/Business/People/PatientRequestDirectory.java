/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.People;

import java.util.ArrayList;

/**
 *
 * @author Akshay
 */
public class PatientRequestDirectory {
    
    
    private ArrayList<PatientRequest> patientRequestList;

    public ArrayList<PatientRequest> getPatientRequestList() {return patientRequestList;}
    public void setPatientRequestList(ArrayList<PatientRequest> patientRequestList) {this.patientRequestList = patientRequestList;}
    
    public PatientRequestDirectory(){
        patientRequestList = new ArrayList<PatientRequest>();
    }
    
    public PatientRequest addPatientRequest()
    {
        PatientRequest patientRequest = new PatientRequest();
        patientRequestList.add(patientRequest);
        return patientRequest;
    }
    
    public DonorRequest removePatientRequest(PatientRequest p)
    {
        //DonorRequest donorRequest = new DonorRequest();
        //this.donor = donor
        patientRequestList.remove(p);
        //return donorRequest;
        return null;
    }
    
    
    
    
}
