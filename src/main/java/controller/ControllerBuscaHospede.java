package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bo.Hospede;
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
            if (this.telaBuscaHospede.getjTableDados().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Nenhum dado selecionado.");
            } else {
                //retorno dos dados para a tela de cadastro
                ControllerCadHospede.codigo = (int) this.telaBuscaHospede.getjTableDados().getValueAt(this.telaBuscaHospede.getjTableDados().getSelectedRow(), 0);
                this.telaBuscaHospede.dispose();
            }

        //Botão Filtrar
        } else if (evento.getSource() == this.telaBuscaHospede.getjButtonFiltar()) {
            if (this.telaBuscaHospede.getjTFFiltro().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaHospede.getjTableDados().getModel();
                tabela.setRowCount(0); // Reseta a tabela
                
                switch (this.telaBuscaHospede.getjCBFiltro().getSelectedIndex()) {
                    case 0://Id
                    {
                        //Criando objeto para receber o dado que virà do banco de dados
                        Hospede hospede = new Hospede();
                        //Carregando o registro do hospede na entidade para o objeto hospede
                        hospede = service.HospedeService.Carregar(Integer.parseInt(this.telaBuscaHospede.getjTFFiltro().getText()));
                        //Criando um objeto tabela do tipo defaulttableModel e atribuindo o modelo da tela a ele
                        tabela = (DefaultTableModel) this.telaBuscaHospede.getjTableDados().getModel();
                        tabela.addRow(new Object[]{
                            hospede.getId(), 
                            hospede.getNome(), 
                            hospede.getCpf(), 
                            hospede.getStatus()
                        });
                        break;
                    }
                    case 1://Nome
                    {
                        //Criando a lista para receber os hospedes
                        List<Hospede> listaHospedes = new ArrayList<>();
                        //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o modelo da tabela a ele
                        listaHospedes = service.HospedeService.Carregar("nome", this.telaBuscaHospede.getjTFFiltro().getText());
                        tabela = (DefaultTableModel) this.telaBuscaHospede.getjTableDados().getModel();
                        //Adicionando os hospedes na tabela
                        for (Hospede hospedeAtualDaLista : listaHospedes) {
                            tabela.addRow(new Object[]{
                                hospedeAtualDaLista.getId(), 
                                hospedeAtualDaLista.getNome(), 
                                hospedeAtualDaLista.getCpf(), 
                                hospedeAtualDaLista.getStatus()
                            });
                        }
                        break;
                    }
                    case 2://CPF
                        //Criando a lista para receber os hospedes
                        List<Hospede> listaHospedes = new ArrayList<>();
                        //Carregando os hospedes via sl para dentro da lista
                        listaHospedes = service.HospedeService.Carregar("cpf", this.telaBuscaHospede.getjTFFiltro().getText());

                        //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o modelo de tabela a ele
                        tabela = (DefaultTableModel) this.telaBuscaHospede.getjTableDados().getModel();

                        for (Hospede hospedeAtualDaLista : listaHospedes) {
                            //Adicionado o hospede na tabela
                            tabela.addRow(new Object[]{hospedeAtualDaLista.getId(),
                                hospedeAtualDaLista.getNome(),
                                hospedeAtualDaLista.getCpf(),
                                hospedeAtualDaLista.getStatus()});
                        }
                        break;
                    default:
                        break;
                }
            }

            //Botão Sair
        } else if (evento.getSource() == this.telaBuscaHospede.getjButtonSair()) {
            this.telaBuscaHospede.dispose();
        }
    }
}
