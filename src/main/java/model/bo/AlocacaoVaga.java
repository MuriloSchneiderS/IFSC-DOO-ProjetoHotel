package model.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name="alocacao_vaga")
public class AlocacaoVaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String obs;
    @Column
    private char status;
    @JoinColumn(name="vaga_estacionamento")
    @ManyToOne
    private VagaEstacionamento vagaEstacionamento;
    @JoinColumn
    @ManyToOne
    private Veiculo veiculo;
    @JoinColumn
    @OneToOne
    private Check check;

    public AlocacaoVaga(){}
    public AlocacaoVaga(int id, String obs, char status, VagaEstacionamento vagaEstacionamento, Veiculo veiculo, Check check) {
        this.id = id;
        this.obs = obs;
        this.status = status;
        this.vagaEstacionamento = vagaEstacionamento;
        this.veiculo = veiculo;
        this.check = check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public VagaEstacionamento getVagaEstacionamento() {
        return vagaEstacionamento;
    }

    public void setVagaEstacionamento(VagaEstacionamento vagaEstacionamento) {
        this.vagaEstacionamento = vagaEstacionamento;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }
    
    
}
