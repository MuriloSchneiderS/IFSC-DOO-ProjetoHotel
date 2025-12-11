package model.bo;

import java.security.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Check {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="data_hora_cadastro")
    private Timestamp dataHoraCadastro;
    @Column(name="data_hora_entrada")
    private Timestamp dataHoraEntrada;
    @Column(name="data_hora_saida")
    private Timestamp dataHoraSaida;
    @Column
    private String obs;
    @Column
    private char status;

    public Check(){}
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
