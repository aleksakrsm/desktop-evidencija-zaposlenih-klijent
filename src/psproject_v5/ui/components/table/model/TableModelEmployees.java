/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.ui.components.table.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;
import psproject_v5.domain.AcademicTitle;
import psproject_v5.domain.Department;
import psproject_v5.domain.EducationTitle;
import psproject_v5.domain.Employee;

/**
 *
 * @author aleks
 */
public class TableModelEmployees extends AbstractTableModel {

    private List<Employee> employees;
    private List<Employee> updatedEmployees;
    private Class[] columnClasses = {Long.class, String.class, String.class, LocalDate.class, Department.class, AcademicTitle.class, EducationTitle.class};
    private String[] columnNames = {"ID", "Firstname", "Lastname", "Birthday", "Department", "Academic title", "Education title"};
    private boolean editable;

    public TableModelEmployees(List<Employee> employees, boolean editable) {
        this.employees = employees;
        this.editable = editable;
        this.updatedEmployees = new ArrayList<>();
        
    }
    public void editEmployee(Employee e){
        for (int i = 0; i < employees.size(); i++) {
            if(Objects.equals(employees.get(i).getId(), e.getId())){
                employees.get(i).setFirstname(e.getFirstname());
                employees.get(i).setLastname(e.getLastname());
                employees.get(i).setAcademicTitle(e.getAcademicTitle());
                employees.get(i).setEducationTitle(e.getEducationTitle());
                employees.get(i).setDepartment(e.getDepartment());
                employees.get(i).setBirthday(e.getBirthday());
                employees.get(i).setStatus(e.getStatus());
                break;
            }
        }
        fireTableDataChanged();
    }
    public List<Employee> getUpdatedEmployees() {
        return updatedEmployees;
    }
    public void add(Employee employee){
        employees.add(employee);
        refreshLastRow();
    }
    public Employee addEmployee() {
        Employee newEmployee = new Employee();
        employees.add(newEmployee);
        refreshLastRow();
        return newEmployee;
    }
    public void refreshLastRow(){
        fireTableRowsInserted(employees.size() - 1, employees.size());
    }
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee employee = employees.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return employee.getId();
            case 1:
                return employee.getFirstname();
            case 2:
                return employee.getLastname();
            case 3:
                if (employee.getBirthday() == null) {
                    return null;
                }
                return employee.getBirthday().toString();
            case 4:
                if (employee.getDepartment() == null) {
                    return null;
                }
                return employee.getDepartment().getShortName();
            case 5:
                return employee.getAcademicTitle();
            case 6:
                return employee.getEducationTitle();
            default:
                return "n/a";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Employee employee = employees.get(rowIndex);
        if(!updatedEmployees.contains(employee))//dodajem u listu za azuriranje svih zaposlenih
            updatedEmployees.add(employee);
        switch (columnIndex) {
            case 0:
                employee.setId((Long) aValue);
                break;
            case 1:
                employee.setFirstname((String) aValue);
                break;
            case 2:
                employee.setLastname((String) aValue);
                break;
            case 3:
                if (aValue == null) 
                    return;        
                employee.setBirthday(LocalDate.parse(aValue.toString()));
                break;
            case 4:
                employee.setDepartment((Department) aValue);
                break;
            case 5:
                employee.setAcademicTitle((AcademicTitle) aValue);
                break;
            case 6:
                if (aValue == null) {
                    employee.setEducationTitle(null);
                } else {
                    employee.setEducationTitle((EducationTitle) aValue);
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        Employee e  = employees.get(rowIndex);
        if(e.getId()!=null)return false;
        if (columnIndex == 0) {
            return false;
        } else {
            return true && editable;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    public void setColumnWidths(JTable tblEmployees) {
        
        TableColumnModel columnModel = tblEmployees.getColumnModel();
        columnModel.getColumn(0).setMinWidth(25);
        columnModel.getColumn(0).setPreferredWidth(25);
        columnModel.getColumn(0).setMaxWidth(50);

        columnModel.getColumn(1).setMinWidth(60);
        columnModel.getColumn(1).setPreferredWidth(60);
        columnModel.getColumn(1).setMaxWidth(100);

        columnModel.getColumn(2).setMinWidth(100);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(2).setMaxWidth(100);

        columnModel.getColumn(3).setMinWidth(90);
        columnModel.getColumn(3).setPreferredWidth(90);
        columnModel.getColumn(3).setMaxWidth(90);

        columnModel.getColumn(4).setMinWidth(80);
        columnModel.getColumn(4).setPreferredWidth(90);
        columnModel.getColumn(4).setMaxWidth(100);

        columnModel.getColumn(5).setMinWidth(100);
        columnModel.getColumn(5).setPreferredWidth(100);
        columnModel.getColumn(5).setMaxWidth(100);

        columnModel.getColumn(6).setMinWidth(50);
        columnModel.getColumn(6).setPreferredWidth(50);
        columnModel.getColumn(6).setMaxWidth(90);
    }
}
