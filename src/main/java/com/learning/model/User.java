package com.learning.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

	private String userId;
	private String username;
	private String password;
	private String isEnabled;
		
}
