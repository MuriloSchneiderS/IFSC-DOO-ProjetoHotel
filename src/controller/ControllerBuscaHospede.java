package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Hospede;
import view.TelaBuscaHospede;

public class ControllerBuscaHospede implements ActionListener {

    TelaBuscaHospede telaBuscaHospede;

    public ControllerBuscaHospede(TelaBuscaHospede telaBuscaHospede) {

        this.telaBuscaHospede = telaBuscaHospede;

        this.telaBuscaHospede.getjButtonCarregar().addActionListener(this);
        this.telaBuscaHospede.getjButtonFiltar().addActionListener(this);
        this.telaBuscaHospede.getjButtonSair().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        //Botão Carregar
        if (evento.getSource() == this.telaBuscaHospede.getjButtonCarregar()) {
            if(this.telaBuscaHospede.getjTableDados().getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Nenhum dado selecionado.");
            }else{
                //retorno dos dados para a tela de cadastro
                ControllerCadHospede.codigo = (int) this.telaBuscaHospede.getjTableDados().getValueAt(this.telaBuscaHospede.getjTableDados().getSelectedRow(), 0);
                this.telaBuscaHospede.dispose();
            }
        //Botão Filtrar
        } else if (evento.getSource() == this.telaBuscaHospede.getjButtonFiltar()) {
            if (this.telaBuscaHospede.getjTFFiltro().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                if (this.telaBuscaHospede.getjCBFiltro().getSelectedIndex() == 0) {
                    //Criando objeto para receber o dado que virà do banco de dados
                    Hospede hospede = new Hospede();
                    //Carregando o registro do hospede na entidade para o objeto hospede
                    hospede = service.HospedeService.Carregar(Integer.parseInt(this.telaBuscaHospede.getjTFFiltro().getText()));
                    //Criando um objeto tabela do tipo defaulttableModel e atribuindo o modelo da tela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaHospede.getjTableDados().getModel();
                    tabela.addRow(new Object[]{hospede.getId(), hospede.getNome(), hospede.getCpf(), hospede.getStatus()});
                } else if (this.telaBuscaHospede.getjCBFiltro().getSelectedIndex() == 1) {
                    //Criando a lista para receber os hospedes
                    List<Hospede> listaHospedes = new ArrayList<>();
                    //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o modelo da tabela a ele
                    listaHospedes = service.HospedeService.Carregar("nome", this.telaBuscaHospede.getjTFFiltro().getText());
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaHospede.getjTableDados().getModel();
                    tabela.setRowCount(0);
                    //Adicionando os hospedes na tabela
                    for(Hospede hospedeAtualDaLista : listaHospedes){
                        tabela.addRow(new Object[]{hospedeAtualDaLista.getId(), hospedeAtualDaLista.getNome(), hospedeAtualDaLista.getCpf(), hospedeAtualDaLista.getStatus()});
                    }
                } else if (this.telaBuscaHospede.getjCBFiltro().getSelectedIndex() == 2) {
                    JOptionPane.showMessageDialog(null, "Filtrando por CPF");
                }
            }
        //Botão Sair
        } else if (evento.getSource() == this.telaBuscaHospede.getjButtonSair()) {
            this.telaBuscaHospede.dispose();
        }
    }
}
