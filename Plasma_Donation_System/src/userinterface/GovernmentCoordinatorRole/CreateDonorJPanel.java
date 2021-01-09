/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.GovernmentCoordinatorRole;

import Business.DB4OUtil.DB4OUtil;
import Business.EcoSystem;
import Business.People.Donor;
import Business.People.DonorDirectory;
import HomePages.TableFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author Akshay
 */
public class CreateDonorJPanel extends javax.swing.JPanel {

    /**
     * Creates new form DonorListJPanel
     */
    private  EcoSystem system;
    private DB4OUtil dB4OUtil = DB4OUtil.getInstance();
    
    private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        //InputStream in = Google.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
      // InputStream in =  new FileInputStream("C:\\Users\\adwai\\Documents\\NetBeansProjects\\google\\credentials.json");
        InputStream in =  new FileInputStream("credentials.json");
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

    }
    
    public CreateDonorJPanel(EcoSystem system) throws GeneralSecurityException, IOException {
        initComponents();
        this.system = system;


        tblGoogleSheet.getTableHeader().setDefaultRenderer(new TableFormat());
        donorTable.getTableHeader().setDefaultRenderer(new TableFormat());
        populateTable();   
        populateGoogleSheetTable();
         
    }
     
    private void populateTable(){
        DefaultTableModel dtm = (DefaultTableModel) donorTable.getModel();
        
        dtm.setRowCount(0);
        
         for(Donor donor: system.getDonorDirectory().getDonorList()){            
            Object row[] = new Object[2];
            row[0]= donor;
            row[1]=donor.getName();
           // row[2]=donor.getContact();
              
            dtm.addRow(row);
        }        
    }

    
    private void populateGoogleSheetTable() throws GeneralSecurityException, IOException{
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        final String spreadsheetId = "1YulpIxHOwFfDMM57n7hbxZueFSsw-q414bD5tzNMUv0";
        final String range = "Form Responses 2!A2:S";
        //final String range = "Form Responses 1!A2:E";
        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
        } else {
            
            DefaultTableModel dtm = (DefaultTableModel) tblGoogleSheet.getModel();
        
            dtm.setRowCount(0);
            
            for (List row : values) {
                // Print columns A and E, which correspond to indices 0 and 4.
              //  System.out.printf("%s, %s\n", row.get(0), row.get(4));
          //    System.out.printf("%s, %s\n", row.get(0), row.get(1));
              System.out.println(row.get(1));
              System.out.println(row.get(3));
              Object row1[] = new Object[4];
            
            row1[1]=row.get(1);
            row1[2]=row.get(2);
            row1[0]= row.get(3);
            row1[3]=row.get(7);
            dtm.addRow(row1);              
            }
        }
    
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblGoogleSheet = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        contactTextField = new javax.swing.JTextField();
        buttonCreate = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        donorTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        uidTextField = new javax.swing.JTextField();
        bloodGroupTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(208, 93, 2));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblGoogleSheet.setBackground(new java.awt.Color(0, 0, 0));
        tblGoogleSheet.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        tblGoogleSheet.setForeground(new java.awt.Color(255, 255, 255));
        tblGoogleSheet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UID", "Name", "Contact", "Blood Group"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGoogleSheet.setFocusable(false);
        tblGoogleSheet.setGridColor(new java.awt.Color(0, 0, 0));
        tblGoogleSheet.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblGoogleSheet.setRowHeight(30);
        tblGoogleSheet.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tblGoogleSheet);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 1200, 210));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("List of people who wish to help as a Donor");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 760, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Name");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 450, 70, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contact");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 500, -1, 30));

        nameTextField.setBackground(new java.awt.Color(0, 0, 0));
        nameTextField.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        nameTextField.setForeground(new java.awt.Color(255, 255, 255));
        nameTextField.setEnabled(false);
        add(nameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 450, 212, -1));

        contactTextField.setBackground(new java.awt.Color(0, 0, 0));
        contactTextField.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        contactTextField.setForeground(new java.awt.Color(255, 255, 255));
        contactTextField.setEnabled(false);
        add(contactTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 500, 212, -1));

        buttonCreate.setBackground(new java.awt.Color(31, 31, 31));
        buttonCreate.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        buttonCreate.setForeground(new java.awt.Color(255, 255, 255));
        buttonCreate.setText("Create Donor");
        buttonCreate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonCreate.setEnabled(false);
        buttonCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCreateActionPerformed(evt);
            }
        });
        add(buttonCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 550, 150, 40));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Create Donor Profile");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 390, -1, -1));

        donorTable.setBackground(new java.awt.Color(0, 0, 0));
        donorTable.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        donorTable.setForeground(new java.awt.Color(255, 255, 255));
        donorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UID", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        donorTable.setGridColor(new java.awt.Color(0, 0, 0));
        donorTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        donorTable.setRowHeight(30);
        donorTable.setShowVerticalLines(false);
        jScrollPane2.setViewportView(donorTable);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 600, 1180, 220));

        jPanel3.setBackground(new java.awt.Color(31, 31, 31));
        jPanel3.setPreferredSize(new java.awt.Dimension(926, 70));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Create Donor Profile");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 1576, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, -1));

        jButton2.setBackground(new java.awt.Color(31, 31, 31));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("See Details");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 130, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("UID");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 450, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Blood Group");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 500, -1, 30));

        uidTextField.setBackground(new java.awt.Color(0, 0, 0));
        uidTextField.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        uidTextField.setForeground(new java.awt.Color(255, 255, 255));
        uidTextField.setEnabled(false);
        add(uidTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 450, 230, -1));

        bloodGroupTextField.setBackground(new java.awt.Color(0, 0, 0));
        bloodGroupTextField.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        bloodGroupTextField.setForeground(new java.awt.Color(255, 255, 255));
        bloodGroupTextField.setEnabled(false);
        add(bloodGroupTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 500, 230, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/blood-donor-card.png"))); // NOI18N
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 440, 130, 90));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCreateActionPerformed
        // TODO add your handling code here:
         
        Donor donor = system.getDonorDirectory().addDonor();  
        donor.setName(nameTextField.getText());
        donor.setDonorID(uidTextField.getText());
        
        System.out.println(contactTextField.getText());  
        donor.setContact((int) Double.parseDouble(contactTextField.getText()));  
        donor.setBloodGroup(bloodGroupTextField.getText());
        donor.setStatus("Government Approved");

        dB4OUtil.storeSystem(system);
        populateTable();
         JOptionPane.showMessageDialog(null, new JLabel(  "<html><h2><I>A new<font color='green'> Donor added! </I></font></h2></html>"));
           
        //JOptionPane.showMessageDialog(null,"new Donor added!");
        nameTextField.setText("");
        contactTextField.setText("");
        uidTextField.setText("");
        bloodGroupTextField.setText("");
         buttonCreate.setEnabled(false);
        

    }//GEN-LAST:event_buttonCreateActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
         int selectedRow = tblGoogleSheet.getSelectedRow();
        if(selectedRow < 0){
           // JOptionPane.showMessageDialog(null,"Please select a row from the table first to View Details!","Warning!",JOptionPane.WARNING_MESSAGE);
            
             JOptionPane.showMessageDialog(null, new JLabel(  "<html><h2><I>Please select<font color='red'> a row</font> from the<font color='green'> table!</I></font></h2></html>"), "Warning", JOptionPane.WARNING_MESSAGE);
           
        }
        else{
            
            //String.valueOf(tblGoogleSheet.getValueAt(selectedRow, 0));
        
        nameTextField.setText( String.valueOf(tblGoogleSheet.getValueAt(selectedRow, 1)));
        contactTextField.setText( String.valueOf(tblGoogleSheet.getValueAt(selectedRow, 2)));
        uidTextField.setText( String.valueOf(tblGoogleSheet.getValueAt(selectedRow, 0)));
        bloodGroupTextField.setText( String.valueOf(tblGoogleSheet.getValueAt(selectedRow, 3)));
        
        buttonCreate.setEnabled(true);
        
        }
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bloodGroupTextField;
    private javax.swing.JButton buttonCreate;
    private javax.swing.JTextField contactTextField;
    private javax.swing.JTable donorTable;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTable tblGoogleSheet;
    private javax.swing.JTextField uidTextField;
    // End of variables declaration//GEN-END:variables
}
