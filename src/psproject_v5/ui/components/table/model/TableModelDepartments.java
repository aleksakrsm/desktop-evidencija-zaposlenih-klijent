/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.ui.components.table.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import psproject_v5.domain.Department;

/**
 *
 * @author aleks
 */
public class TableModelDepartments extends AbstractTableModel{
    private List<Department> departments;
    private Class[] colClasses = {Long.class,String.class,String.class};
    private String[] colNames = {"Id","Name","Short name"};
    public TableModelDepartments(List<Department> departments) {
        this.departments = departments;
    }
    
    @Override
    public int getRowCount() {
        return departments.size();
        }

    @Override
    public int getColumnCount() {
        return 3;
        }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Department department = departments.get(rowIndex);
        switch (columnIndex) {
            case 0: return department.getId();
            case 1: return department.getName();
            case 2: return department.getShortName();
            default: return "n/a";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return colClasses[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    public void add(Department department){
        departments.add(department);
        refreshLastRow();
    }
    
    public void refreshLastRow(){
        fireTableRowsInserted(departments.size() - 1, departments.size());
    }
    
}
