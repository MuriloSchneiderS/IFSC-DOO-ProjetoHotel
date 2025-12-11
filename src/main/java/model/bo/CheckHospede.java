package model;

public class CheckHospede {
    private int id;
    private String tipoHospede, obs;
    private char status;
    private Hospede hospede;
    private Check check;

    public CheckHospede(int id, String tipoHospede, String obs, char status, Hospede hospede, Check check) {
        this.id = id;
        this.tipoHospede = tipoHospede;
        this.obs = obs;
        this.status = status;
        this.hospede = hospede;
        this.check = check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoHospede() {
        return tipoHospede;
    }

    public void setTipoHospede(String tipoHospede) {
        this.tipoHospede = tipoHospede;
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

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }
    
}
