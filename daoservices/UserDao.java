package daoservices;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


import entity.User;

public class UserDao implements DaoGenerics<User> {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction et;
	private List<String> userIds; 
	
	
	public UserDao(){
		
		userIds = new ArrayList<>();
	}

	public void insertObject(User user){
			
		    emf = Persistence.createEntityManagerFactory("EShop2");
			em = emf.createEntityManager();
			et = em.getTransaction();
			et.begin();
			em.persist(user);
			userIds.add(user.getUsername());
			et.commit();
			em.close();
			emf.close();
		}
	    
	    
		
    public void findAndPrint(User user){
			
			emf = Persistence.createEntityManagerFactory("EShop2");
			em = emf.createEntityManager();
			et = em.getTransaction();
			et.begin();
			User u = em.find(User.class, user.getUsername());
			u.printUser();
			et.commit();
			em.close();
			emf.close();
		}
		
	public void findAndModify(User user){
			
			Scanner reader = new Scanner(System.in);
			System.out.println("Vuoi modificare il nome attuale del utente? (si/no)");
			String s = reader.nextLine();
			if(s.equals("si")){
				
				emf = Persistence.createEntityManagerFactory("EShop2");
				em = emf.createEntityManager();
				et = em.getTransaction();
				et.begin();
				User u = em.find(User.class, user.getUsername());
				em.remove(u);
				System.out.println("inserisci il nome dell'utente: ");
				s = reader.nextLine();
				u.setName(s);
				em.persist(u);
				et.commit();
				em.close();
				emf.close();
			}

		}
		
	public void printAll(){
			
			if(userIds.size()!=0){
				
			    emf = Persistence.createEntityManagerFactory("EShop2");
			    em = emf.createEntityManager();
			    et = em.getTransaction();
			    for(String id : userIds){
			        
			    	et.begin();
			    	User u = em.find(User.class, id);
			    	u.printUser();
			    	et.commit();
			         }
			    em.close();
			    emf.close();
			    }
			else{System.out.println("Non ci sono utenti");}
			
		}
}
