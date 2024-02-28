/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.ui.components.validator.impl;

import psproject_v5.exception.ValidatorException;
import java.util.regex.*;
import psproject_v5.ui.components.validator.IValidator;

/**
 *
 * @author aleks
 */
public class RequiredDepartmentNameValidator implements IValidator {

    @Override
    public void validate(Object input) throws ValidatorException {
        if (input == null) {
            throw new ValidatorException("Null parametar!");
        }
        String name = input.toString();
        if (name.isEmpty()) {
            throw new ValidatorException("unet je prazan string");
        }
        Pattern pattern = Pattern.compile("[[A-Za-z]{1,15}\\s]+");
        Matcher matcher = pattern.matcher(name);
        boolean matchFound = matcher.matches();
        if (matchFound) {
            System.out.println("unos je ispravan: " + name);
        } else {
            throw new ValidatorException("Unos nije ispravan!!! [[A-Za-z]{1,15}\\s]+");
        }
    }

}
