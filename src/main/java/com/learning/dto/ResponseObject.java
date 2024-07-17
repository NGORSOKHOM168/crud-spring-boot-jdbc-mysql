package com.learning.dto;

import lombok.Builder;

@Builder
public record ResponseObject <T> (
		ResultMessage resultMessage, 
		T payload
		) {

}
