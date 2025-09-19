package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Funcionario;
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
            if(this.telaBuscaFuncionario.getjTableDados().getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Nenhum dado selecionado.");
            }else{
                //retorno dos dados para a tela de cadastro
                ControllerCadFuncionario.codigo = (int) this.telaBuscaFuncionario.getjTableDados().getValueAt(this.telaBuscaFuncionario.getjTableDados().getSelectedRow(), 0);
                this.telaBuscaFuncionario.dispose();
            }
        //Botão Filtrar
        } else if (evento.getSource() == this.telaBuscaFuncionario.getjButtonFiltar()) {
            JOptionPane.showMessageDialog(null, "Botão Filtrar Pressionado...");
            if (this.telaBuscaFuncionario.getjTFFiltro().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                JOptionPane.showMessageDialog(null, "Filtrando informações...");
                if (this.telaBuscaFuncionario.getjCBFiltro().getSelectedIndex() == 0) {
                    //Criando objeto para receber o dado que virà do banco de dados
                    Funcionario funcionario = new Funcionario();
                    //Carregando o registro do funcionario na entidade para o objeto funcionario
                    funcionario = service.FuncionarioService.Carregar(Integer.parseInt(this.telaBuscaFuncionario.getjTFFiltro().getText()));
                    //Criando um objeto tabela do tipo defaulttableModel e atribuindo o modelo da tela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaFuncionario.getjTableDados().getModel();
                    tabela.addRow(new Object[]{funcionario.getId(), funcionario.getNome(), funcionario.getCpf(), funcionario.getStatus()});
                } else if (this.telaBuscaFuncionario.getjCBFiltro().getSelectedIndex() == 1) {
                    //Criando a lista para receber os funcionarios
                    List<Funcionario> listaFuncionarios = new ArrayList<>();
                    //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o modelo da tabela a ele
                    listaFuncionarios = service.FuncionarioService.Carregar("nome", this.telaBuscaFuncionario.getjTFFiltro().getText());
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaFuncionario.getjTableDados().getModel();
                    tabela.setRowCount(0);
                    //Adicionando os funcionarios na tabela
                    for(Funcionario funcionarioAtualDaLista : listaFuncionarios){
                        tabela.addRow(new Object[]{funcionarioAtualDaLista.getId(), funcionarioAtualDaLista.getNome(), funcionarioAtualDaLista.getCpf(), funcionarioAtualDaLista.getStatus()});
                    }
                } else if (this.telaBuscaFuncionario.getjCBFiltro().getSelectedIndex() == 2) {
                    JOptionPane.showMessageDialog(null, "Filtrando por CPF");
                }
            }
        //Botão Sair
        } else if (evento.getSource() == this.telaBuscaFuncionario.getjButtonSair()) {
            this.telaBuscaFuncionario.dispose();
        }
    }
}
