package model.bo;

import java.security.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity(name="oderm_servico")
public class OrdemServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="data_hora_cadastro")
    private Timestamp dataHoraCadastro;
    @Column(name="data_hora_prevista_inicio")
    private Timestamp dataPrevisaoInicio;
    @Column(name="data_hora_prevista_termino")
    private Timestamp dataHoraPrevisaoTermino;
    @Column
    private String obs;
    @Column
    private char status;
    @JoinColumn
    private Check check;
    @JoinColumn
    private Servico servico;
    @JoinColumn
    private Quarto quarto;

    public OrdemServico(){}
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
