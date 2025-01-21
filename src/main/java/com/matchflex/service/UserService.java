package com.matchflex.service;

import com.matchflex.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
    UserDTO getUserByEmail(String email);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    List<UserDTO> searchUsersByName(String name);
    boolean isEmailTaken(String email);
    boolean isPhoneNumberTaken(String phoneNumber);
}

