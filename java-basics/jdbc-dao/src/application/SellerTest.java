package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class SellerTest {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: seller findById ===");
        Seller seller = sellerDao.findById(1);
        System.out.println(seller);

        System.out.println("\n\n=== TEST 2: seller findByDepartment ===");
        List<Seller> list = sellerDao.findByDepartment(
                new Department(2, null)
        );
        for(Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println("\n\n=== TEST 3: seller findAll ===");
        List<Seller> list2 = sellerDao.findAll();
        for(Seller obj : list2) {
            System.out.println(obj);
        }

        System.out.println("\n\n=== TEST 4: seller insert ===");
        Seller newSeller = new Seller(
                9,
                "Annie Lux",
                "annie@email.com",
                new Date(),
                3000.0,
                new Department(2, null)
        );
        sellerDao.insert(newSeller);
        System.out.println("Inserted new seller. Id = " + newSeller.getId());

        System.out.println("\n\n=== TEST 5: seller update ===");
        newSeller.setName("Annie Lisa");
        sellerDao.update(newSeller);
        System.out.println("Updated seller at Id = " + newSeller.getId());

        System.out.println("\n\n=== TEST 6: seller delete ===");
        sellerDao.deleteById(newSeller.getId());
        System.out.println("Deleted seller at Id = " + newSeller.getId());
    }
}
