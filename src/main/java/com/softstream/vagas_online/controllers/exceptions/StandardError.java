package com.softstream.vagas_online.controllers.exceptions;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandardError implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

}
