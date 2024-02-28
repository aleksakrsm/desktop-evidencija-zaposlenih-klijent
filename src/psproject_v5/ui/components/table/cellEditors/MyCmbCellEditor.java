/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.ui.components.table.cellEditors;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author aleks
 */
public class MyCmbCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private JComboBox cmbChooser;
    private Object currentChoice;

    public MyCmbCellEditor(List<Object> list) {
        this.cmbChooser = new JComboBox(list.toArray());
    }

    @Override
    public Object getCellEditorValue() {
        return currentChoice;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        cmbChooser.addActionListener(this);
        if (isSelected) {
            cmbChooser.setBackground(table.getSelectionBackground());
        } else {
            cmbChooser.setBackground(table.getSelectionForeground());
        }
        currentChoice = value;
        return cmbChooser;
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        if (super.isCellEditable(e)) {
            if (e instanceof MouseEvent) {
                MouseEvent me = (MouseEvent) e;

                return me.getClickCount() >= 2;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.currentChoice = cmbChooser.getSelectedItem();
        fireEditingStopped();
    }
}
