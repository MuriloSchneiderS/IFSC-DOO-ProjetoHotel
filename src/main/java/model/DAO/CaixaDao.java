package model.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.DAO.InterfaceDAO;
import model.bo.Caixa;

public class CaixaDAO implements InterfaceDAO<Caixa> {

    private static CaixaDAO instance;
    protected EntityManager entityManager;

    public CaixaDAO() {
        entityManager = getEntityManager();
    }

    public static CaixaDAO getInstance() {
        if (instance == null) {
            instance = new CaixaDAO();
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
    public void Create(Caixa objeto) {
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
    public Caixa Retrieve(int id) {

        Caixa caixa = entityManager.find(Caixa.class, id);
        return caixa;
    }

    @Override
    public List<Caixa> Retrieve(String atributo, String valor) {

        List<Caixa> listaCaixa = new ArrayList<>();
        listaCaixa = entityManager.createQuery(" Select x From Caixa x "
                + " where x." + atributo
                + " LIKE '%" + valor + "%'", Caixa.class).getResultList();
        return listaCaixa;
    }

    @Override
    public void Update(Caixa objeto) {
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
    public void Delete(Caixa objeto) {

        try{
            entityManager.getTransaction().begin();
            Caixa caixa = new Caixa();
            caixa = entityManager.find(Caixa.class, objeto.getId());
            if(caixa != null){
                entityManager.remove(caixa);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}