package com.works.services;

import com.works.dto.UserDto;
import com.works.dto.UserService;
import com.works.entities.User;
import com.works.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserManagementService implements UserService {
private final UserRepository userRepository;
private final ModelMapper modelMapper;
@Override
    public UserDto createUser(UserDto userDto){
    User user = modelMapper.map(userDto, User.class);
    user.setCreatedBy("User");
    return modelMapper.map(userRepository.save(user), UserDto.class);

}
@Override
    public List<UserDto> getUsers(){
    List<User> users =userRepository.findAll();
    List<UserDto> dtos = users.stream().map(customer -> modelMapper.map(customer, UserDto.class)).collect(Collectors.toList());

    return dtos;
}

    @Override
    public UserDto getUser() {
        return null;
    }
}
