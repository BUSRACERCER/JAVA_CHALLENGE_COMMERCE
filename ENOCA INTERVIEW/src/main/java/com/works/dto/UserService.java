package com.works.dto;

import java.util.List;

public interface UserService {
 UserDto createUser(UserDto user);
 List<UserDto> getUsers();
 UserDto getUser();


}
