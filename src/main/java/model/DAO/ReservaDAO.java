package model.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.bo.Reserva;

public class ReservaDAO implements InterfaceDAO<Reserva> {

    private static ReservaDAO instance;
    protected EntityManager entityManager;

    public ReservaDAO() {
        entityManager = getEntityManager();
    }

    public static ReservaDAO getInstance() {
        if (instance == null) {
            instance = new ReservaDAO();
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
    public void Create(Reserva objeto) {
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
    public Reserva Retrieve(int id) {

        Reserva reserva = entityManager.find(Reserva.class, id);
        return reserva;
    }

    @Override
    public List<Reserva> Retrieve(String atributo, String valor) {

        List<Reserva> listaReserva = new ArrayList<>();
        listaReserva = entityManager.createQuery(" Select x From Reserva x "
                + " where x." + atributo
                + " LIKE '%" + valor + "%'", Reserva.class).getResultList();
        return listaReserva;
    }

    @Override
    public void Update(Reserva objeto) {
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
    public void Delete(Reserva objeto) {

        try{
            entityManager.getTransaction().begin();
            Reserva reserva = new Reserva();
            reserva = entityManager.find(Reserva.class, objeto.getId());
            if(reserva != null){
                entityManager.remove(reserva);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}