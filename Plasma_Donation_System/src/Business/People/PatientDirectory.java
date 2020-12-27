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
public class PatientDirectory {
    
    
    private ArrayList<Patient> patientList;

    public ArrayList<Patient> getPatientList() {return patientList;}
    public void setPatientList(ArrayList<Patient> patientList) {this.patientList = patientList;}
    
    public PatientDirectory(){
        patientList = new ArrayList<Patient>();
        
    }

    public Patient addPatient()
    {
        Patient patient = new Patient();
        patientList.add(patient);
        return patient;
        
    }

    
    
}
