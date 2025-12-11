package model.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.bo.MovimentoCaixa;

public class MovimentoCaixaDAO implements InterfaceDAO<MovimentoCaixa> {

    private static MovimentoCaixaDAO instance;
    protected EntityManager entityManager;

    public MovimentoCaixaDAO() {
        entityManager = getEntityManager();
    }

    public static MovimentoCaixaDAO getInstance() {
        if (instance == null) {
            instance = new MovimentoCaixaDAO();
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
    public void Create(MovimentoCaixa objeto) {
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
    public MovimentoCaixa Retrieve(int id) {

        MovimentoCaixa movimentoCaixa = entityManager.find(MovimentoCaixa.class, id);
        return movimentoCaixa;
    }

    @Override
    public List<MovimentoCaixa> Retrieve(String atributo, String valor) {

        List<MovimentoCaixa> listaMovimentoCaixa = new ArrayList<>();
        listaMovimentoCaixa = entityManager.createQuery(" Select hosp From movimentoCaixa hosp "
                + " where " + atributo
                + " like (%" + valor + " %)", MovimentoCaixa.class).getResultList();
        return listaMovimentoCaixa;
    }

    @Override
    public void Update(MovimentoCaixa objeto) {
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
    public void Delete(MovimentoCaixa objeto) {

        try{
            entityManager.getTransaction().begin();
            MovimentoCaixa movimentoCaixa = new MovimentoCaixa();
            movimentoCaixa = entityManager.find(MovimentoCaixa.class, objeto.getId());
            if(movimentoCaixa != null){
                entityManager.remove(movimentoCaixa);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}