/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.threads;

import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import psproject_v5.communication.Communication;
import psproject_v5.communication.ObjectWrapper;
import psproject_v5.constants.MyConstants;
import psproject_v5.session.Session;
import psproject_v5.ui.form.FrmMain;
import psproject_v5.ui.form.function.IUpdateView;
import psproject_v5.communication.Operation;
import psproject_v5.communication.Response;
import psproject_v5.domain.AcademicTitle;
import psproject_v5.domain.Department;
import psproject_v5.domain.EducationTitle;
import psproject_v5.domain.Employee;
import psproject_v5.domain.EmployeeAcademicTitle;
import psproject_v5.domain.IEntity;

/**
 *
 * @author aleks
 */
public class ReceiverThread extends Thread {

    private final ObjectWrapper resultObject;

    public ReceiverThread() {
        super();
        this.resultObject = new ObjectWrapper();
        Session.getInstance().addItem(MyConstants.RESULT_OPERATION, resultObject);
        System.out.println(resultObject);
    }

    public ObjectWrapper getResultObject() {

        return resultObject;
    }

    @Override
    public void run() {
        try {
            while (!Communication.getInstance().getSocket().isClosed()) {
                try {
                    handleResponse();
                } catch (Exception ex) {
                    Logger.getLogger(ReceiverThread.class.getName()).log(Level.SEVERE, null, ex);
                    resultObject.setObject(null);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ReceiverThread.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    private void handleResponse() throws Exception {
        Response response = (Response) Communication.getInstance().getReceiver().receive();
        System.out.println();
        Operation operation = response.getOperation();
        System.out.println(response);

        if (response.getException() != null) {
            System.out.println("GRESKA");
            resultObject.setObject(null);
            throw response.getException();
        }
        switch (operation) {
            case TERMINATE -> {
                Communication.getInstance().getReceiver().close();
                Communication.getInstance().getSender().close();
                Communication.getInstance().getSocket().close();
                FrmMain mainForm = (FrmMain) Session.getInstance().getValue("mainForm");
                JOptionPane.showMessageDialog(null, "Server stopped", "Error", JOptionPane.INFORMATION_MESSAGE);
                mainForm.dispatchEvent(new WindowEvent(mainForm, WindowEvent.WINDOW_CLOSING));

            }
            case NEW_ENTITY_NOTIFICATION -> {
                IEntity entity = (IEntity) response.getResult();
                Object o = Session.getInstance().getValue(MyConstants.CURRENT_JDIALOG);
                IUpdateView u;
                if (o != null && (o instanceof IUpdateView)) {
                    u = (IUpdateView) o;
                    u.newEntity((IEntity) entity);
                }
            }
            case UPDATE_ENTITY_NOTIFICATION -> {
                IEntity entity = (IEntity) response.getResult();
                Object o = Session.getInstance().getValue(MyConstants.CURRENT_JDIALOG);
                IUpdateView u;
                if (o != null && (o instanceof IUpdateView)) {
                    u = (IUpdateView) o;
                    u.changedEntity((IEntity) entity);
                }
            }
            case ADD_ENTITY -> {
//              PODESAVANJE ID-a : 
                IEntity entity = (IEntity) response.getResult();
                resultObject.setObject(entity);
            }
            case GET_ALL_EMPLOYEES -> {
                List<Employee> returned = (List<Employee>) response.getResult();
                resultObject.setObject(returned);
            }
            case GET_ALL_EMPLOYEES_FILTERED -> {
                System.out.println("jedan");
                List<Employee> returned = (List<Employee>) response.getResult();
                System.out.println("dva");
                resultObject.setObject(returned);
                System.out.println("tri");
            }
            case GET_ALL_ACADEMIC_TITLES -> {
                List<AcademicTitle> returnedTitles1 = (List<AcademicTitle>) response.getResult();
                resultObject.setObject(returnedTitles1);
            }
            case GET_ALL_EDUCATION_TITLES -> {
                List<EducationTitle> returnedTitles2 = (List<EducationTitle>) response.getResult();
                resultObject.setObject(returnedTitles2);
            }
            case GET_ALL_DEPARTMENTS -> {
                List<Department> returnedDepartments = (List<Department>) response.getResult();
                resultObject.setObject(returnedDepartments);
            }
            case GET_EMPLOYEE_TITLE_HISTORY -> {
                List<EmployeeAcademicTitle> returnedHistory = (List<EmployeeAcademicTitle>) response.getResult();
                resultObject.setObject(returnedHistory);
            }
        }
    }

}
