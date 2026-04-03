package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Check;
import model.CheckQuarto;
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

        if (codigo == 0) {
            utilities.Utilities.ativaDesativa(this.telaCadastroReserva.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroReserva.getjPanelDados(), false);
        } else {//rotina de carga de reserva
            utilities.Utilities.ativaDesativa(this.telaCadastroReserva.getjPanelBotoes(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroReserva.getjPanelDados(), true);

            this.telaCadastroReserva.getjTextFieldId().setText(codigo + "");
            this.telaCadastroReserva.getjTextFieldId().setEnabled(false);

            Reserva reserva = new Reserva();
            reserva = service.ReservaService.Carregar(codigo);

            this.telaCadastroReserva.getjFormattedTextFieldDataPrevistaEntrada().setText(reserva.getDataPrevistaEntrada());
            this.telaCadastroReserva.getjFormattedTextFieldDataPrevistaSaida().setText(reserva.getDataPrevistaSaida());
            this.telaCadastroReserva.getjTextFieldObs().setText(reserva.getObs());
            Quarto quartoReservado = (Quarto) service.QuartoService.Carregar(reserva.getCheck().get(0).getId());
            this.telaCadastroReserva.getjComboBoxQuarto().setSelectedItem(quartoReservado.getDescricao());
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
                Reserva reserva = new Reserva();
                Quarto quarto = service.QuartoService.Carregar("descricao", (String) this.telaCadastroReserva.getjComboBoxQuarto().getSelectedItem()).get(0);
                //Cria o check_quarto com base na reserva e retorna o que foi criado para poder criar o check
                CheckQuarto checkQuarto = new CheckQuarto(
                        this.telaCadastroReserva.getjFormattedTextFieldDataPrevistaEntrada().getText(),
                        this.telaCadastroReserva.getjFormattedTextFieldDataPrevistaSaida().getText(),
                        this.telaCadastroReserva.getjTextFieldObs().getText(),
                        'A',
                        quarto
                );
                service.CheckQuartoService.Criar(checkQuarto);
                checkQuarto = service.CheckQuartoService.Carregar("data_hora_inicio", utilities.Utilities.formataDataHoraParaMySQL(this.telaCadastroReserva.getjFormattedTextFieldDataPrevistaEntrada().getText())).get(0);
                Check check = new Check(
                        this.telaCadastroReserva.getjFormattedTextFieldDataCadastro().getText(),
                        this.telaCadastroReserva.getjFormattedTextFieldDataPrevistaEntrada().getText(),
                        this.telaCadastroReserva.getjFormattedTextFieldDataPrevistaSaida().getText(),
                        this.telaCadastroReserva.getjTextFieldObs().getText(),
                        'A',
                        checkQuarto
                );
                service.CheckService.Criar(check);

                reserva.setDataHoraReserva(this.telaCadastroReserva.getjFormattedTextFieldDataCadastro().getText());
                reserva.setDataPrevistaEntrada(this.telaCadastroReserva.getjFormattedTextFieldDataPrevistaEntrada().getText());
                reserva.setDataPrevistaSaida(this.telaCadastroReserva.getjFormattedTextFieldDataPrevistaSaida().getText());
                reserva.setObs(this.telaCadastroReserva.getjTextFieldObs().getText());
                reserva.setCheck(service.CheckService.Carregar("data_hora_entrada", utilities.Utilities.formataDataHoraParaMySQL(this.telaCadastroReserva.getjFormattedTextFieldDataPrevistaEntrada().getText())));

                if (this.telaCadastroReserva.getjTextFieldId().getText().trim().equalsIgnoreCase("")) {
                    //Inclusão
                    reserva.setStatus('A');
                    service.ReservaService.Criar(reserva);
                } else {
                    //Atualização
                    reserva.setId(Integer.parseInt(this.telaCadastroReserva.getjTextFieldId().getText()));
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
