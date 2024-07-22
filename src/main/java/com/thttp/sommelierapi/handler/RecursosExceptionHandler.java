package com.thttp.sommelierapi.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.thttp.sommelierapi.model.DetalhesErro;
import com.thttp.sommelierapi.service.exceptions.RecursoNaoEncontradoException;

@ControllerAdvice
public class RecursosExceptionHandler {
	
	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handlerRecursoNaoEncontrado(RecursoNaoEncontradoException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404L);
		erro.setTitulo(e.getMessage());
		erro.setMensagemDesenvolvedor("Confira se a URI/ID ou outros parâmetros estão corretos");
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);		
	}

}
