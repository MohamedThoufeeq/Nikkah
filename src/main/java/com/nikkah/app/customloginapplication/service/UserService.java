package com.nikkah.app.customloginapplication.service;

import com.nikkah.app.customloginapplication.dto.UserDto;
import com.nikkah.app.customloginapplication.model.User;

public interface UserService {
	
	User findByUsername(String username);
	User save (UserDto userDto);
	User save (User user);

}
