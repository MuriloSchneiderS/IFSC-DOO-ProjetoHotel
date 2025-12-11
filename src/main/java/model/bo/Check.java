package model;

import java.security.Timestamp;

public class Check {
    private int id;
    private Timestamp dataHoraCadastro, dataHoraEntrada, dataHoraSaida;
    private String obs;
    private char status;

    public Check(int id, Timestamp dataHoraCadastro, Timestamp dataHoraEntrada, Timestamp dataHoraSaida, String obs, char status) {
        this.id = id;
        this.dataHoraCadastro = dataHoraCadastro;
        this.dataHoraEntrada = dataHoraEntrada;
        this.dataHoraSaida = dataHoraSaida;
        this.obs = obs;
        this.status = status;
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

    public Timestamp getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public void setDataHoraEntrada(Timestamp dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public Timestamp getDataHoraSaida() {
        return dataHoraSaida;
    }

    public void setDataHoraSaida(Timestamp dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
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
    
}
