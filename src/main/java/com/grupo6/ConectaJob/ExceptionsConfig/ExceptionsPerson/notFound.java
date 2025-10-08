package com.grupo6.ConectaJob.ExceptionsConfig.ExceptionsPerson;

public class notFound extends RuntimeException {
    public notFound(String detalhamentoMensagem) {
        super (detalhamentoMensagem);
    }
}
