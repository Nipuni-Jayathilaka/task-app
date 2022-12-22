package lk.ijse.dep9.app.service.custom;

import lk.ijse.dep9.app.dto.UserDTO;
import lk.ijse.dep9.app.entity.User;
import lk.ijse.dep9.app.service.SuperService;

public abstract class UserService extends SuperService {
    public abstract void createNewUserAccount(UserDTO userDTO);

    public abstract UserDTO verifyUser(String username, String password);

    public abstract UserDTO getUserAccountDetails(String username);

    public abstract void updateUserAccountDetails(UserDTO userDTO);
    public abstract void deleteUserAccount(String username);



}


