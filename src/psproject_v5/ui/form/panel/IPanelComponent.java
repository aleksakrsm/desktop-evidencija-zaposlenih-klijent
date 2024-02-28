/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package psproject_v5.ui.form.panel;

/**
 *
 * @author aleks
 */
public interface IPanelComponent {
    
    void setStaticText(String staticTxt);
    void setErrorText(String errorTxt);
    void preparePanel();
    Object getValue() throws Exception;
    void setValue(Object value) ;
    void setPnlEnabled(boolean enabled);
    void setPnlVisible(boolean visible);
}
