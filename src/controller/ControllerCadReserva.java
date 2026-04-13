package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Check;
import model.CheckHospede;
import model.CheckQuarto;
import model.Hospede;
import model.Quarto;
import model.Reserva;
import view.TelaCadastroReserva;

public class ControllerCadReserva implements ActionListener {

    TelaCadastroReserva telaCadastroReserva;
    public static int codigo;

    public ControllerCadReserva(TelaCadastroReserva telaCadastroReserva) {

        this.telaCadastroReserva = telaCadastroReserva;

        this.telaCadastroReserva.getjButtonNovo().addActionListener(this);
        this.telaCadastroReserva.getjButtonGravar().addActionListener(this);
        this.telaCadastroReserva.getjButtonCancelar().addActionListener(this);
        this.telaCadastroReserva.getjButtonSair().addActionListener(this);

        //Carregar o campo Quarto
        List<Quarto> quartos = service.QuartoService.Carregar("descricao", "%");
        for (Quarto quarto : quartos) {
            this.telaCadastroReserva.getjComboBoxQuarto().addItem(quarto.getDescricao());
        }
        //Carregar o campo Hospede
        List<Hospede> hospedes = service.HospedeService.Carregar("nome", "%");
        for (Hospede hospede : hospedes) {
            this.telaCadastroReserva.getjComboBoxHospede().addItem(hospede.getNome());
        }

        if (codigo == 0 && telaCadastroReserva.getjTextFieldId().getText().isEmpty()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroReserva.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroReserva.getjPanelDados(), false);
        } else {//rotina de carga de reserva
            codigo = Integer.parseInt(telaCadastroReserva.getjTextFieldId().getText());
            utilities.Utilities.ativaDesativa(this.telaCadastroReserva.getjPanelBotoes(), false);

            this.telaCadastroReserva.getjTextFieldId().setText(codigo + "");
            this.telaCadastroReserva.getjTextFieldId().setEnabled(false);
            
            java.util.Date dataAtual = new Date();
            SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
            String novaData = dataFormatada.format(dataAtual);
            this.telaCadastroReserva.getjFormattedTextFieldDataCadastro().setText(novaData);
            this.telaCadastroReserva.getjFormattedTextFieldDataCadastro().setEnabled(false);

            Reserva reserva = service.ReservaService.Carregar(codigo);
            Check check = service.CheckService.Carregar(reserva.getCheck().get(0).getId());
            CheckHospede checkHospede = service.CheckHospedeService.Carregar("check_id", check.getId() + "").get(0);//check_hospede tem check_id
            Hospede hospede = service.HospedeService.Carregar(checkHospede.getHospede().getId());//check_hospede tem hospede_id
            CheckQuarto checkQuarto = service.CheckQuartoService.Carregar(check.getCheckQuarto().getId());//check tem check_quarto_id
            Quarto quarto = service.QuartoService.Carregar(checkQuarto.getQuarto().getId());//checkQuarto tem quarto_id

            this.telaCadastroReserva.getjFormattedTextFieldDataPrevistaEntrada().setText(checkQuarto.getDataHoraInicio());
            this.telaCadastroReserva.getjFormattedTextFieldDataPrevistaSaida().setText(checkQuarto.getDataHoraFim());
            this.telaCadastroReserva.getjComboBoxQuarto().setSelectedItem(quarto.getDescricao());
            this.telaCadastroReserva.getjComboBoxHospede().setSelectedItem(hospede.getNome());
            this.telaCadastroReserva.getjTextFieldObs().setText(reserva.getObs());

            this.telaCadastroReserva.getjTextFieldObs().requestFocus();
        }
        this.telaCadastroReserva.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        //Botão Novo
        if (evento.getSource() == this.telaCadastroReserva.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroReserva.getjPanelBotoes(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroReserva.getjPanelDados(), true);
            this.telaCadastroReserva.getjTextFieldId().setEnabled(false);
            java.util.Date dataAtual = new Date();
            SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
            String novaData = dataFormatada.format(dataAtual);
            this.telaCadastroReserva.getjFormattedTextFieldDataCadastro().setText(novaData);
            this.telaCadastroReserva.getjFormattedTextFieldDataCadastro().setEnabled(false);

        //Botão Gravar
        } else if (evento.getSource() == this.telaCadastroReserva.getjButtonGravar()) {
            if (utilities.Utilities.todosOsCamposPreenchidos(this.telaCadastroReserva.getjPanelDados())) {
                //Inclusão
                if (codigo == 0 || this.telaCadastroReserva.getjTextFieldId().getText().trim().equalsIgnoreCase("")) {
                    Reserva reserva = new Reserva();
                    
                    //Salvar o Quarto selecionado
                    Quarto quarto = service.QuartoService.Carregar("descricao", (String) this.telaCadastroReserva.getjComboBoxQuarto().getSelectedItem()).get(0);

                    //Cria o check_quarto com base na reserva e retorna o que foi criado para poder criar o check
                    CheckQuarto checkQuarto = new CheckQuarto(//Check quarto armazena a datahora de inicio e fim para quando for editar
                            this.telaCadastroReserva.getjFormattedTextFieldDataPrevistaEntrada().getText(),
                            this.telaCadastroReserva.getjFormattedTextFieldDataPrevistaSaida().getText(),
                            this.telaCadastroReserva.getjTextFieldObs().getText(),
                            'A',
                            quarto
                    );
                    service.CheckQuartoService.Criar(checkQuarto);
                    checkQuarto = service.CheckQuartoService.Carregar(-1);

                    //Criar o check com o check_quarto
                    Check check = new Check(
                            this.telaCadastroReserva.getjFormattedTextFieldDataCadastro().getText(),
                            "00/00/0000 00:00:00",
                            "00/00/0000 00:00:00",
                            this.telaCadastroReserva.getjTextFieldObs().getText(),
                            'A',
                            checkQuarto
                    );
                    service.CheckService.Criar(check);
                    check = service.CheckService.Carregar(-1);

                    //Criar hospede com o check e o hospede
                    Hospede hospede = service.HospedeService.Carregar("nome", (String) this.telaCadastroReserva.getjComboBoxHospede().getSelectedItem()).get(0);
                    
                    //Cria o check_hospede com base na reserva e retorna o que foi criado para poder criar o check
                    CheckHospede checkHospede = new CheckHospede(
                            "Hospede",
                            this.telaCadastroReserva.getjTextFieldObs().getText(),
                            'A',
                            check,
                            hospede
                    );
                    service.CheckHospedeService.Criar(checkHospede);

                    reserva.setDataHoraReserva(this.telaCadastroReserva.getjFormattedTextFieldDataCadastro().getText());
                    reserva.setDataPrevistaEntrada(this.telaCadastroReserva.getjFormattedTextFieldDataPrevistaEntrada().getText());
                    reserva.setDataPrevistaSaida(this.telaCadastroReserva.getjFormattedTextFieldDataPrevistaSaida().getText());
                    reserva.setObs(this.telaCadastroReserva.getjTextFieldObs().getText());
                    reserva.setStatus('A');
                    List<Check> checks = new ArrayList<>();
                    checks.add(check);
                    reserva.setCheck(checks);
                    service.ReservaService.Criar(reserva);
                //Atualização
                } else {
                    Reserva reserva = service.ReservaService.Carregar("id", String.valueOf(codigo)).get(0);

                    // Atualizar dados da reserva
                    reserva.setDataHoraReserva(this.telaCadastroReserva.getjFormattedTextFieldDataCadastro().getText());
                    reserva.setDataPrevistaEntrada(this.telaCadastroReserva.getjFormattedTextFieldDataPrevistaEntrada().getText());
                    reserva.setDataPrevistaSaida(this.telaCadastroReserva.getjFormattedTextFieldDataPrevistaSaida().getText());
                    reserva.setObs(this.telaCadastroReserva.getjTextFieldObs().getText());
                    reserva.setStatus('A');

                    //Recuperar o Quarto selecionado
                    Quarto quarto = service.QuartoService.Carregar("descricao", (String) this.telaCadastroReserva.getjComboBoxQuarto().getSelectedItem()).get(0);
                    
                    //Atualizar o Check Quarto
                    CheckQuarto checkQuarto = reserva.getCheck().get(0).getCheckQuarto();
                    checkQuarto.setQuarto(quarto);
                    checkQuarto.setObs(this.telaCadastroReserva.getjTextFieldObs().getText());
                    checkQuarto.setDataHoraInicio(this.telaCadastroReserva.getjFormattedTextFieldDataPrevistaEntrada().getText());
                    checkQuarto.setDataHoraFim(this.telaCadastroReserva.getjFormattedTextFieldDataPrevistaSaida().getText());

                    //Reiniciar o Check
                    Check check = new Check(
                            reserva.getCheck().get(0).getId(),
                            this.telaCadastroReserva.getjFormattedTextFieldDataCadastro().getText(),
                            "00/00/0000 00:00:00",
                            "00/00/0000 00:00:00",
                            this.telaCadastroReserva.getjTextFieldObs().getText(),
                            'A',
                            checkQuarto
                    );

                    //Recuperar o Hospede selecionado
                    Hospede hospede = service.HospedeService.Carregar("nome", (String) this.telaCadastroReserva.getjComboBoxHospede().getSelectedItem()).get(0);

                    //Atualizar o Check Hospede
                    CheckHospede checkHospede = service.CheckHospedeService.Carregar("check_id", reserva.getCheck().get(0).getId()+"").get(0);
                    checkHospede.setHospede(hospede);
                    
                    //Subir atualizações
                    service.CheckQuartoService.Atualizar(checkQuarto);
                    service.CheckService.Atualizar(check);
                    service.CheckHospedeService.Atualizar(checkHospede);
                    
                    //Atualizar a Reserva
                    service.ReservaService.Atualizar(reserva);
                }
                utilities.Utilities.ativaDesativa(this.telaCadastroReserva.getjPanelBotoes(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastroReserva.getjPanelDados(), false);
            }
        //Botão Cancelar
        } else if (evento.getSource() == this.telaCadastroReserva.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroReserva.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroReserva.getjPanelDados(), false);
        //Botão Sair
        } else if (evento.getSource() == this.telaCadastroReserva.getjButtonSair()) {
            this.telaCadastroReserva.dispose();
        }
    }
}
