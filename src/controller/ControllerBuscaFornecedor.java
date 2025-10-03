package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Fornecedor;
import view.TelaBuscaFornecedor;

public class ControllerBuscaFornecedor implements ActionListener {

    TelaBuscaFornecedor telaBuscaFornecedor;

    public ControllerBuscaFornecedor(TelaBuscaFornecedor telaBuscaFornecedor) {

        this.telaBuscaFornecedor = telaBuscaFornecedor;

        this.telaBuscaFornecedor.getjButtonCarregar().addActionListener(this);
        this.telaBuscaFornecedor.getjButtonFiltar().addActionListener(this);
        this.telaBuscaFornecedor.getjButtonSair().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        //Botão Carregar
        if (evento.getSource() == this.telaBuscaFornecedor.getjButtonCarregar()) {
            if (this.telaBuscaFornecedor.getjTableDados().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Nenhum dado selecionado.");
            } else {
                //retorno dos dados para a tela de cadastro
                ControllerCadFornecedor.codigo = (int) this.telaBuscaFornecedor.getjTableDados().getValueAt(this.telaBuscaFornecedor.getjTableDados().getSelectedRow(), 0);
                this.telaBuscaFornecedor.dispose();
            }

        //Botão Filtrar
        } else if (evento.getSource() == this.telaBuscaFornecedor.getjButtonFiltar()) {
            if (this.telaBuscaFornecedor.getjTFFiltro().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaFornecedor.getjTableDados().getModel();
                tabela.setRowCount(0); // Reseta a tabela
                
                switch (this.telaBuscaFornecedor.getjCBFiltro().getSelectedIndex()) {
                    case 0://Id
                    {
                        //Criando objeto para receber o dado que virà do banco de dados
                        Fornecedor fornecedor = new Fornecedor();
                        //Carregando o registro do fornecedor na entidade para o objeto fornecedor
                        fornecedor = service.FornecedorService.Carregar(Integer.parseInt(this.telaBuscaFornecedor.getjTFFiltro().getText()));
                        //Criando um objeto tabela do tipo defaulttableModel e atribuindo o modelo da tela a ele
                        tabela = (DefaultTableModel) this.telaBuscaFornecedor.getjTableDados().getModel();
                        tabela.addRow(new Object[]{
                            fornecedor.getId(), 
                            fornecedor.getNome(), 
                            fornecedor.getCpf(), 
                            fornecedor.getStatus()
                        });
                        break;
                    }
                    case 1://Nome
                    {
                        //Criando a lista para receber os fornecedors
                        List<Fornecedor> listaFornecedors = new ArrayList<>();
                        //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o modelo da tabela a ele
                        listaFornecedors = service.FornecedorService.Carregar("nome", this.telaBuscaFornecedor.getjTFFiltro().getText());
                        tabela = (DefaultTableModel) this.telaBuscaFornecedor.getjTableDados().getModel();
                        //Adicionando os fornecedors na tabela
                        for (Fornecedor fornecedorAtualDaLista : listaFornecedors) {
                            tabela.addRow(new Object[]{
                                fornecedorAtualDaLista.getId(), 
                                fornecedorAtualDaLista.getNome(), 
                                fornecedorAtualDaLista.getCpf(), 
                                fornecedorAtualDaLista.getStatus()
                            });
                        }
                        break;
                    }
                    case 2://CPF
                        //Criando a lista para receber os fornecedors
                        List<Fornecedor> listaFornecedors = new ArrayList<>();
                        //Carregando os fornecedors via sl para dentro da lista
                        listaFornecedors = service.FornecedorService.Carregar("cpf", this.telaBuscaFornecedor.getjTFFiltro().getText());

                        //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o modelo de tabela a ele
                        tabela = (DefaultTableModel) this.telaBuscaFornecedor.getjTableDados().getModel();

                        for (Fornecedor fornecedorAtualDaLista : listaFornecedors) {
                            //Adicionado o fornecedor na tabela
                            tabela.addRow(new Object[]{fornecedorAtualDaLista.getId(),
                                fornecedorAtualDaLista.getNome(),
                                fornecedorAtualDaLista.getCpf(),
                                fornecedorAtualDaLista.getStatus()});
                        }
                        break;
                    default:
                        break;
                }
            }

            //Botão Sair
        } else if (evento.getSource() == this.telaBuscaFornecedor.getjButtonSair()) {
            this.telaBuscaFornecedor.dispose();
        }
    }
}
