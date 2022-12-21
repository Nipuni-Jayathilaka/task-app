package lk.ijse.dep9.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    @NotBlank(message = "Full name can't be empty or null")
    @Pattern(regexp = "^[a-zA-Z ]+$",message = "Invalid name")
    private String fullName;
    @NotBlank (message = "Username can't be empty or null")
    private String username;
    @NotEmpty(message = "Password can't be empty or null")
    @Length(min=3, message = "Password should be at least 3 character long")
    private String password;
}
