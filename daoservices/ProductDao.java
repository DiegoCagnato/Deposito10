package daoservices;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entity.Category;
import entity.Product;


public class ProductDao implements DaoGenerics<Product> {

	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction et;
	private List<String> productIds; 
	
	
	public ProductDao(){
		
		productIds = new ArrayList<>();
	}
	
	 public void insertObject(Product product){
			
		    emf = Persistence.createEntityManagerFactory("EShop2");
			em = emf.createEntityManager();
			et = em.getTransaction();
			et.begin();
			Category c = em.find(Category.class, product.getCategory().getIdCategory());
			c.setNProduct(c.getNProduct() + 1);
			em.persist(product);
			productIds.add(product.getIdProduct());
			et.commit();
			em.close();
			emf.close();
		}
	    
	    
		public Product findById(String id){
			
			emf = Persistence.createEntityManagerFactory("EShop2");
			em = emf.createEntityManager();
			et = em.getTransaction();
			et.begin();
			Product product = em.find(Product.class, id);
			et.commit();
			em.close();
			emf.close();
			return product;
		}
		
		public void findAndPrint(Product product){
			
			emf = Persistence.createEntityManagerFactory("EShop2");
			em = emf.createEntityManager();
			et = em.getTransaction();
			et.begin();
			Product p = em.find(Product.class, product.getIdProduct());
			p.printProduct();
			et.commit();
			em.close();
			emf.close();
		}
		
		public void findAndModify(Product product){
			
			Scanner reader = new Scanner(System.in);
			System.out.println("Vuoi modificare il nome attuale del prodotto? (si/no)");
			String s = reader.nextLine();
			Integer i;
			if(s.equals("si")){
				
				emf = Persistence.createEntityManagerFactory("EShop2");
				em = emf.createEntityManager();
				et = em.getTransaction();
				et.begin();
				Product p = em.find(Product.class, product.getIdProduct());
				System.out.println("inserisci il nome del nuovo prodotto: ");
				s = reader.nextLine();
				p.setProductName(s);
				System.out.println("inserisci il prezzo del nuovo prodotto");
				i = reader.nextInt();
				p.setPrice(i);
				em.persist(p);
				et.commit();
				em.close();
				emf.close();
			}

		}
		
		public void printAll(){
			
			if(productIds.size()!=0){
				
			    emf = Persistence.createEntityManagerFactory("EShop");
			    em = emf.createEntityManager();
			    et = em.getTransaction();
			    for(String id : productIds){
			        
			    	et.begin();
			    	Product p = em.find(Product.class, id);
			    	p.printProduct();
			    	et.commit();
			         }
			    em.close();
			    emf.close();
			    }
			else{System.out.println("Non ci sono prodotti");}
			
		}
}
