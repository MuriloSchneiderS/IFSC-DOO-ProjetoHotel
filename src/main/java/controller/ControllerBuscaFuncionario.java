package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bo.Funcionario;
import view.TelaBuscaFuncionario;

public class ControllerBuscaFuncionario implements ActionListener {

    TelaBuscaFuncionario telaBuscaFuncionario;

    public ControllerBuscaFuncionario(TelaBuscaFuncionario telaBuscaFuncionario) {

        this.telaBuscaFuncionario = telaBuscaFuncionario;

        this.telaBuscaFuncionario.getjButtonCarregar().addActionListener(this);
        this.telaBuscaFuncionario.getjButtonFiltar().addActionListener(this);
        this.telaBuscaFuncionario.getjButtonSair().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        //Botão Carregar
        if (evento.getSource() == this.telaBuscaFuncionario.getjButtonCarregar()) {
            if (this.telaBuscaFuncionario.getjTableDados().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Nenhum dado selecionado.");
            } else {
                //retorno dos dados para a tela de cadastro
                ControllerCadFuncionario.codigo = (int) this.telaBuscaFuncionario.getjTableDados().getValueAt(this.telaBuscaFuncionario.getjTableDados().getSelectedRow(), 0);
                this.telaBuscaFuncionario.dispose();
            }

        //Botão Filtrar
        } else if (evento.getSource() == this.telaBuscaFuncionario.getjButtonFiltar()) {
            if (this.telaBuscaFuncionario.getjTFFiltro().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaFuncionario.getjTableDados().getModel();
                tabela.setRowCount(0); // Reseta a tabela
                
                switch (this.telaBuscaFuncionario.getjCBFiltro().getSelectedIndex()) {
                    case 0://Id
                    {
                        //Criando objeto para receber o dado que virà do banco de dados
                        Funcionario funcionario = new Funcionario();
                        //Carregando o registro do funcionario na entidade para o objeto funcionario
                        funcionario = service.FuncionarioService.Carregar(Integer.parseInt(this.telaBuscaFuncionario.getjTFFiltro().getText()));
                        //Criando um objeto tabela do tipo defaulttableModel e atribuindo o modelo da tela a ele
                        tabela = (DefaultTableModel) this.telaBuscaFuncionario.getjTableDados().getModel();
                        tabela.addRow(new Object[]{
                            funcionario.getId(), 
                            funcionario.getNome(), 
                            funcionario.getCpf(), 
                            funcionario.getStatus()
                        });
                        break;
                    }
                    case 1://Nome
                    {
                        //Criando a lista para receber os funcionarios
                        List<Funcionario> listaFuncionarios = new ArrayList<>();
                        //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o modelo da tabela a ele
                        listaFuncionarios = service.FuncionarioService.Carregar("nome", this.telaBuscaFuncionario.getjTFFiltro().getText());
                        tabela = (DefaultTableModel) this.telaBuscaFuncionario.getjTableDados().getModel();
                        //Adicionando os funcionarios na tabela
                        for (Funcionario funcionarioAtualDaLista : listaFuncionarios) {
                            tabela.addRow(new Object[]{
                                funcionarioAtualDaLista.getId(), 
                                funcionarioAtualDaLista.getNome(), 
                                funcionarioAtualDaLista.getCpf(), 
                                funcionarioAtualDaLista.getStatus()
                            });
                        }
                        break;
                    }
                    case 2://CPF
                        //Criando a lista para receber os funcionarios
                        List<Funcionario> listaFuncionarios = new ArrayList<>();
                        //Carregando os funcionarios via sl para dentro da lista
                        listaFuncionarios = service.FuncionarioService.Carregar("cpf", this.telaBuscaFuncionario.getjTFFiltro().getText());

                        //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o modelo de tabela a ele
                        tabela = (DefaultTableModel) this.telaBuscaFuncionario.getjTableDados().getModel();

                        for (Funcionario funcionarioAtualDaLista : listaFuncionarios) {
                            //Adicionado o funcionario na tabela
                            tabela.addRow(new Object[]{funcionarioAtualDaLista.getId(),
                                funcionarioAtualDaLista.getNome(),
                                funcionarioAtualDaLista.getCpf(),
                                funcionarioAtualDaLista.getStatus()});
                        }
                        break;
                    default:
                        break;
                }
            }

            //Botão Sair
        } else if (evento.getSource() == this.telaBuscaFuncionario.getjButtonSair()) {
            this.telaBuscaFuncionario.dispose();
        }
    }
}
