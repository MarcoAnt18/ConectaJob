package com.grupo6.ConectaJob.Model.notificacao;

import com.grupo6.ConectaJob.Model.TempoSubdivicoes.intervaloTempo;
import com.grupo6.ConectaJob.Model.TempoSubdivicoes.intervaloTempoSalvosubCategorias;
import com.grupo6.ConectaJob.Model.cargo.cargo;
import com.grupo6.ConectaJob.Model.userEmpresa.servicoPrestado;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificacaoVagaInfo {

    private servicoPrestado servicoPrestadoNaOcasiao;
    private cargo cargo;
    private String meioDeComunicacao;
    private String equipamentoDeSeguranca;
    private String pagamento;
    private intervaloTempo jornadaAmpla;
    private intervaloTempoSalvosubCategorias jornandaDetalhada;

    public NotificacaoVagaInfo(servicoPrestado servicoPrestadoNaOcasiao,cargo cargo, String meioDeComunicacao,
                               String equipamentoDeSeguranca, String pagamento, intervaloTempo jornadaAmpla,
                               intervaloTempoSalvosubCategorias jornandaDetalhada
    ){

        this.servicoPrestadoNaOcasiao = servicoPrestadoNaOcasiao;
        this.cargo = cargo;
        this.meioDeComunicacao = meioDeComunicacao;
        this.equipamentoDeSeguranca = equipamentoDeSeguranca;
        this.pagamento = pagamento;
        this.jornadaAmpla = jornadaAmpla;
        this.jornandaDetalhada = jornandaDetalhada;
    }
}
