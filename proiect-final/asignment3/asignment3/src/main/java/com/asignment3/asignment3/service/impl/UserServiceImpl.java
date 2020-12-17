package com.asignment3.asignment3.service.impl;

import com.asignment3.asignment3.model.dto.UserDTO;
import com.asignment3.asignment3.model.entity.Doctor;
import com.asignment3.asignment3.model.entity.Patient;
import com.asignment3.asignment3.model.entity.User;
import com.asignment3.asignment3.model.enumeration.UserRole;
import com.asignment3.asignment3.model.mapper.UserMapper;
import com.asignment3.asignment3.repository.DoctorRepository;
import com.asignment3.asignment3.repository.PatientRepository;
import com.asignment3.asignment3.repository.UserRepository;
import com.asignment3.asignment3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PatientRepository patientRepository,UserMapper userMapper) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.userMapper=userMapper;
    }

    @Override
    public UserDTO register(UserDTO userDTO) {
        User user;
        Patient patient;
        if(userDTO.getId() != null){
            user = userRepository.findById(userDTO.getId())
                    .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find user with ID: " + userDTO.getId()); } );
            patient = patientRepository.findByUserId(userDTO.getId());
        }else{
            user = new User();
            userDTO.setRole(UserRole.PATIENT);
            patient =  new Patient();
        }

        userMapper.toEntityUpdate(user,userDTO);
        User result = userRepository.save(user);
        if(patient.getUser()==null) patient.setUser(result);
        patientRepository.save(patient);

        return userMapper.toDTO(result);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user;
        if(userDTO.getId()!=null){
            user = userRepository.findById(userDTO.getId())
                    .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find bank account with ID: " + userDTO.getId()); } );

        }else{
            user = new User();
        }
        userMapper.toEntityUpdate(user,userDTO);
        return userMapper.toDTO(userRepository.save(user));
    }


    @Override
    public void deleteUser(Long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find user with ID: " + id); } );

        userRepository.delete(user);
    }

    @Override
    public List<UserDTO> findAllUsersDTO() {
        return userRepository.findAll().
                stream().
                map(userMapper::toDTO).
                collect(Collectors.toList());

    }

    @Override
    public UserDTO logIn(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username,password);
        if(user==null){
            return null;
        }else{
            UserDTO uu = userMapper.toDTO(user);
            return uu;
        }

    }

    @Override
    public UserDTO findByUsername(String username) {
        return userMapper.toDTO(userRepository.findByUsername(username));
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find user with ID: " + id); } );
        return userMapper.toDTO(user);
    }
}
