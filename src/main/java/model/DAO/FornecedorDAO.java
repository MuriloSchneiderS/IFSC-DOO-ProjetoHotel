package model.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.bo.Fornecedor;

public class FornecedorDAO implements InterfaceDAO<Fornecedor> {

    private static FornecedorDAO instance;
    protected EntityManager entityManager;

    public FornecedorDAO() {
        entityManager = getEntityManager();
    }

    public static FornecedorDAO getInstance() {
        if (instance == null) {
            instance = new FornecedorDAO();
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
    public void Create(Fornecedor objeto) {
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
    public Fornecedor Retrieve(int id) {

        Fornecedor fornecedor = entityManager.find(Fornecedor.class, id);
        return fornecedor;
    }

    @Override
    public List<Fornecedor> Retrieve(String atributo, String valor) {

        List<Fornecedor> listaFornecedor = new ArrayList<>();
        listaFornecedor = entityManager.createQuery(" Select hosp From fornecedor hosp "
                + " where " + atributo
                + " like (%" + valor + " %)", Fornecedor.class).getResultList();
        return listaFornecedor;
    }

    @Override
    public void Update(Fornecedor objeto) {
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
    public void Delete(Fornecedor objeto) {

        try{
            entityManager.getTransaction().begin();
            Fornecedor fornecedor = new Fornecedor();
            fornecedor = entityManager.find(Fornecedor.class, objeto.getId());
            if(fornecedor != null){
                entityManager.remove(fornecedor);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}