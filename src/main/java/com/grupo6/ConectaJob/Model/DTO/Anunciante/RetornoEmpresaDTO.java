package com.grupo6.ConectaJob.Model.DTO.Anunciante;

import com.grupo6.ConectaJob.Model.Anunciante.Anunciante;
import com.grupo6.ConectaJob.Model.listaAvaliacoesSegundoCargo;
import com.grupo6.ConectaJob.Model.userEmpresa.Empresa;
import com.grupo6.ConectaJob.Model.userEmpresa.servicoPrestado;

import java.util.List;

public class RetornoEmpresaDTO extends RetornoAnuncianteDTO{
    private String cnpjEmpresa;
    private String segmento;
    private List<servicoPrestado> servicoPrestado;
    private listaAvaliacoesSegundoCargo avaliacoesSegundoCargo;

    public RetornoEmpresaDTO(){}

    public RetornoEmpresaDTO(String _nomeAnunciante, String _meioDeComunicacao, String _ftPerfilLink,
                             String _cnpjEmpresa, String _segmento, List<servicoPrestado> _servicoPrestado,
                             listaAvaliacoesSegundoCargo _avaliacoesSegundoCargo

    ){
        super(_nomeAnunciante, _meioDeComunicacao, _ftPerfilLink);
        this.cnpjEmpresa = _cnpjEmpresa;
        this.segmento = _segmento;
        this.servicoPrestado = _servicoPrestado;
        this.avaliacoesSegundoCargo = _avaliacoesSegundoCargo;
    }

    public String getCnpjEmpresa(){
        return this.cnpjEmpresa;
    }

    public void setCnpjEmpresa(String _cnpjEmpresa){
        this.cnpjEmpresa = _cnpjEmpresa;
    }

    public String getSegmento(){
        return this.segmento;
    }

    public void setSegmento(String _segmento){
        this.segmento = _segmento;
    }

    public List<servicoPrestado> getServicoPrestado(){
        return this.servicoPrestado;
    }

    public void setServicoPrestado(List<servicoPrestado> _servicoPrestado){
        this.servicoPrestado = _servicoPrestado;
    }

    public listaAvaliacoesSegundoCargo getAvaliacoesSegundoCargo(){
        return this.avaliacoesSegundoCargo;
    }

    public void setAvaliacoesSegundoCargo(listaAvaliacoesSegundoCargo _listaAvaliacoesSegundoCargo){
        this.avaliacoesSegundoCargo = _listaAvaliacoesSegundoCargo;
    }
}
