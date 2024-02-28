/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.ui.components.table.cellEditors;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import psproject_v5.ui.form.FrmDatePicker;

/**
 *
 * @author aleks
 */
public class DateCellEditor extends AbstractCellEditor implements TableCellEditor {

    private LocalDate currentDate;
    private JButton button;
    private FrmDatePicker frmDatePicker;

    public DateCellEditor() {
        button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmDatePicker = new FrmDatePicker(null, true);
                frmDatePicker.setPreviousDate(currentDate);
                frmDatePicker.setVisible(true);

                currentDate = frmDatePicker.getDate();
                frmDatePicker = null;
                fireEditingStopped();
            }
        });
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
    public Object getCellEditorValue() {
        return currentDate;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value == null) {
            currentDate = null;
        } else {
            currentDate = LocalDate.parse(value.toString());
        }
        return button;
    }

}
