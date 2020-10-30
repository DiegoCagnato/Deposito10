package services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import daoservices.ProductDao;
import daoservices.PurchaseDao;
import entity.Product;
import entity.User;

public class CartService {
	
	private Integer idCart;
	private User user;
	private Set<Product> cart;
	private Map<String, Product> map;
	
	public CartService(User user, Integer idCart){
		
		this.idCart = idCart;
		this.user = user;
		cart = new HashSet<Product>();
		map = new HashMap<>();
	}
	
	public void printUserCart(){
		
		user.printUser();
	}
	
	public void insertProductCart(String id){
		
		PurchaseDao purchaseDao = new PurchaseDao();
		ProductDao productDao = new ProductDao(); 
		Product product = productDao.findById(id);
		cart.add(product);
		map.put(id, product);
		purchaseDao.addPurchase(idCart, product, user);
		
	}
	
	public void removeProductCart(String id){
			
		cart.remove(map.get(id));
	}
	
	public void purchaseCart(){
		
		PurchaseDao purchaseDao = new PurchaseDao();
		for(Product product : cart){
			
			purchaseDao.removeProductDB(product);
		}
	}
	
	public void printProductCart(){
		
		for(Product product : cart){
			
			product.printProduct();
		}
	}
	

}
