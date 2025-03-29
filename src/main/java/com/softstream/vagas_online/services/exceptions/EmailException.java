package com.softstream.vagas_online.services.exceptions;

import java.io.Serial;

public class EmailException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

	public EmailException(String msg) {
		super(msg);
	}
}
