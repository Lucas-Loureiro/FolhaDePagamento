package br.org.serratec.backend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(CpfException.class)
	public ResponseEntity<Object> handleCpfException(CpfException ex){
		CpfException cpfException = new CpfException(ex.getMessage());
		return ResponseEntity.unprocessableEntity().body(cpfException);
	}
	@ExceptionHandler(DataException.class)
	public ResponseEntity<Object> handleDataException(DataException ex){
		DataException dataException = new DataException(ex.getMessage());
		return ResponseEntity.unprocessableEntity().body(dataException);
	}
}
