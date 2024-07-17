package com.learning.dto;

import lombok.Builder;

@Builder
public record ResultMessage(
		Boolean isSuccessFul,
		Integer code,
		String message
		) {

}
