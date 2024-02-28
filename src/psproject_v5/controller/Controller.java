/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import psproject_v5.communication.Communication;
import psproject_v5.communication.ObjectWrapper;
import psproject_v5.communication.Operation;
import psproject_v5.communication.Request;
import psproject_v5.constants.MyConstants;
import psproject_v5.domain.AcademicTitle;
import psproject_v5.domain.Department;
import psproject_v5.domain.EducationTitle;
import psproject_v5.domain.Employee;
import psproject_v5.domain.EmployeeAcademicTitle;
import psproject_v5.domain.IEntity;
import psproject_v5.session.Session;

/**
 *
 * @author aleks
 */
public class Controller {
    private static Controller instance;
    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }


    public List<Department> getAllDepartments() throws Exception {
        try {
            Request request = new Request(null,Operation.GET_ALL_DEPARTMENTS);
            Communication.getInstance().getSender().send(request);
            List<Department> departments = (List<Department>) ((ObjectWrapper)Session.getInstance().getValue(MyConstants.RESULT_OPERATION)).getObject();
            return departments;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<Employee> getAllEmployees(Department department) throws Exception {
        try {
            Request request = new Request(department, Operation.GET_ALL_EMPLOYEES_FILTERED);
            Communication.getInstance().getSender().send(request);
            List<Employee> employees = (List<Employee>)((ObjectWrapper)Session.getInstance().getValue(MyConstants.RESULT_OPERATION)).getObject();
            return employees;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<Employee> getAllEmployees() throws Exception {
        try {
            Request request = new Request(null,Operation.GET_ALL_EMPLOYEES);
            Communication.getInstance().getSender().send(request);
            List<Employee> employees = (List<Employee>)((ObjectWrapper)Session.getInstance().getValue(MyConstants.RESULT_OPERATION)).getObject();
            return employees;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<AcademicTitle> getAllAcademicTitles() throws Exception {
        try {
            Request request = new Request(null,Operation.GET_ALL_ACADEMIC_TITLES);
            Communication.getInstance().getSender().send(request);
            List<AcademicTitle> academicTitles = (List<AcademicTitle>) ((ObjectWrapper)Session.getInstance().getValue(MyConstants.RESULT_OPERATION)).getObject();
            return academicTitles;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<EducationTitle> getAllEducationTitles() throws Exception {
        try {
            Request request = new Request(null,Operation.GET_ALL_EDUCATION_TITLES);
            Communication.getInstance().getSender().send(request);
            List<EducationTitle> educationTitles = (List<EducationTitle>) ((ObjectWrapper)Session.getInstance().getValue(MyConstants.RESULT_OPERATION)).getObject();
            return educationTitles;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void add(IEntity entity) throws Exception {
        try {
            Request request = new Request(entity,Operation.ADD_ENTITY);
            Communication.getInstance().getSender().send(request);
            IEntity newE = (IEntity) ((ObjectWrapper)Session.getInstance().getValue(MyConstants.RESULT_OPERATION)).getObject();
            entity.setId(newE.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    public void delete(IEntity entity) throws Exception {
        try {
            Request request = new Request(entity,Operation.DELETE_ENTITY);
            Communication.getInstance().getSender().send(request);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void update(IEntity entity) throws Exception {
        try {
            Request request = new Request(entity,Operation.UPDATE_ENTITY);
            Communication.getInstance().getSender().send(request);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<EmployeeAcademicTitle> getAcademicTitleHistory(Employee employee) throws Exception {
        try {
            Request request = new Request(employee,Operation.GET_EMPLOYEE_TITLE_HISTORY);
            Communication.getInstance().getSender().send(request);
            List<EmployeeAcademicTitle> titleHistory = (List<EmployeeAcademicTitle>) ((ObjectWrapper)Session.getInstance().getValue(MyConstants.RESULT_OPERATION)).getObject();
            titleHistory.sort(new Comparator<EmployeeAcademicTitle>() {
                @Override
                public int compare(EmployeeAcademicTitle o1, EmployeeAcademicTitle o2) {
                    if(o1.getBeginDate()!=null&&o2.getBeginDate()!=null)
                        return o1.getBeginDate().compareTo(o2.getBeginDate());
                    if(o1.getBeginDate()==null&&o2.getBeginDate()!=null)
                        return -1;
                    if(o2.getBeginDate()==null&&o1.getBeginDate()!=null)
                        return 1;
                    if(o1.getBeginDate()==null&&o2.getBeginDate()==null)
                        return 0;
                    else
                        return 0;
                }
            });
            return titleHistory;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    public void saveOrUpdateList(List<IEntity> list) throws Exception{
        try {
            Request request = new Request(list,Operation.SAVE_OR_UPDATE_ELEMENTS);
//            System.out.println("+++++++"+request.getOperation());
//            System.out.println("+++++++"+list);
            Communication.getInstance().getSender().send(request);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
