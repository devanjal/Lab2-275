package edu.sjsu.cmpe275.lab2;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class PassengerDAO {

	private EntityManagerFactory entityManagerFactory;
	public PassengerDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("lab2");
		//entityManagerFactory = new PersistenceProvider().createEntityManagerFactory;
	}
	public void store(Passenger passengr) {
		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		try {
			tx.begin();
			manager.merge(passengr);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			manager.close();
		}
	}
	public void delete(String Id) {
		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		try {
		tx.begin();
			Passenger passengr = manager.find(Passenger.class, Id);
			manager.remove(passengr);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			manager.close();
		}
	}

	public Passenger findById(String Id) {
		
		EntityManager manager = entityManagerFactory.createEntityManager();
		try {
			return manager.find(Passenger.class, Id);
		} catch (RuntimeException e) {
			throw e;
		}finally {
		manager.close();
		}
	}
	public  List<Reservation> findPassengerReservations(String Id) {
		
		EntityManager manager = entityManagerFactory.createEntityManager();
		Query query = manager.createQuery("Select reservation from Reservation reservation where reservation.passenger="+Id);
		List<Reservation> reservations =query.getResultList();
		return reservations;
	}
	

}
