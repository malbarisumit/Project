/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business.Network.Network;
import Business.Organization.Organization;
import Business.People.DonorDirectory;
import Business.People.DonorRequestDirectory;
import Business.People.PatientDirectory;
import Business.People.PatientRequestDirectory;

import Business.Role.Role;
import Business.Role.SystemAdminRole;
import Business.UserAccount.UserAccountDirectory;
import java.util.ArrayList;

/**
 *
 * @author MyPC1
 */
public class EcoSystem extends Organization{
    
    private static EcoSystem business;
    
    /*
private UserAccountDirectory userAccountDirectory;

    public UserAccountDirectory getUserAccountDirectory() {
        return userAccountDirectory;
    }

    public void setUserAccountDirectory(UserAccountDirectory userAccountDirectory) {
        this.userAccountDirectory = userAccountDirectory;
    }
    */
    private DonorDirectory donorDirectory;
    private PatientDirectory patientDirectory;
    private DonorRequestDirectory donorRequestDirectory;
    private PatientRequestDirectory patientRequestDirectory;
    private  UserAccountDirectory userAccountDirectory;

    public UserAccountDirectory getUserAccountDirectory() {return userAccountDirectory; }
    public void setUserAccountDirectory(UserAccountDirectory userAccountDirectory) { this.userAccountDirectory = userAccountDirectory;  }
    public DonorRequestDirectory getDonorRequestDirectory() {   return donorRequestDirectory;    }
    public void setDonorRequestDirectory(DonorRequestDirectory donorRequestDirectory) {        this.donorRequestDirectory = donorRequestDirectory;    }
    public PatientRequestDirectory getPatientRequestDirectory() {        return patientRequestDirectory;    }
    public void setPatientRequestDirectory(PatientRequestDirectory patientRequestDirectory) {        this.patientRequestDirectory = patientRequestDirectory;    }
    public PatientDirectory getPatientDirectory() {return patientDirectory;}
    public void setPatientDirectory(PatientDirectory patientDirectory) {this.patientDirectory = patientDirectory;}
    public DonorDirectory getDonorDirectory() {return donorDirectory;}
    public void setDonorDirectory(DonorDirectory donorDirectory) {this.donorDirectory = donorDirectory;}
    
    
    private ArrayList<Network> networkList;
    
    
    public static EcoSystem getInstance(){
        if(business==null){
            business=new EcoSystem();
        }
        return business;
    }
    
    public Network createAndAddNetwork(){
        Network network=new Network();
        networkList.add(network);
        return network;
    }
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList=new ArrayList<Role>();
        roleList.add(new SystemAdminRole());
        return roleList;
    }
    private EcoSystem(){
        super(null);
        networkList=new ArrayList<Network>();
        this.donorDirectory = new DonorDirectory();
        this.patientDirectory = new PatientDirectory();
        this.donorRequestDirectory = new DonorRequestDirectory();
        this.patientRequestDirectory = new PatientRequestDirectory();
        this.userAccountDirectory  = new UserAccountDirectory();
    }

    public ArrayList<Network> getNetworkList() {
        return networkList;
    }

    public void setNetworkList(ArrayList<Network> networkList) {
        this.networkList = networkList;
    }
    
    public boolean checkIfUserIsUnique(String userName){
        if(!this.getUserAccountDirectory().checkIfUsernameIsUnique(userName)){
            return false;
        }
        for(Network network:networkList){
            
        }
        return true;
    }
}
