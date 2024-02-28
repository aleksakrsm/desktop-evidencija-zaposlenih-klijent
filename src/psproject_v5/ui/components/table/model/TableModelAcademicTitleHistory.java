/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.ui.components.table.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;
import psproject_v5.domain.AcademicTitle;
import psproject_v5.domain.Employee;
import psproject_v5.domain.EmployeeAcademicTitle;
import psproject_v5.domain.IEntity;
import psproject_v5.session.Session;

/**
 *
 * @author aleks
 */
public class TableModelAcademicTitleHistory extends AbstractTableModel {

    private List<EmployeeAcademicTitle> academicTitles;
    private List<IEntity> unsavedAcademicTitles;
    private Class[] colClasses = {AcademicTitle.class, LocalDate.class, LocalDate.class};
    private String[] colNames = {"Academic title", "Begin date", "End date"};

    public EmployeeAcademicTitle getLatestEntry() throws Exception{
        if(academicTitles==null)
            throw new Exception("invalid value");
        if(academicTitles.isEmpty())
            return null;
        return academicTitles.get(academicTitles.size()-1);
    }
    public TableModelAcademicTitleHistory(List<EmployeeAcademicTitle> academicTitles) throws Exception {
        if(academicTitles == null)
            throw new Exception("parameter mustn't be null");
        this.academicTitles = academicTitles;
        unsavedAcademicTitles = new ArrayList<>();
    }

    public List<EmployeeAcademicTitle> getAcademicTitles() {
        return academicTitles;
    }

    public void setAcademicTitles(List<EmployeeAcademicTitle> academicTitles) {
        this.academicTitles = academicTitles;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return academicTitles.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        EmployeeAcademicTitle title = academicTitles.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return title.getAcademicTitle();
            case 1:
                return title.getBeginDate();
            case 2:
                return title.getEndDate();
            default:
                return "n/a";
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

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        EmployeeAcademicTitle title = academicTitles.get(rowIndex);
        if(unsavedAcademicTitles.contains(title))return true;
        if (title.getAcademicTitle() == null) {
            return true;
        } else if (columnIndex == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        EmployeeAcademicTitle title = academicTitles.get(rowIndex);
        //PROVERITI DA LI JE VEC U LISTI OVAJ ITEM
        if (!unsavedAcademicTitles.contains(title)) {
            unsavedAcademicTitles.add(title);
        }
        switch (columnIndex) {
            case 0:
                title.setAcademicTitle((AcademicTitle) aValue);
                break;
            case 1:
                if (aValue == null) {
                    title.setBeginDate(null);
                } else {
                    title.setBeginDate(LocalDate.parse(aValue.toString()));
                }
                break;
            case 2:
                if (aValue == null) {
                    title.setEndDate(null);
                } else {
                    title.setEndDate(LocalDate.parse(aValue.toString()));
                }
                break;
            default:
                break;
        }
        JButton btnSave = (JButton) Session.getInstance().getValue("btnSavePanelHistory");
        btnSave.setEnabled(true);
    }
    public void remove(EmployeeAcademicTitle title){
        academicTitles.remove(title);
        fireTableDataChanged();
    }
    public void add() {
        EmployeeAcademicTitle title = new EmployeeAcademicTitle();
        title.setEmployee((Employee) Session.getInstance().getValue("employee"));
        Session.getInstance().addItem("academicTitleHistoryItem", title);
        academicTitles.add(title);
        unsavedAcademicTitles.add(title);
        refreshLastRow();
    }

    public List<IEntity> getUnsavedAcademicTitles() {
        return unsavedAcademicTitles;
    }

    public void refreshLastRow() {
        fireTableRowsInserted(academicTitles.size() - 1, academicTitles.size());
    }

}
