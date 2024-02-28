/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package psproject_v5.ui.form;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import psproject_v5.communication.ObjectWrapper;
import psproject_v5.constants.MyConstants;
import psproject_v5.controller.Controller;
import psproject_v5.domain.AcademicTitle;
import psproject_v5.domain.Department;
import psproject_v5.domain.EducationTitle;
import psproject_v5.domain.Employee;
import psproject_v5.domain.EmployeeAcademicTitle;
import psproject_v5.domain.Status;
import psproject_v5.session.Session;
import psproject_v5.ui.components.table.cellEditors.DateCellEditor;
import psproject_v5.ui.components.table.cellEditors.MyCmbCellEditor;
import psproject_v5.ui.components.table.model.TableModelAcademicTitleHistory;
import psproject_v5.ui.components.table.model.TableModelEmployees;
import psproject_v5.ui.components.validator.impl.RequiredCapitalisedNameValidator;
import psproject_v5.ui.form.function.IUpdateView;
import psproject_v5.ui.form.util.FormMode;

/**
 *
 * @author aleks
 */
public class FrmEmployee extends javax.swing.JDialog{

    private int mode;
    private List<AcademicTitle> academicTitles;
    private List<EducationTitle> educationTitles;
    private List<Department> departments;
    private List<EmployeeAcademicTitle> titleHistory;
    private Employee employee;

    
    /**
     * Creates new form FrmAddEmployee
     */
    public FrmEmployee(java.awt.Frame parent, boolean modal, int mode) {
        super(parent, modal);
        this.mode = mode;
        initComponents();
        setValidators();
        try {
//            if(1==1)throw new Exception("Sistem ne moze da prikaze zaposlenog");
            departments = Controller.getInstance().getAllDepartments();            
            educationTitles = Controller.getInstance().getAllEducationTitles();            
            academicTitles = Controller.getInstance().getAllAcademicTitles();            
            prepareView();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

        pnlFirstname = new psproject_v5.ui.form.panel.impl.PnlTextField();
        pnlLastname = new psproject_v5.ui.form.panel.impl.PnlTextField();
        pnlBirthday = new psproject_v5.ui.form.panel.impl.PnlDate3CMB();
        pnlDepartment = new psproject_v5.ui.form.panel.impl.PnlCombobox();
        pnlAcademicTitle = new psproject_v5.ui.form.panel.impl.PnlCombobox();
        pnlEducationTitle = new psproject_v5.ui.form.panel.impl.PnlCombobox();
        btnSave = new javax.swing.JButton();
        pnlAcademicTitleHistory = new psproject_v5.ui.form.panel.impl.PnlTable();
        btnEdit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add New Employee");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlEducationTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlAcademicTitleHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                    .addComponent(pnlAcademicTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDepartment, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlLastname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlFirstname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlLastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAcademicTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEducationTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlAcademicTitleHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnEdit))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        if (mode == FormMode.ADD_MODE) {

            try {
                employee = getEmployee();
                Controller.getInstance().add(employee);
                
                TableModelEmployees tableModel = (TableModelEmployees) Session.getInstance().getValue("tableModelEmployees");
                if (tableModel != null) {
                    tableModel.add(employee);
                    tableModel.fireTableDataChanged();
                    Session.getInstance().removeItem("tableModelEmployees");
                }
                this.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Sistem ne moze da kreira novog zaposlenog", "GRESKA", JOptionPane.ERROR_MESSAGE);
//                JOptionPane.showMessageDialog(null, ex.getMessage(), "GRESKA", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (mode == FormMode.UPDATE_MODE) {
            try {
                
                Employee employee1 = getEmployee();
                employee1.setId(employee.getId());
                Controller.getInstance().update(employee1);

                TableModelEmployees tableModel = (TableModelEmployees) Session.getInstance().getValue("tableModelEmployees");
                if (tableModel != null) {
                    int i = tableModel.getEmployees().indexOf(employee);
                    tableModel.getEmployees().remove(employee);
                    tableModel.getEmployees().add(i, employee1);
                    tableModel.fireTableDataChanged();
                }

                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti zaposlenog", "GRESKA", JOptionPane.ERROR_MESSAGE);
//                JOptionPane.showMessageDialog(null, ex.getMessage(), "GRESKA", JOptionPane.ERROR_MESSAGE);
            }
        }


    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

        try {
            mode = FormMode.UPDATE_MODE;
            prepareView();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "GRESKA", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnEditActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

//        System.out.println("otvoreeenoooo");
//        Session.getInstance().addItem(MyConstants.CURRENT_JDIALOG, this);

    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//        System.out.println("otvoren prozor");
//        Session.getInstance().addItem(MyConstants.CURRENT_JDIALOG, this);
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
//        System.out.println("zatvoren prozor");
//        Session.getInstance().removeItem(MyConstants.CURRENT_JDIALOG);        
    }//GEN-LAST:event_formWindowClosed

    private Employee getEmployee() throws Exception {
        String firstname = (String) pnlFirstname.getValue();
        String lastname = (String) pnlLastname.getValue();
        LocalDate birthday = (LocalDate) pnlBirthday.getValue();
        Department department = (Department) pnlDepartment.getValue();
        AcademicTitle academicTitle = (AcademicTitle) pnlAcademicTitle.getValue();
        EducationTitle educationTitle = (EducationTitle) pnlEducationTitle.getValue();
        Employee employee1 = new Employee(firstname, lastname, birthday, department, academicTitle, educationTitle, Status.ACTIVE);
        return employee1;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private psproject_v5.ui.form.panel.impl.PnlCombobox pnlAcademicTitle;
    private psproject_v5.ui.form.panel.impl.PnlTable pnlAcademicTitleHistory;
    private psproject_v5.ui.form.panel.impl.PnlDate3CMB pnlBirthday;
    private psproject_v5.ui.form.panel.impl.PnlCombobox pnlDepartment;
    private psproject_v5.ui.form.panel.impl.PnlCombobox pnlEducationTitle;
    private psproject_v5.ui.form.panel.impl.PnlTextField pnlFirstname;
    private psproject_v5.ui.form.panel.impl.PnlTextField pnlLastname;
    // End of variables declaration//GEN-END:variables

    private void prepareView() throws Exception {

        pnlFirstname.setStaticText("Firstname:");
        pnlLastname.setStaticText("Lastname:");
        pnlBirthday.setStaticText("Birthday:");
        pnlDepartment.setStaticText("Department:");
        pnlAcademicTitle.setStaticText("Academic title:");
        pnlEducationTitle.setStaticText("Education title:");
        pnlEducationTitle.setOptional(true);

        pnlFirstname.setPnlEnabled(true);
        pnlLastname.setPnlEnabled(true);
        pnlBirthday.setPnlEnabled(true);
        pnlDepartment.setPnlEnabled(true);
        pnlAcademicTitle.setPnlEnabled(true);
        pnlEducationTitle.setPnlEnabled(true);

        btnSave.setVisible(true);
        btnEdit.setVisible(false);

        prepareCmb();

        if (mode == FormMode.ADD_MODE) {
            setTitle("ADD EMPLOYEE");
            Department department = (Department) Session.getInstance().getValue("departmentHires");
            if (department != null) {
                pnlDepartment.setPnlEnabled(false);
                pnlDepartment.setValue(department);
                Session.getInstance().removeItem("departmentHires");
            }
            //ako postoji vec
            remove(pnlAcademicTitleHistory);
            pack();
        }
        if (mode == FormMode.UPDATE_MODE) {            
            setTitle("EDIT EMPLOYEE");
//            if(1==1)throw new Exception("Sistem ne moze da pokrene rezim za izmenu");
            btnSave.setVisible(true);
            btnEdit.setEnabled(false);

            pnlFirstname.setPnlEnabled(true);
            pnlLastname.setPnlEnabled(true);
            pnlBirthday.setPnlEnabled(true);
            pnlDepartment.setPnlEnabled(true);
            pnlAcademicTitle.setPnlEnabled(true);
            pnlEducationTitle.setPnlEnabled(true);
            //ako postoji vec
            remove(pnlAcademicTitleHistory);
            pack();
        }
        if (mode == FormMode.VIEW_MODE) {
            setTitle("VIEW EMPLOYEE");
            btnEdit.setVisible(true);
            btnSave.setVisible(false);

            employee = (Employee) Session.getInstance().getValue("employee");

            pnlFirstname.setPnlEnabled(false);
            pnlLastname.setPnlEnabled(false);
            pnlBirthday.setPnlEnabled(false);
            pnlDepartment.setPnlEnabled(false);
            pnlAcademicTitle.setPnlEnabled(false);
            pnlEducationTitle.setPnlEnabled(false);

            pnlFirstname.setValue(employee.getFirstname());
            pnlLastname.setValue(employee.getLastname());
            pnlBirthday.setValue(employee.getBirthday());
            pnlDepartment.setValue(employee.getDepartment());
            pnlAcademicTitle.setValue(employee.getAcademicTitle());

            if (employee.getEducationTitle() == null) {
                remove(pnlEducationTitle);
                pack();
            } else {
                pnlEducationTitle.setValue(employee.getEducationTitle());
            }
            titleHistory = Controller.getInstance().getAcademicTitleHistory(employee);
            
            pnlAcademicTitleHistory.getTable().setModel(new TableModelAcademicTitleHistory(titleHistory));
            setCellEditors();
        }
    }

    private void prepareCmb() {
        List<Object> listOfObjects = new ArrayList<>(departments);
        pnlDepartment.fillCmb(listOfObjects);

        listOfObjects = new ArrayList<>(academicTitles);
        pnlAcademicTitle.fillCmb(listOfObjects);

        listOfObjects = new ArrayList<>(educationTitles);
        listOfObjects.add(0, null);
        pnlEducationTitle.fillCmb(listOfObjects);
    }

    private void setCellEditors() {
        TableColumnModel columnModel = pnlAcademicTitleHistory.getTable().getColumnModel();

        TableColumn columnTitle = columnModel.getColumn(0);
        TableColumn columnBeginDate = columnModel.getColumn(1);
        TableColumn columnEndDate = columnModel.getColumn(2);

        List<Object> listOfObjects = new ArrayList<>(academicTitles);
        columnTitle.setCellEditor(new MyCmbCellEditor(listOfObjects));
        columnBeginDate.setCellEditor(new DateCellEditor());
        columnEndDate.setCellEditor(new DateCellEditor());
    }

    private void setValidators() {
        pnlFirstname.setValidator(new RequiredCapitalisedNameValidator());
        pnlLastname.setValidator(new RequiredCapitalisedNameValidator());

    }
    
}
