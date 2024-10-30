package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class DepartmentTest {
    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("=== TEST 1: department findById ===");
        Department department = departmentDao.findById(1);
        System.out.println(department);

        System.out.println("\n\n=== TEST 2: department findAll ===");
        List<Department> list2 = departmentDao.findAll();
        for(Department obj : list2) {
            System.out.println(obj);
        }

//        System.out.println("\n\n=== TEST 4: department insert ===");
//        Seller newSeller = new Seller(
//                9,
//                "Annie Lux",
//                "annie@email.com",
//                new Date(),
//                3000.0,
//                new Department(2, null)
//        );
//        departmentDao.insert(newSeller);
//        System.out.println("Inserted new department. Id = " + newSeller.getId());
//
//        System.out.println("\n\n=== TEST 5: department update ===");
//        newSeller.setName("Annie Lisa");
//        departmentDao.update(newSeller);
//        System.out.println("Updated department at Id = " + newSeller.getId());
//
//        System.out.println("\n\n=== TEST 6: department delete ===");
//        departmentDao.deleteById(newSeller.getId());
//        System.out.println("Deleted department at Id = " + newSeller.getId());
    }
}
