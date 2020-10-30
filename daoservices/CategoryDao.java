package daoservices;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entity.Category;

public class CategoryDao implements DaoGenerics<Category> {
	
	
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction et;
	private List<String> categoryIds; 
	
	
	public CategoryDao(){
		
		categoryIds = new ArrayList<>();
		emf = null;
		em = null;
		et = null;
	}
	
    public void insertObject(Category category){
		
	    emf = Persistence.createEntityManagerFactory("EShop2");
		em = emf.createEntityManager();
		et = em.getTransaction();
		et.begin();
		em.persist(category);
		categoryIds.add(category.getIdCategory());
		et.commit();
		em.close();
		emf.close();
	}
    
    
	
	public void findAndPrint(Category category){
		
		emf = Persistence.createEntityManagerFactory("EShop2");
		em = emf.createEntityManager();
		et = em.getTransaction();
		et.begin();
		Category c = em.find(Category.class, category.getIdCategory());
		c.printCategory();
		et.commit();
		em.close();
		emf.close();
	}
	
	public void findAndModify(Category category){
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Vuoi modificare il nome attuale della categoria? (si/no)");
		String s = reader.nextLine();
		if(s.equals("si")){
			
			emf = Persistence.createEntityManagerFactory("EShop2");
			em = emf.createEntityManager();
			et = em.getTransaction();
			et.begin();
			Category c = em.find(Category.class, category.getIdCategory());
			System.out.println("inserisci il nome della nuova categoria: ");
			s = reader.nextLine();
			c.setCategoryName(s);
			em.persist(c);
			et.commit();
			em.close();
			emf.close();
		}

	}
	
	public void printAll(){
		
		if(categoryIds.size()!=0){
			
		    emf = Persistence.createEntityManagerFactory("EShop");
		    em = emf.createEntityManager();
		    et = em.getTransaction();
		    for(String id : categoryIds){
		        
		    	et.begin();
		    	Category category = em.find(Category.class, id);
		    	category.printCategory();
		    	et.commit();
		         }
		    em.close();
		    emf.close();
		    }
		else{System.out.println("Non ci sono categorie");}
		
	}

}
