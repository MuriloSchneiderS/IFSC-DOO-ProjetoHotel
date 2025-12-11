package model;

import java.security.Timestamp;

public class OrdemServico {
    private int id;
    private Timestamp dataHoraCadastro, dataPrevisaoInicio, dataHoraPrevisaoTermino;
    private String obs;
    private char status;
    private Servico servico;
    private Quarto quarto;
    private Check check;

    public OrdemServico(int id, Timestamp dataHoraCadastro, Timestamp dataPrevisaoInicio, Timestamp dataHoraPrevisaoTermino, String obs, char status, Servico servico, Check check) {
        this.id = id;
        this.dataHoraCadastro = dataHoraCadastro;
        this.dataPrevisaoInicio = dataPrevisaoInicio;
        this.dataHoraPrevisaoTermino = dataHoraPrevisaoTermino;
        this.obs = obs;
        this.status = status;
        this.servico = servico;
        this.check = check;
    }
    public OrdemServico(int id, Timestamp dataHoraCadastro, Timestamp dataPrevisaoInicio, Timestamp dataHoraPrevisaoTermino, String obs, char status, Servico servico, Quarto quarto, Check check) {
        this.id = id;
        this.dataHoraCadastro = dataHoraCadastro;
        this.dataPrevisaoInicio = dataPrevisaoInicio;
        this.dataHoraPrevisaoTermino = dataHoraPrevisaoTermino;
        this.obs = obs;
        this.status = status;
        this.servico = servico;
        this.quarto = quarto;
        this.check = check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(Timestamp dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public Timestamp getDataPrevisaoInicio() {
        return dataPrevisaoInicio;
    }

    public void setDataPrevisaoInicio(Timestamp dataPrevisaoInicio) {
        this.dataPrevisaoInicio = dataPrevisaoInicio;
    }

    public Timestamp getDataHoraPrevisaoTermino() {
        return dataHoraPrevisaoTermino;
    }

    public void setDataHoraPrevisaoTermino(Timestamp dataHoraPrevisaoTermino) {
        this.dataHoraPrevisaoTermino = dataHoraPrevisaoTermino;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }
    
    
}
