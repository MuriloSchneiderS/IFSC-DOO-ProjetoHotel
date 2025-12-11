package model.bo;

import java.util.List;
import java.security.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Caixa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="valor_de_abertura")
    private float valorDeAbertura;
    @Column(name="valor_de_fechamento")
    private float valorDeFechamento;
    @Column(name="data_hora_abertura")
    private Timestamp dataHoraAbertura;
    @Column(name="data_hora_fechamento")
    private Timestamp dataHoraFechamento;
    @Column
    private String obs;
    @Column
    private char status;
    @JoinColumn
    @OneToMany
    private List<MovimentoCaixa> movimentacoes;

    public Caixa(){}
    public Caixa(int id, float valorDeAbertura, float valorDeFechamento, Timestamp dataHoraAbertura, Timestamp dataHoraFechamento, String obs, char status) {
        this.id = id;
        this.valorDeAbertura = valorDeAbertura;
        this.valorDeFechamento = valorDeFechamento;
        this.dataHoraAbertura = dataHoraAbertura;
        this.dataHoraFechamento = dataHoraFechamento;
        this.obs = obs;
        this.status = status;
    }
    public void adicionarMovimentacao(MovimentoCaixa movimentacao){
        this.movimentacoes.add(movimentacao);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValorDeAbertura() {
        return valorDeAbertura;
    }

    public void setValorDeAbertura(float valorDeAbertura) {
        this.valorDeAbertura = valorDeAbertura;
    }

    public float getValorDeFechamento() {
        return valorDeFechamento;
    }

    public void setValorDeFechamento(float valorDeFechamento) {
        this.valorDeFechamento = valorDeFechamento;
    }

    public Timestamp getDataHoraAbertura() {
        return dataHoraAbertura;
    }

    public void setDataHoraAbertura(Timestamp dataHoraAbertura) {
        this.dataHoraAbertura = dataHoraAbertura;
    }

    public Timestamp getDataHoraFechamento() {
        return dataHoraFechamento;
    }

    public void setDataHoraFechamento(Timestamp dataHoraFechamento) {
        this.dataHoraFechamento = dataHoraFechamento;
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
