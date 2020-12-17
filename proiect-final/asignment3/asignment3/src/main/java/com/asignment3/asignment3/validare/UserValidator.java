package com.asignment3.asignment3.validare;
import com.asignment3.asignment3.model.dto.UserDTO;
import com.asignment3.asignment3.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class UserValidator {

    private final UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateUser(UserDTO userDTO, BindingResult result){
        if(!StringUtils.containsOnly(userDTO.getPhone(),"0123456789"))
            result.rejectValue("phone","Phone is not valid");
    }
}
