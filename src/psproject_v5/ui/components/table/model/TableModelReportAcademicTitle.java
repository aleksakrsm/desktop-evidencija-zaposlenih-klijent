/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.ui.components.table.model;

import java.util.List;
import java.util.Objects;
import javax.swing.table.AbstractTableModel;
import psproject_v5.domain.AcademicTitle;
import psproject_v5.domain.Employee;
import psproject_v5.domain.Status;

/**
 *
 * @author aleks
 */
public class TableModelReportAcademicTitle extends AbstractTableModel{
    private List<AcademicTitle> academicTitles;
    private List<Employee> employees;
    private String[] columnNames = {"Title","Number of employees"};
    private Class[] columnClasses = {AcademicTitle.class,Integer.class};

    public TableModelReportAcademicTitle(List<AcademicTitle> academicTitles, List<Employee> employees) {
        this.academicTitles = academicTitles;
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
    
    public Employee getEmployeeById(Long id){
        for (Employee employee : employees) {
            if(employee.getId().equals(id))
                return employee;
        }
        return null;
    }
    public void addEmployee(Employee employee){
        employees.add(employee);
        fireTableDataChanged();
    }
    public void removeEmployee(Employee employee){
        employees.remove(employee);
        fireTableDataChanged();
    }
    public void editEmployee(Employee e){
        for (int i = 0; i < employees.size(); i++) {
            if(Objects.equals(employees.get(i).getId(), e.getId())){
                employees.get(i).setAcademicTitle(e.getAcademicTitle());
                employees.get(i).setStatus(e.getStatus());
                break;
            }
        }
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return academicTitles.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AcademicTitle academicTitle = academicTitles.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return academicTitle;
            case 1: 
                int count = 0;
                for (Employee employee : employees) {
                    if(employee.getAcademicTitle().equals(academicTitle)&&employee.getStatus()==Status.ACTIVE)
                        count++;
                }
                return count;
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }
    
    
}
