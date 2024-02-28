/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package psproject_v5.ui.form;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import psproject_v5.communication.ObjectWrapper;
import psproject_v5.constants.MyConstants;
import psproject_v5.controller.Controller;
import psproject_v5.domain.Department;
import psproject_v5.session.Session;
import psproject_v5.ui.components.table.model.TableModelDepartments;
import psproject_v5.ui.components.validator.impl.RequiredDepartmentNameValidator;
import psproject_v5.ui.components.validator.impl.RequiredShortNameValidator;
import psproject_v5.domain.Employee;

/**
 *
 * @author aleks
 */
public class FrmAddDepartment extends javax.swing.JDialog {

    /**
     * Creates new form FrmAddDepartment
     */
    public FrmAddDepartment(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setValidators();
        prepareView();
        setKeyBindings();
//        System.out.println(Session.getInstance().getValue("tableModelDepartments") + "===== iz forme Dodaj");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlName = new psproject_v5.ui.form.panel.impl.PnlTextField();
        pnlShortName = new psproject_v5.ui.form.panel.impl.PnlTextField();
        btnDiscard = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnDiscard.setText("Discard");
        btnDiscard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiscardActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDiscard, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlShortName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(pnlName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlShortName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(btnDiscard)
                .addGap(18, 18, 18)
                .addComponent(btnSave)
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDiscardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiscardActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnDiscardActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        
        actionSave();
        
        
    }//GEN-LAST:event_btnSaveActionPerformed

    private void actionSave() throws HeadlessException {
        try {
            Department department = getDepartment();
            Controller.getInstance().add(department);
            
            TableModelDepartments tableModel = (TableModelDepartments) Session.getInstance().getValue("tableModelDepartments");
            tableModel.add(department);
            this.dispose();
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da kreira novu katedru", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDiscard;
    private javax.swing.JButton btnSave;
    private psproject_v5.ui.form.panel.impl.PnlTextField pnlName;
    private psproject_v5.ui.form.panel.impl.PnlTextField pnlShortName;
    // End of variables declaration//GEN-END:variables

    private void prepareView() {
        setTitle("ADD DEPARTMENT");
        pnlName.setStaticText("Name:");
        pnlShortName.setStaticText("Short name:");
    }

    private void setKeyBindings() {
        btnSave.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "save");
        btnSave.getActionMap().put("save", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionSave();
            }
        });
        btnDiscard.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "discard");
        btnDiscard.getActionMap().put("discard", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    private Department getDepartment() throws Exception {
        Department department = new Department();
        String name = (String) pnlName.getValue();
        String shortName = (String) pnlShortName.getValue();
        department.setName(name);
        department.setShortName(shortName);
        return department;
    }

    private void setValidators() {
        pnlName.setValidator(new RequiredDepartmentNameValidator());
        pnlShortName.setValidator(new RequiredShortNameValidator());
    }
}