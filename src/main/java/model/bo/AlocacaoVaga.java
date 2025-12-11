package model;

public class AlocacaoVaga {
    private int id;
    private String obs;
    private char status;
    private VagaEstacionamento vagaEstacionamento;
    private Veiculo veiculo;
    private Check check;

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
