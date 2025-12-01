package com.grupo6.ConectaJob.Model.notificacao;

import com.grupo6.ConectaJob.Model.TempoSubdivicoes.IntervaloTempo;
import com.grupo6.ConectaJob.Model.TempoSubdivicoes.IntervaloTempoSalvosubCategorias;
import com.grupo6.ConectaJob.Model.cargo.Cargo;
import com.grupo6.ConectaJob.Model.userEmpresa.servicoPrestado;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificacaoVagaInfo {

    private servicoPrestado servicoPrestadoNaOcasiao;
    private Cargo cargo;
    private String meioDeComunicacao;
    private String equipamentoDeSeguranca;
    private String pagamento;
    private IntervaloTempo jornadaAmpla;
    private IntervaloTempoSalvosubCategorias jornandaDetalhada;

    public NotificacaoVagaInfo(servicoPrestado servicoPrestadoNaOcasiao, Cargo cargo, String meioDeComunicacao,
                               String equipamentoDeSeguranca, String pagamento, IntervaloTempo jornadaAmpla,
                               IntervaloTempoSalvosubCategorias jornandaDetalhada
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
