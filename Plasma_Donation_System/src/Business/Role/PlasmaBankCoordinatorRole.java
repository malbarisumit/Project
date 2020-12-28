/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Organization.PlasmaBankOrganization;
import Business.UserAccount.UserAccount;

import javax.swing.JPanel;
import userinterface.PlasmaBankCoordinatorRole.PlasmaBankCoordinatorWorkAreaJPanel;

/**
 *
 * @author raunak
 */
public class PlasmaBankCoordinatorRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business, Network network) {
        return new PlasmaBankCoordinatorWorkAreaJPanel(account, (PlasmaBankOrganization)organization, enterprise, business, network);
    }
    
}
