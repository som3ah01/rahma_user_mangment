package com.taskMangament.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class InvalidJwtAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = -761503632186596342L;

	public InvalidJwtAuthenticationException(String e) {
        super(e);
    }
}
