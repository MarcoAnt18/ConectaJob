package com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson;

public class DuplicateEntityException extends RuntimeException {
  public DuplicateEntityException(String detalhamentoMensagem) {
    super(detalhamentoMensagem);
  }
}