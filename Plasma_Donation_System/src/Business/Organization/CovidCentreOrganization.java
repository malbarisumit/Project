/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.CovidCentreCoordinatorRole;
import Business.Role.DoctorRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author raunak
 */
public class CovidCentreOrganization extends Organization{

    public CovidCentreOrganization() {
        super(Organization.CovidCentreType.CovidCentre.getValue());
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new CovidCentreCoordinatorRole());
        return roles;
    }
     
}