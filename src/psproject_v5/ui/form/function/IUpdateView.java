/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package psproject_v5.ui.form.function;

import java.util.List;
import psproject_v5.domain.IEntity;

/**
 *
 * @author aleks
 */
public interface IUpdateView {
    default public void newEntity(IEntity o) throws Exception{
        
    }
    default public void changedEntity(IEntity o) throws Exception{
        
    }
    default public void deletedEntity(IEntity o) throws Exception{
        
    }
    default public void changedList(List<IEntity> list) throws Exception{
        
    }
}
