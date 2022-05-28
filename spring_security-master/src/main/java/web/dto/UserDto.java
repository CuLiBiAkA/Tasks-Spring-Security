package web.dto;

import lombok.Data;
import web.model.Role;

import java.util.Set;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String password;

    private int penisSize;

    private String drove;

    private Set<Role> roles;
}
