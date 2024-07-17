package com.learning.dto;

public record UserRequest(
		String userId,
		String username,
		String password,
		String isEnabled
		) {

}
