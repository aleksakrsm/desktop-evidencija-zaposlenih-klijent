/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package psproject_v5.ui.components.validator;

import psproject_v5.exception.ValidatorException;

/**
 *
 * @author aleks
 */
public interface IValidator {

    void validate(Object input) throws ValidatorException;
}
