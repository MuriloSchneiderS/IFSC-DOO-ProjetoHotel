package model;

public class VagaEstacionamento {
    private int id;
    private String descricao, obs;
    private float metragemVaga;
    private char status;
    private AlocacaoVaga alocacao;
    
    public VagaEstacionamento(){}
    public VagaEstacionamento(int id, String descricao, String obs, float metragemVaga, char status) {
        this.id = id;
        this.descricao = descricao;
        this.obs = obs;
        this.metragemVaga = metragemVaga;
        this.status = status;
    }
    
    public void alocarVaga(AlocacaoVaga alocacao){
        this.alocacao = alocacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public float getMetragemVaga() {
        return metragemVaga;
    }

    public void setMetragemVaga(float metragemVaga) {
        this.metragemVaga = metragemVaga;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
    
}
