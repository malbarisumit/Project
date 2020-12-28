/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.GovernmentOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;

import javax.swing.JPanel;
import userinterface.GovernmentCoordinatorRole.GovernmentCoordinatorWorkAreaJPanel;

/**
 *
 * @author raunak
 */
public class GovernmentCoordinatorRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business, Network network) {
        return new GovernmentCoordinatorWorkAreaJPanel(account, (GovernmentOrganization) organization, enterprise, business, network);
    }
    
}
