package edu.sjsu.cmpe275.lab2;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;
public class FlightDAO {

		private EntityManagerFactory entityManagerFactory;
		public FlightDAO() {
			entityManagerFactory = Persistence.createEntityManagerFactory("lab2");
			//entityManagerFactory = new PersistenceProvider().createEntityManagerFactory;
		}
		@Transactional
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
		@Transactional
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
		@Transactional
		public Flight findById(String Id) {
			
			EntityManager manager = entityManagerFactory.createEntityManager();
			try {
				return manager.find(Flight.class, Id);
			} finally {
				manager.close();
			}
		}
		
		public  List<Passenger> findFlightPassengers(String Id) {
			
			EntityManager manager = entityManagerFactory.createEntityManager();
			Query query = manager.createQuery("Select reservation from Reservation reservation inner join reservation.flights flight where flight.id="+Id);
			List<Reservation> reservations =query.getResultList();
			List<Passenger> flight_passenger= new ArrayList<>();
			if (reservations!= null)
			for (Reservation r: reservations){
				flight_passenger.add(r.getPassenger());
			}
			return flight_passenger;
		}
		
}
