/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.GovernmentCoordinatorRole;

import Business.DB4OUtil.DB4OUtil;
import Business.EcoSystem;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.People.DonorRequest;
import Business.People.DonorRequestDirectory;
import HomePages.DonorRecieverFramePage;
import HomePages.LoginPage;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.SwingUtilities.getWindowAncestor;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Akshay
 */
public class NewDonorJPanel extends javax.swing.JPanel {

    /**
     * Creates new form NewDonorJPanel
     */
    
    private boolean emailValid;
    private EcoSystem system;
    private DB4OUtil dB4OUtil = DB4OUtil.getInstance();
    private byte[] tempdP;
    private ButtonGroup radioGroup1;
    private ButtonGroup radioGroup2;
    private ButtonGroup radioGroup3;
    private ButtonGroup radioGroup4;
            
    public NewDonorJPanel(EcoSystem system)
    {
        initComponents();
        this.system = system;
        this.radioGroup1 = new ButtonGroup();
        this.radioGroup2 = new ButtonGroup();
        this.radioGroup3 = new ButtonGroup();
        this.radioGroup4 = new ButtonGroup();
       
        
        
        curedDateValidateMessage.setVisible(false);
        emailSuccessLabel.setVisible(false);
        emailValidateMessage.setVisible(false);
        
       // ButtonGroup radioGroup1 = new ButtonGroup();
        radioGroup1.add(buttonYesGroup1);
        radioGroup1.add(buttonNoGroup1);
  
      //  ButtonGroup radioGroup2 = new ButtonGroup();
        radioGroup2.add(buttonYesGroup2);
        radioGroup2.add(buttonNoGroup2);
        
      //  ButtonGroup radioGroup3 = new ButtonGroup();
        radioGroup3.add(buttonYesGroup3);
        radioGroup3.add(buttonNoGroup3);
        
       // ButtonGroup radioGroup4 = new ButtonGroup();
        radioGroup4.add(buttonYesGroup4);
        radioGroup4.add(buttonNoGroup4);
        
        populateBloodGroupComboBox();
        populateGenderComboBox();
        populateStateComboBox();
    
    
    }

  private void populateBloodGroupComboBox(){
           
           bloodGroupJComboBox.addItem("O+");
           bloodGroupJComboBox.addItem("AB+");
           bloodGroupJComboBox.addItem("O-");
           bloodGroupJComboBox.addItem("AB-");
           bloodGroupJComboBox.addItem("A+");
           bloodGroupJComboBox.addItem("B+");
           bloodGroupJComboBox.addItem("A-");
           bloodGroupJComboBox.addItem("B-");
           
        }
  
  private void populateGenderComboBox(){
           genderJComboBox.addItem("Male");
           genderJComboBox.addItem("Female");
           genderJComboBox.addItem("Other");
        }
  
  private void populateStateComboBox(){
           stateJComboBox.addItem("California");
           stateJComboBox.addItem("Massachusetts");
           stateJComboBox.addItem("Georgia");
           stateJComboBox.addItem("Arizona");
           stateJComboBox.addItem("Texas");
           stateJComboBox.addItem("Florida");
           stateJComboBox.addItem("Illinois");
      }
    public static boolean phoneNumberValidator(String contact) {
        Pattern pattern;
        Matcher matcher;
        String PHONE_PATTERN = "^[0-9]{10}$";
        pattern = Pattern.compile(PHONE_PATTERN);
        matcher = pattern.matcher(contact);
        return matcher.matches();
        }
    public static boolean zipCodeValidator(String zip) {
        Pattern pattern;
        Matcher matcher;
        String zip_pattern = "^[0-9]{5}$";
        pattern = Pattern.compile(zip_pattern);
        matcher = pattern.matcher(zip);
        return matcher.matches();
        } 
    public static boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
        }
    public boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
        }

    private void disableAllButton(){
    
        uidText.setEnabled(false);
        nameText.setEnabled(false);
        dobDateField.setEnabled(false);
        ageText.setEnabled(false);
        emailText.setEnabled(false);
        contactText.setEnabled(false);
        genderJComboBox.setEnabled(false);
        bloodGroupJComboBox.setEnabled(false);
        diagnosedDateField.setEnabled(false);
        curedDateField.setEnabled(false);
        lastDonationDateField.setEnabled(false);
        streetText.setEnabled(false);
        cityText.setEnabled(false);
        stateJComboBox.setEnabled(false);
        zipText.setEnabled(false);
        buttonYesGroup1.setEnabled(false);
        buttonNoGroup1.setEnabled(false);
        buttonYesGroup2.setEnabled(false);
        buttonNoGroup2.setEnabled(false);
        buttonYesGroup3.setEnabled(false);
        buttonNoGroup3.setEnabled(false);
        buttonYesGroup4.setEnabled(false);
        buttonNoGroup4.setEnabled(false);
        addPhotoButton.setEnabled(false);
    
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        uidText = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ageText = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        streetText = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cityText = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        zipText = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        contactText = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        emailText = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        addPhotoButton = new javax.swing.JButton();
        buttonYesGroup2 = new javax.swing.JRadioButton();
        buttonNoGroup2 = new javax.swing.JRadioButton();
        buttonNoGroup1 = new javax.swing.JRadioButton();
        buttonYesGroup1 = new javax.swing.JRadioButton();
        buttonNoGroup3 = new javax.swing.JRadioButton();
        buttonYesGroup3 = new javax.swing.JRadioButton();
        buttonNoGroup4 = new javax.swing.JRadioButton();
        buttonYesGroup4 = new javax.swing.JRadioButton();
        lblProfilePicture = new javax.swing.JLabel();
        lastDonationDateField = new com.toedter.calendar.JDateChooser();
        lblBack = new javax.swing.JLabel();
        curedDateField = new com.toedter.calendar.JDateChooser();
        dobDateField = new com.toedter.calendar.JDateChooser();
        diagnosedDateField = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        stateJComboBox = new javax.swing.JComboBox();
        bloodGroupJComboBox = new javax.swing.JComboBox();
        genderJComboBox = new javax.swing.JComboBox();
        curedDateValidateMessage = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        emailValidateMessage = new javax.swing.JLabel();
        emailSuccessLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(208, 93, 2));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Name");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, -1, -1));

        nameText.setBackground(new java.awt.Color(0, 0, 0));
        nameText.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        nameText.setForeground(new java.awt.Color(255, 255, 255));
        nameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextActionPerformed(evt);
            }
        });
        add(nameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 180, -1));
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(676, 218, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("UID");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 180, -1, -1));

        uidText.setBackground(new java.awt.Color(0, 0, 0));
        uidText.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        uidText.setForeground(new java.awt.Color(255, 255, 255));
        add(uidText, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 180, 180, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("DOB");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Age");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 220, -1, -1));

        ageText.setBackground(new java.awt.Color(0, 0, 0));
        ageText.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        ageText.setForeground(new java.awt.Color(255, 255, 255));
        ageText.setEnabled(false);
        ageText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ageTextMouseClicked(evt);
            }
        });
        ageText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ageTextKeyPressed(evt);
            }
        });
        add(ageText, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 220, 180, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Gender");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Blood group");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 320, -1, 20));

        streetText.setBackground(new java.awt.Color(0, 0, 0));
        streetText.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        streetText.setForeground(new java.awt.Color(255, 255, 255));
        add(streetText, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 490, 180, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Covid diagnosed date");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 440, -1, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Covid cured date");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 480, -1, -1));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Street address");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 490, -1, -1));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("City");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 530, -1, -1));

        cityText.setBackground(new java.awt.Color(0, 0, 0));
        cityText.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        cityText.setForeground(new java.awt.Color(255, 255, 255));
        add(cityText, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 530, 180, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("State");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 580, -1, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("ZipCode");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 630, -1, -1));

        zipText.setBackground(new java.awt.Color(0, 0, 0));
        zipText.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        zipText.setForeground(new java.awt.Color(255, 255, 255));
        add(zipText, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 630, 180, -1));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Contact Number");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 270, -1, -1));

        contactText.setBackground(new java.awt.Color(0, 0, 0));
        contactText.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        contactText.setForeground(new java.awt.Color(255, 255, 255));
        add(contactText, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 270, 180, -1));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Email ID");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, -1, -1));

        emailText.setBackground(new java.awt.Color(0, 0, 0));
        emailText.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        emailText.setForeground(new java.awt.Color(255, 255, 255));
        emailText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emailTextMouseClicked(evt);
            }
        });
        emailText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                uEmailKeyTyped(evt);
            }
        });
        add(emailText, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, 170, -1));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Date of last donation of Plasma");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 520, -1, -1));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Was your COVID-19 diagnosis confirmed by a lab test?");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 560, -1, -1));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Currently you do NOT have any symptoms? ");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 620, -1, -1));

        jLabel21.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Has it been at least 14 days since the last day of your symptoms? ");
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 680, 630, -1));

        jLabel22.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Have you had a follow up test that was negative for COVID-19?");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 740, 600, 20));

        btnSubmit.setBackground(new java.awt.Color(31, 31, 31));
        btnSubmit.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnSubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmit.setText("Submit");
        btnSubmit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        add(btnSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 800, 110, 40));

        addPhotoButton.setBackground(new java.awt.Color(31, 31, 31));
        addPhotoButton.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        addPhotoButton.setForeground(new java.awt.Color(255, 255, 255));
        addPhotoButton.setText("Add photo");
        addPhotoButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addPhotoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPhotoButtonActionPerformed(evt);
            }
        });
        add(addPhotoButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 250, 120, 40));

        buttonYesGroup2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        buttonYesGroup2.setForeground(new java.awt.Color(255, 255, 255));
        buttonYesGroup2.setText("Yes");
        buttonYesGroup2.setOpaque(false);
        add(buttonYesGroup2, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 650, -1, -1));

        buttonNoGroup2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        buttonNoGroup2.setForeground(new java.awt.Color(255, 255, 255));
        buttonNoGroup2.setText("No");
        buttonNoGroup2.setOpaque(false);
        add(buttonNoGroup2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 650, -1, -1));

        buttonNoGroup1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        buttonNoGroup1.setForeground(new java.awt.Color(255, 255, 255));
        buttonNoGroup1.setText("No");
        buttonNoGroup1.setOpaque(false);
        add(buttonNoGroup1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 590, -1, -1));

        buttonYesGroup1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        buttonYesGroup1.setForeground(new java.awt.Color(255, 255, 255));
        buttonYesGroup1.setText("Yes");
        buttonYesGroup1.setOpaque(false);
        add(buttonYesGroup1, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 590, -1, -1));

        buttonNoGroup3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        buttonNoGroup3.setForeground(new java.awt.Color(255, 255, 255));
        buttonNoGroup3.setText("No");
        buttonNoGroup3.setOpaque(false);
        add(buttonNoGroup3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 710, -1, -1));

        buttonYesGroup3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        buttonYesGroup3.setForeground(new java.awt.Color(255, 255, 255));
        buttonYesGroup3.setText("Yes");
        buttonYesGroup3.setOpaque(false);
        add(buttonYesGroup3, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 710, -1, -1));

        buttonNoGroup4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        buttonNoGroup4.setForeground(new java.awt.Color(255, 255, 255));
        buttonNoGroup4.setText("No");
        buttonNoGroup4.setOpaque(false);
        add(buttonNoGroup4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 770, -1, -1));

        buttonYesGroup4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        buttonYesGroup4.setForeground(new java.awt.Color(255, 255, 255));
        buttonYesGroup4.setText("Yes");
        buttonYesGroup4.setOpaque(false);
        add(buttonYesGroup4, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 770, -1, -1));

        lblProfilePicture.setBackground(new java.awt.Color(0, 0, 0));
        lblProfilePicture.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 204)));
        add(lblProfilePicture, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 100, 130, 130));

        lastDonationDateField.setBackground(new java.awt.Color(0, 0, 0));
        lastDonationDateField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lastDonationDateField.setForeground(new java.awt.Color(255, 255, 255));
        add(lastDonationDateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 520, 200, -1));

        lblBack.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblBack.setForeground(new java.awt.Color(255, 255, 255));
        lblBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/back.png"))); // NOI18N
        lblBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackMouseClicked(evt);
            }
        });
        add(lblBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 790, -1, 54));

        curedDateField.setBackground(new java.awt.Color(0, 0, 0));
        curedDateField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        curedDateField.setForeground(new java.awt.Color(255, 255, 255));
        curedDateField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                UCovidCuredDateKeyTyped(evt);
            }
        });
        add(curedDateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 480, 210, -1));

        dobDateField.setBackground(new java.awt.Color(0, 0, 0));
        dobDateField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dobDateField.setForeground(new java.awt.Color(255, 255, 255));
        dobDateField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dobDateFieldFocusLost(evt);
            }
        });
        dobDateField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dobDateFieldMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dobDateFieldMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                agePop(evt);
            }
        });
        dobDateField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                uDobKeyTyped(evt);
            }
        });
        add(dobDateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 170, -1));

        diagnosedDateField.setBackground(new java.awt.Color(0, 0, 0));
        diagnosedDateField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        diagnosedDateField.setForeground(new java.awt.Color(255, 255, 255));
        add(diagnosedDateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 440, 210, -1));

        jButton2.setBackground(new java.awt.Color(31, 31, 31));
        jButton2.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("New Form");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 800, 120, 40));

        stateJComboBox.setBackground(new java.awt.Color(0, 0, 0));
        stateJComboBox.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        stateJComboBox.setForeground(new java.awt.Color(255, 255, 255));
        stateJComboBox.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        stateJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stateJComboBoxActionPerformed(evt);
            }
        });
        add(stateJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 580, 180, -1));

        bloodGroupJComboBox.setBackground(new java.awt.Color(0, 0, 0));
        bloodGroupJComboBox.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        bloodGroupJComboBox.setForeground(new java.awt.Color(255, 255, 255));
        bloodGroupJComboBox.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bloodGroupJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bloodGroupJComboBoxActionPerformed(evt);
            }
        });
        add(bloodGroupJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 320, 180, -1));

        genderJComboBox.setBackground(new java.awt.Color(0, 0, 0));
        genderJComboBox.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        genderJComboBox.setForeground(new java.awt.Color(255, 255, 255));
        genderJComboBox.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        genderJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderJComboBoxActionPerformed(evt);
            }
        });
        add(genderJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, 170, -1));

        curedDateValidateMessage.setBackground(new java.awt.Color(0, 0, 0));
        curedDateValidateMessage.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        curedDateValidateMessage.setText("Cured Date is before the Diagnosed Date!");
        add(curedDateValidateMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 420, -1, -1));

        jPanel3.setBackground(new java.awt.Color(31, 31, 31));
        jPanel3.setPreferredSize(new java.awt.Dimension(926, 70));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Donor Registration Form ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 1666, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1690, -1));

        emailValidateMessage.setBackground(new java.awt.Color(0, 0, 0));
        emailValidateMessage.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        emailValidateMessage.setText("Email format incorrect!");
        add(emailValidateMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        emailSuccessLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tick.gif"))); // NOI18N
        add(emailSuccessLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, 30, 30));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, 1140, 20));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("BIO Details");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 110, -1, -1));

        jLabel23.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Address Details");
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 420, -1, -1));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 370, 20, 360));

        jLabel24.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("COVID Details");
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 390, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        Date currentDate = new Date(); 
         if ( lblProfilePicture == null ){
            lblProfilePicture.setBorder(BorderFactory.createLineBorder(Color.RED));
            lblProfilePicture.setForeground(Color.red);
            addPhotoButton.setBorder(BorderFactory.createLineBorder(Color.RED));
            addPhotoButton.setForeground(Color.red);       
        }
        if ( stateJComboBox.getSelectedItem().equals("") ){
            stateJComboBox.setBorder(BorderFactory.createLineBorder(Color.RED));
            stateJComboBox.setForeground(Color.red);
        }
        if ( genderJComboBox.getSelectedItem().equals("") ){
            genderJComboBox.setBorder(BorderFactory.createLineBorder(Color.RED));
            genderJComboBox.setForeground(Color.red);
        }
        if ( bloodGroupJComboBox.getSelectedItem().equals("") ){
            bloodGroupJComboBox.setBorder(BorderFactory.createLineBorder(Color.RED));
            bloodGroupJComboBox.setForeground(Color.red);
        }
        if (nameText.getText().isEmpty()) {
            nameText.setBorder(BorderFactory.createLineBorder(Color.RED));
            nameText.setForeground(Color.red);
        }
        if (uidText.getText().isEmpty()) {
            uidText.setBorder(BorderFactory.createLineBorder(Color.RED));
            uidText.setForeground(Color.red);
        } 
        if (emailText.getText().isEmpty()) {
            emailText.setBorder(BorderFactory.createLineBorder(Color.RED));
            emailText.setForeground(Color.red);
        }
        if (contactText.getText().isEmpty()) {
            contactText.setBorder(BorderFactory.createLineBorder(Color.RED));
            contactText.setForeground(Color.red);
        }
        if (zipText.getText().isEmpty()) {
            zipText.setBorder(BorderFactory.createLineBorder(Color.RED));
            zipText.setForeground(Color.red);
        }
        if (cityText.getText().isEmpty()) {
            cityText.setBorder(BorderFactory.createLineBorder(Color.RED));
            cityText.setForeground(Color.red);
        }
        if (streetText.getText().isEmpty()) {
            streetText.setBorder(BorderFactory.createLineBorder(Color.RED));
            streetText.setForeground(Color.red);
        }
        if (diagnosedDateField.getDate() == null ) {
            diagnosedDateField.setBorder(BorderFactory.createLineBorder(Color.RED));
            diagnosedDateField.setForeground(Color.red);
        }
        if (curedDateField.getDate() == null ) {
            curedDateField.setBorder(BorderFactory.createLineBorder(Color.RED));
            curedDateField.setForeground(Color.red);
        }
        if (lastDonationDateField.getDate() == null ) {
            lastDonationDateField.setBorder(BorderFactory.createLineBorder(Color.RED));
            lastDonationDateField.setForeground(Color.red);
        }        
        if (dobDateField.getDate() == null ) {
            dobDateField.setBorder(BorderFactory.createLineBorder(Color.RED));
            dobDateField.setForeground(Color.red);
        }
         if (ageText.getText().isEmpty()) {
            ageText.setBorder(BorderFactory.createLineBorder(Color.RED));
            ageText.setForeground(Color.red);
        }
       //
       //
       //
       //
       
        if (    nameText.getText().isEmpty()        || emailText.getText().isEmpty()        || cityText.getText().isEmpty() ||
                contactText.getText().isEmpty()     || zipText.getText().isEmpty()          || streetText.getText().isEmpty() || 
                uidText.getText().isEmpty()         || curedDateField.getDate() == null  || diagnosedDateField.getDate() == null || 
                dobDateField.getDate() == null      || lastDonationDateField.getDate() == null     || 
                ageText.getText().isEmpty()         ||
                String.valueOf(genderJComboBox.getSelectedItem()).equals("") || 
                String.valueOf(bloodGroupJComboBox.getSelectedItem()).equals("") || 
                String.valueOf(stateJComboBox.getSelectedItem()).equals(""))
            { 
            JOptionPane.showMessageDialog(null,new JLabel(  "<html><h2><I>All fields are</I><font color='red'> mandatory</font>!</h2></html>") , "Error", JOptionPane.ERROR_MESSAGE);
             return ;
            }
   
        
        else if(  !buttonNoGroup1.isSelected()  &&  !buttonYesGroup1.isSelected()   ||
                  !buttonNoGroup2.isSelected()  &&  !buttonYesGroup2.isSelected()   ||
                  !buttonNoGroup3.isSelected()  &&  !buttonYesGroup3.isSelected()   ||
                  !buttonNoGroup4.isSelected()  &&  !buttonYesGroup4.isSelected()   )
        {
        JOptionPane.showMessageDialog(null,new JLabel("<html><h2><I>All fields are</I><font color='red'> mandatory</font>!</h2></html>"), "Error", JOptionPane.ERROR_MESSAGE);
        return;
        }
        
    
        else if (!isAlpha(nameText.getText()))
        {
         nameText.setBorder(BorderFactory.createLineBorder(Color.RED));
            nameText.setForeground(Color.red);
            JOptionPane.showMessageDialog(null, new JLabel("<html><h2><font color='red'><I>Name</I></font> must contain only<font color='green'> alphabets</font></h2>!</html>") , "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if (!isAlpha(cityText.getText()))
        {
         cityText.setBorder(BorderFactory.createLineBorder(Color.RED));
            cityText.setForeground(Color.red);
         JOptionPane.showMessageDialog(null, new JLabel("<html><h2>Name of the<font color='red'><I> City</I></font> must contain only<font color='green'> alphabets</font>!</h2></html>") , "Error", JOptionPane.ERROR_MESSAGE);
            return;
            //JOptionPane.showMessageDialog(null, "City name must contain only alphabets." , "Error", JOptionPane.ERROR_MESSAGE);
            //return;
        }
        
        else   if ( !phoneNumberValidator(contactText.getText())   ){
           //JOptionPane.showMessageDialog(null, "Contact number should be 10 digit and Zip should be 5 digit" , "Error", JOptionPane.ERROR_MESSAGE);
           JOptionPane.showMessageDialog(null, new JLabel("<html><h2><font color='red'><I>Contact Number</I></font> should be<font color='green'> 10 digit</font>!</h2></html>") , "Error", JOptionPane.ERROR_MESSAGE);
           // return;
           return;
        }
        else   if ( !zipCodeValidator(zipText.getText()) ){
           //JOptionPane.showMessageDialog(null, "Contact number should be 10 digit and Zip should be 5 digit" , "Error", JOptionPane.ERROR_MESSAGE);
           JOptionPane.showMessageDialog(null, new JLabel("<html><h2><font color='red'><I>Zip</I></font> should be<font color='green'> 5 digit</font>!</h2></html>") , "Error", JOptionPane.ERROR_MESSAGE);
           return;
        }
        else   if ( !emailValidator(emailText.getText()) ){
           //JOptionPane.showMessageDialog(null, "Email ID must be in correct format!" , "Error", JOptionPane.ERROR_MESSAGE);
JOptionPane.showMessageDialog(null, new JLabel("<html><h2><font color='red'><I>Email ID</I></font> must be in<font color='green'> correct format</font>!</h2></html>") , "Error", JOptionPane.ERROR_MESSAGE);

           return;
        } 
        
        else if (currentDate.equals(lastDonationDateField.getDate()) || lastDonationDateField.getDate().after(currentDate)) {
             //JOptionPane.showMessageDialog(null, "Date should be past date", "Error", JOptionPane.ERROR_MESSAGE);
             JOptionPane.showMessageDialog(null, new JLabel("<html><h2><font color='red'><I>Date</I></font> should be<font color='green'> past date</font>!</h2></html>") , "Error", JOptionPane.ERROR_MESSAGE);

             return;}
        else if(diagnosedDateField.getDate().compareTo(curedDateField.getDate())>0){
            //JOptionPane.showMessageDialog(null, "Cured Date should be ahead of Diagnosed Date", "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, new JLabel("<html><h2><font color='red'><I>Cured Date</I></font> should be ahead of<font color='red'><I> Diagnosed Date</I></font>!</h2></html>") , "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
          
          
          else{

            int n = JOptionPane.showConfirmDialog(null,new JLabel("<html><h2>Would you like to<font color='green'><I> submit</I></font> the form?</h2></html>"),
                    "CONFIRMATION REQUIRED",JOptionPane.YES_NO_OPTION);

            if(n == 0)
            { 
            
        DonorRequest donorRequest = system.getDonorRequestDirectory().addDonorRequest(); 
        donorRequest.setDonorID(uidText.getText()); // UID
        donorRequest.setName(nameText.getText()); // Name
        donorRequest.setDob(dobDateField.getDate()); // DOB 
        donorRequest.setCovidDiagnosedDate(diagnosedDateField.getDate()); // covidDiagnosedDate
        donorRequest.setCovidCuredDate(curedDateField.getDate()); // covidCuredDate
        donorRequest.setLastDonationDate(lastDonationDateField.getDate()); // lastDonationDate
        donorRequest.setAge(Integer.parseInt(ageText.getText())); // Age
        donorRequest.setGender((String) genderJComboBox.getSelectedItem()); // gender
        donorRequest.setBloodGroup((String) bloodGroupJComboBox.getSelectedItem()); // Blood group
        donorRequest.setStreetAddress(streetText.getText()); // streetAddress
        donorRequest.setCity(cityText.getText()); // city
        donorRequest.setState((String) stateJComboBox.getSelectedItem()); // state
        donorRequest.setZipCode(Integer.parseInt(zipText.getText())); // zipCode
        donorRequest.setContact(Integer.parseInt(contactText.getText())); // contact
        donorRequest.setEmailID(emailText.getText()); // emailID
        donorRequest.setStatus("New Request"); // status
        //donorRequest.setImagePath(photoTextField.getText()); 
        donorRequest.setdP(tempdP);
        
        for(DonorRequest dnr : system.getDonorRequestDirectory().getDonorRequestList()){
            //System.out.println("PRINITNG IT HERE!!");
           // System.out.println(dnr.getName());
        }
        
        if(buttonYesGroup1.isSelected())
        {donorRequest.setLabConfirmation(true); //  labConfirmation
        }
        else if(buttonNoGroup1.isSelected())
        {donorRequest.setLabConfirmation(false); //  labConfirmation
        }
        
        if(buttonYesGroup2.isSelected())
        {donorRequest.setSymptoms(true); //  symptoms
        }
        else if(buttonNoGroup2.isSelected())
        {donorRequest.setSymptoms(false); //  symptoms
        }
        
        if(buttonYesGroup3.isSelected())
        {donorRequest.setLastDaySymptoms(true); //  lastDaySymptoms
        }
        else if(buttonNoGroup3.isSelected())
        {donorRequest.setLastDaySymptoms(false); //  lastDaySymptoms
        }
        
        if(buttonYesGroup4.isSelected())
        {donorRequest.setFollowUpTest(true); //  followUpTest
        }
        else if(buttonNoGroup4.isSelected())
        {donorRequest.setFollowUpTest(false); //  followUpTest
        }

        JOptionPane.showMessageDialog(null, new JLabel("<html><h2>Thank you for registering to<font color='green'><I><B> HELP</B></I></font>!</h2></html>"));
        
        dB4OUtil.storeSystem(system);
        disableAllButton();
        }}
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void addPhotoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPhotoButtonActionPerformed
        // TODO add your handling code here:
       /* JFileChooser chooser1 = new JFileChooser();
        chooser1.showOpenDialog(null);
        File f = chooser1.getSelectedFile();
        String filename = f.getAbsolutePath();
        photoTextField.setText(filename);
        Image getAbsolutePath = null;
        ImageIcon icon = new ImageIcon(filename);
        Image image = icon.getImage().getScaledInstance(jLabel23.getWidth(), jLabel23.getHeight(), Image.SCALE_SMOOTH);
        jLabel23.setIcon(icon);*/
       
       
       JFileChooser file = new JFileChooser();
          file.setCurrentDirectory(new File(System.getProperty("user.dir")));
          //filter the files
          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
          file.addChoosableFileFilter(filter);
          int result = file.showSaveDialog(null);
           //if the user click on save in Jfilechooser
          if(result == JFileChooser.APPROVE_OPTION){
              File selectedFile = file.getSelectedFile();
              tempdP = new byte[(int) selectedFile.length()]; 
                FileInputStream fis;
             try {
                 fis = new FileInputStream(selectedFile);
                 fis.read(tempdP);
                 fis.close();
             } catch (IOException ex) {
                 Logger.getLogger(NewDonorJPanel.class.getName()).log(Level.SEVERE, null, ex);
             }             
              lblProfilePicture.setIcon(ResizeImage(selectedFile.getAbsolutePath()));
              lblProfilePicture.setIcon(setPicture(selectedFile.getAbsolutePath(),lblProfilePicture));
          }

          else if(result == JFileChooser.CANCEL_OPTION){
             // System.out.println("No File Select");
          }
       
          
       
    }//GEN-LAST:event_addPhotoButtonActionPerformed

    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseClicked
        // TODO add your handling code here:
        
        
        
        
        JFrame frame = (JFrame) getWindowAncestor(this);
        frame.dispose();
        NewDonorJPanel.super.setVisible(false);
        dB4OUtil.storeSystem(system);
        
        
    }//GEN-LAST:event_lblBackMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        lblProfilePicture.setIcon(null);
        radioGroup1.clearSelection();
        radioGroup2.clearSelection();
        radioGroup3.clearSelection();
        radioGroup4.clearSelection();
        uidText.setEnabled(true);
        nameText.setEnabled(true);
        dobDateField.setEnabled(true);
        ageText.setEnabled(false);
        emailText.setEnabled(true);
        contactText.setEnabled(true);
        genderJComboBox.setEnabled(true);
        bloodGroupJComboBox.setEnabled(true);
        diagnosedDateField.setEnabled(true);
        curedDateField.setEnabled(true);
        lastDonationDateField.setEnabled(true);
        streetText.setEnabled(true);
        cityText.setEnabled(true);
        stateJComboBox.setEnabled(true);
        zipText.setEnabled(true);
        buttonYesGroup1.setEnabled(true);
        buttonNoGroup1.setEnabled(true);
        buttonYesGroup2.setEnabled(true);
        buttonNoGroup2.setEnabled(true);
        buttonYesGroup3.setEnabled(true);
        buttonNoGroup3.setEnabled(true);
        buttonYesGroup4.setEnabled(true);
        buttonNoGroup4.setEnabled(true);
        addPhotoButton.setEnabled(true);
        
        uidText.setText("");
        nameText.setText("");
        ageText.setText("");
        emailText.setText("");
        contactText.setText("");
        streetText.setText("");
        cityText.setText("");
        zipText.setText("");
        
        genderJComboBox.setSelectedItem("");
        bloodGroupJComboBox.setSelectedItem("");
        stateJComboBox.setSelectedItem("");
        
        dobDateField.setCalendar(null);
        diagnosedDateField.setCalendar(null);
        curedDateField.setCalendar(null);
        lastDonationDateField.setCalendar(null);
        
        buttonYesGroup1.setSelected(false);
        buttonNoGroup1.setSelected(false);
        buttonYesGroup2.setSelected(false);
        buttonNoGroup2.setSelected(false);
        buttonYesGroup3.setSelected(false);
        buttonNoGroup3.setSelected(false);
        buttonYesGroup4.setSelected(false);
        buttonNoGroup4.setSelected(false);
        
        lblProfilePicture.removeAll();
        
        //JOptionPane.showMessageDialog(null, new JLabel(  "<html><h2><font color='red'>Hello</font>, world </h2></html>"));
            
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void stateJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stateJComboBoxActionPerformed
       
        
    }//GEN-LAST:event_stateJComboBoxActionPerformed

    private void bloodGroupJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bloodGroupJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bloodGroupJComboBoxActionPerformed

    private void genderJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genderJComboBoxActionPerformed

    private void uEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_uEmailKeyTyped
        // TODO add your handling code here:
        if (!emailValidator(emailText.getText())) {
            emailValidateMessage.setVisible(true);
            emailValid = false;
        } else {
            emailText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            emailText.setForeground(Color.WHITE);
            emailValidateMessage.setVisible(false);
            emailSuccessLabel.setVisible(true);
            emailValid = true;
            int delay = 2500; //milliseconds
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                   emailSuccessLabel.setVisible(false);
                }
            };
            javax.swing.Timer tick = new javax.swing.Timer(delay, taskPerformer);
            tick.setRepeats(false);
            tick.start();
        }
        
        
        
    }//GEN-LAST:event_uEmailKeyTyped

    private void nameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextActionPerformed

    private void uDobKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_uDobKeyTyped
        // TODO add your handling code here:
        // System.out.println("date");
    /*

        Date dob = dobDateField.getDate();
    
        LocalDate today = LocalDate.now();                          //Today's date
        LocalDate birthday;  //Birth date
        birthday = LocalDate.of(dob);
 
        Period p = Period.between(birthday, today);
        
        ageText.setText(String.valueOf(p.getYears()));
    String dobq=  calculateAge(dob, today);    
        
    }

    
*/
  /*  public  int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
  
      */
    }//GEN-LAST:event_uDobKeyTyped

    private void ageTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ageTextKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ageTextKeyPressed

    private void ageTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ageTextMouseClicked
        // TODO add your handling code here:
        Date dob = dobDateField.getDate();
        //System.out.println(dob.getYear());
        ageText.setText((String.valueOf(new Date().getYear()-dob.getYear())));
    
      /*  LocalDate today = LocalDate.now();                          //Today's date
        LocalDate birthday;  //Birth date
        birthday = LocalDate.of(dob);
 
        Period p = Period.between(birthday, today);
        
        ageText.setText(String.valueOf(p.getYears()));
        String dobq=  calculateAge(dob, today);  
        */
        
    }//GEN-LAST:event_ageTextMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        Date dob = dobDateField.getDate();
       // System.out.println(dob.getYear());
        ageText.setText((String.valueOf(new Date().getYear()-dob.getYear())));
    }//GEN-LAST:event_formMouseClicked

    private void dobDateFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dobDateFieldMouseExited
  //    Date dob = dobDateField.getDate();
 //       System.out.println(dob.getYear());
    //    ageText.setText((String.valueOf(new Date().getYear()-dob.getYear())));
    }//GEN-LAST:event_dobDateFieldMouseExited

    public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lblProfilePicture.getWidth(), lblProfilePicture.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
    
     private ImageIcon setPicture(String carImageLocation, JLabel carImage){

        ImageIcon imageCar;
        imageCar = new ImageIcon(carImageLocation);
        Image picCar = imageCar.getImage();
        Image resizedImage = picCar.getScaledInstance(carImage.getWidth(), carImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon selectedCarPicture = new ImageIcon(resizedImage);
        
        return selectedCarPicture;  
}    
    
    private void dobDateFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dobDateFieldMousePressed
     
    }//GEN-LAST:event_dobDateFieldMousePressed

    
    private void dobDateFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dobDateFieldFocusLost
        // TODO add your handling code here:
      
    }//GEN-LAST:event_dobDateFieldFocusLost

    private void UCovidCuredDateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UCovidCuredDateKeyTyped
        // TODO add your handling code here:
        if( diagnosedDateField.getDate().compareTo(curedDateField.getDate()) > 0){
                // Date 1 is after Date 2
                curedDateValidateMessage.setVisible(true);
        }
        
  
    }//GEN-LAST:event_UCovidCuredDateKeyTyped

    private void agePop(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agePop
        // TODO add your handling code here:
        Date dob = dobDateField.getDate();
        System.out.println(dob.getYear());
        ageText.setText((String.valueOf(new Date().getYear()-dob.getYear())));
        
    }//GEN-LAST:event_agePop

    private void emailTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emailTextMouseClicked
        // TODO add your handling code here:
        
        Date dob = dobDateField.getDate();
      //  System.out.println(dob.getYear());
        ageText.setText((String.valueOf(new Date().getYear()-dob.getYear())));
    }//GEN-LAST:event_emailTextMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPhotoButton;
    private javax.swing.JTextField ageText;
    private javax.swing.JComboBox bloodGroupJComboBox;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JRadioButton buttonNoGroup1;
    private javax.swing.JRadioButton buttonNoGroup2;
    private javax.swing.JRadioButton buttonNoGroup3;
    private javax.swing.JRadioButton buttonNoGroup4;
    private javax.swing.JRadioButton buttonYesGroup1;
    private javax.swing.JRadioButton buttonYesGroup2;
    private javax.swing.JRadioButton buttonYesGroup3;
    private javax.swing.JRadioButton buttonYesGroup4;
    private javax.swing.JTextField cityText;
    private javax.swing.JTextField contactText;
    private com.toedter.calendar.JDateChooser curedDateField;
    private javax.swing.JLabel curedDateValidateMessage;
    private com.toedter.calendar.JDateChooser diagnosedDateField;
    private com.toedter.calendar.JDateChooser dobDateField;
    private javax.swing.JLabel emailSuccessLabel;
    private javax.swing.JTextField emailText;
    private javax.swing.JLabel emailValidateMessage;
    private javax.swing.JComboBox genderJComboBox;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private com.toedter.calendar.JDateChooser lastDonationDateField;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblProfilePicture;
    private javax.swing.JTextField nameText;
    private javax.swing.JComboBox stateJComboBox;
    private javax.swing.JTextField streetText;
    private javax.swing.JTextField uidText;
    private javax.swing.JTextField zipText;
    // End of variables declaration//GEN-END:variables
}
