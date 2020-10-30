package eshop2;

import daoservices.CategoryDao;
import daoservices.ProductDao;
import daoservices.UserDao;
import entity.Category;
import entity.Product;
import entity.User;
import services.CartService;


public class EShop2 {

	
	public static void main(String[] args) {
		
		Category caffetteria = new Category("ca", "caffetteria");
		Category piante = new Category("pi", "piante");
		Category telefonia = new Category("te", "telefonia");
		
		CategoryDao categoryDao = new CategoryDao();
		
        categoryDao.insertObject(caffetteria);
        categoryDao.insertObject(piante);
        categoryDao.insertObject(telefonia);
        
        Product caffettiera = new Product("ca001", caffetteria, "caffettiera bialetti", 20, "moka");
        Product tazzine = new Product("ca002", caffetteria, "tazzine pregiate", 40, "tazzine");
        Product cactus = new Product("pi001", piante, "cactus pungente", 20, "cactus");
        Product baobab = new Product("pi002", piante, "baobab gigante", 50, "baobab");
        Product nokia3310 = new Product("te001", telefonia, "telefono antico", 15, "nokia 3310");
        Product IPhone = new Product("te002", telefonia, "telefono moderno", 100, "Iphone");
        
        ProductDao productDao = new ProductDao();
        
        productDao.insertObject(caffettiera);
        productDao.insertObject(tazzine);
        productDao.insertObject(cactus);
        productDao.insertObject(baobab);
        productDao.insertObject(nokia3310);
        productDao.insertObject(IPhone);
        
        
        User giovanni = new User("jhonny", "Giovanni");
        User paolo = new User("Pablo", "Paolo");
        User maria = new User("mary", "Maria");
        
        UserDao userDao = new UserDao();
        
        userDao.insertObject(giovanni);
        userDao.insertObject(paolo);
        userDao.insertObject(maria);
        
        userDao.findAndModify(paolo);
        
        userDao.printAll();
        
        CartService carrello = new CartService(paolo, 1);
        carrello.insertProductCart("ca001");
        carrello.insertProductCart("pi001");
        carrello.insertProductCart("pi002");
        carrello.printProductCart();
        carrello.removeProductCart("pi002");
        
        carrello.printProductCart();
        
        carrello.purchaseCart();
        
        
	}
}
