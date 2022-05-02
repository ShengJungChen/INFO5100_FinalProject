/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.AnimalRegistorRole;

import ui.AnimalRegistorRole.*;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.EcoSystem.EcoSystem;
import model.Enterprise.AnimalShelterEnterprise;
import model.Enterprise.Enterprise;
import model.Network.Network;
import model.Organization.AnimalRegisterOrganization;
import model.Organization.Organization;
import model.Organization.VolunteerOrganization;
import model.UserAccount.UserAccount;
import model.WorkQueue.VolunteerRequest;
import model.WorkQueue.WorkQueue;
import model.WorkQueue.WorkRequest;
import ui.AnimalManagerRole.ManageAnimalJPanel;

/**
 *
 * @author raunak
 */
public class RequestVolunteerJPanel extends javax.swing.JPanel {

        private JPanel workArea;
        private UserAccount account;
        private AnimalRegisterOrganization organization;
        private AnimalShelterEnterprise enterprise;
        private Network network;
        private EcoSystem ecosystem;
    //test

    public RequestVolunteerJPanel(JPanel userProcessContainer, UserAccount account, AnimalRegisterOrganization organization, AnimalShelterEnterprise enterprise, Network network, EcoSystem ecosystem) {
        initComponents();

        this.workArea = userProcessContainer;
        this.account = account;
        this.organization = organization;
        this.enterprise = enterprise;
        this.network = network;
        this.ecosystem = ecosystem;
        
        populateVolunteerRequestTable();
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRequestVolunteer = new javax.swing.JButton();
        lblMessage = new javax.swing.JLabel();
        txtRequestVolunteerMessage = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSentVolunteerRequest = new javax.swing.JTable();

        setBackground(new java.awt.Color(212, 255, 224));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRequestVolunteer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icon_send.png"))); // NOI18N
        btnRequestVolunteer.setText("Request");
        btnRequestVolunteer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRequestVolunteerActionPerformed(evt);
            }
        });
        add(btnRequestVolunteer, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 430, -1, 40));

        lblMessage.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblMessage.setText("Message:");
        add(lblMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, -1, -1));
        add(txtRequestVolunteerMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, 250, 40));

        btnBack.setText("<< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitle.setText("Request Volunteer");
        add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, -1, -1));

        tblSentVolunteerRequest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Message", "Sender", "Volunteer Manager", "Volunteer Assigned", "Enterprise", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblSentVolunteerRequest);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 890, 250));
    }// </editor-fold>//GEN-END:initComponents
    public void populateVolunteerRequestTable(){
        DefaultTableModel model = (DefaultTableModel) tblSentVolunteerRequest.getModel();
        
        model.setRowCount(0);
        
        for (WorkRequest request : account.getWorkQueue().getWorkRequestList()){
            if (request instanceof VolunteerRequest){
            Object[] row = new Object[6];
            row[0] = request.getMessage();
            row[1] = request.getSender();
            row[2] = request.getReceiver();
            row[3] = ((VolunteerRequest) request).getAssignedVolunteer() == null ? null : ((VolunteerRequest) request).getAssignedVolunteer();
            row[4] = request.getReceiver() == null ? null : request.getReceiver().getEnterprise();
            row[5] = request.getStatus();

            model.addRow(row);
            }
        }
    }
    
    private void btnRequestVolunteerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRequestVolunteerActionPerformed

        String requestVolunteerMessage = txtRequestVolunteerMessage.getText();
        
        if(requestVolunteerMessage.equals("") || requestVolunteerMessage.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter something to send.","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        VolunteerRequest request = new VolunteerRequest();
        request.setMessage(requestVolunteerMessage);
        request.setSender(account);
        request.setStatus("Pending");
        
        network.getWorkQueue().getWorkRequestList().add(request);
        account.getWorkQueue().getWorkRequestList().add(request);
        
        JOptionPane.showMessageDialog(this, "Request message sent","Information",JOptionPane.INFORMATION_MESSAGE);
        txtRequestVolunteerMessage.setText("");
        
        populateVolunteerRequestTable();
    }//GEN-LAST:event_btnRequestVolunteerActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        
        workArea.remove(this);
        Component[] componentArray = workArea.getComponents();
        Component component = componentArray[componentArray.length - 1];
        AnimalRegistorWorkAreaJPanel arwajp = (AnimalRegistorWorkAreaJPanel) component;
        CardLayout layout = (CardLayout) workArea.getLayout();
        layout.previous(workArea);


    }//GEN-LAST:event_btnBackActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnRequestVolunteer;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblSentVolunteerRequest;
    private javax.swing.JTextField txtRequestVolunteerMessage;
    // End of variables declaration//GEN-END:variables
}
