package edu.sjsu.cmpe275.lab2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
public class FlightDAO {

		private EntityManagerFactory entityManagerFactory;
		public FlightDAO() {
			entityManagerFactory = Persistence.createEntityManagerFactory("lab2");
			//entityManagerFactory = new PersistenceProvider().createEntityManagerFactory;
		}
		public void store(Flight res) {
			EntityManager manager = entityManagerFactory.createEntityManager();
			EntityTransaction tx = manager.getTransaction();
			try {
				tx.begin();
				manager.merge(res);
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
				Flight res = manager.find(Flight.class, Id);
				manager.remove(res);
				tx.commit();
			} catch (RuntimeException e) {
				tx.rollback();
				throw e;
			} finally {
				manager.close();
			}
		}
		public Flight findById(String Id) {
			
			EntityManager manager = entityManagerFactory.createEntityManager();
			try {
				return manager.find(Flight.class, Id);
			} finally {
				manager.close();
			}
		}
}


	
	
	

