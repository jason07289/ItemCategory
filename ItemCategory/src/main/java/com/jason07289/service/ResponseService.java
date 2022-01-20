package com.jason07289.service;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ResponseService {
	public ResponseEntity<Map<String, Object>> handleSuccess(Object data);
	public ResponseEntity<Map<String, Object>> handleFail(Object data, HttpStatus status);
}
