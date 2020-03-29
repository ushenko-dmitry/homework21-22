package ru.mail.dimaushenko.webmodule.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.mail.dimaushenko.service.model.AddItemDTO;
import ru.mail.dimaushenko.webmodule.utils.PriceUtil;

@Component
public class AddItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return AddItemDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AddItemDTO item = (AddItemDTO) o;
        
        if (!PriceUtil.isValid(item.getPrice())) {
            errors.rejectValue("price", "", "Format error");
        }
        if (item.getShopId().length==0){
            errors.rejectValue("shopId", "", "Shop was not selected");
        }
    }

}
