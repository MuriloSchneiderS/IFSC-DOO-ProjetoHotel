package model.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.bo.Funcionario;

public class FuncionarioDAO implements InterfaceDAO<Funcionario> {

    private static FuncionarioDAO instance;
    protected EntityManager entityManager;

    public FuncionarioDAO() {
        entityManager = getEntityManager();
    }

    public static FuncionarioDAO getInstance() {
        if (instance == null) {
            instance = new FuncionarioDAO();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }

    @Override
    public void Create(Funcionario objeto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }

    }

    @Override
    public Funcionario Retrieve(int id) {

        Funcionario funcionario = entityManager.find(Funcionario.class, id);
        return funcionario;
    }

    @Override
    public List<Funcionario> Retrieve(String atributo, String valor) {

        List<Funcionario> listaFuncionario = new ArrayList<>();
        listaFuncionario = entityManager.createQuery(" Select hosp From funcionario hosp "
                + " where " + atributo
                + " like (%" + valor + " %)", Funcionario.class).getResultList();
        return listaFuncionario;
    }

    @Override
    public void Update(Funcionario objeto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void Delete(Funcionario objeto) {

        try{
            entityManager.getTransaction().begin();
            Funcionario funcionario = new Funcionario();
            funcionario = entityManager.find(Funcionario.class, objeto.getId());
            if(funcionario != null){
                entityManager.remove(funcionario);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}