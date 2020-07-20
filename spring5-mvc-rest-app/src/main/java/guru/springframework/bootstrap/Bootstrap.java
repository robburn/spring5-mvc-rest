package guru.springframework.bootstrap;

import guru.springframework.domain.Category;
import guru.springframework.domain.Customer;
import guru.springframework.domain.Vendor;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.CustomerRepository;
import guru.springframework.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Nutty Nut Co.");
        vendorRepository.save(vendor1);

        Vendor vendor2 = new Vendor();
        vendor2.setName("Fruity Fruit Co.");
        vendorRepository.save(vendor2);

        System.out.println("Vendor Data Loaded = " + vendorRepository.count());
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Category Data Loaded = " + categoryRepository.count());
    }

    private void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstname("Michael");
        customer1.setLastname("Lachappele");
//        customer1.setCustomerurl("/shop/customers/2");

        Customer customer2 = new Customer();
        customer2.setFirstname("David");
        customer2.setLastname("Winter");
//        customer2.setCustomer_url("/shop/customers/7");

        Customer customer3 = new Customer();
        customer3.setFirstname("Anne");
        customer3.setLastname("Hine");
//        customer3.setCustomer_url("/shop/customers/102");

        Customer customer4 = new Customer();
        customer4.setFirstname("Alice");
        customer4.setLastname("Eastman");
//        customer4.setCustomer_url("/shop/customers/342");

        Customer customer5 = new Customer();
        customer5.setFirstname("N3O");
        customer5.setLastname("idk");
//        customer5.setCustomer_url("/shop/customers/343");

        Customer customer6 = new Customer();
        customer6.setFirstname("Nilesh");
        customer6.setLastname("Choudhary");
//        customer6.setCustomer_url("/shop/customers/345");

        Customer customer7 = new Customer();
        customer7.setFirstname("N3O7");
        customer7.setLastname("lul");
//        customer7.setCustomer_url("/shop/customers/347");

        Customer customer8 = new Customer();
        customer8.setFirstname("Freddy");
        customer8.setLastname("Meyers");
//        customer8.setCustomer_url("/shop/customers/348");

        Customer customer9 = new Customer();
        customer9.setFirstname("Freddy");
        customer9.setLastname("Meyers");
//        customer9.setCustomer_url("/shop/customers/349");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);
        customerRepository.save(customer5);
        customerRepository.save(customer6);
        customerRepository.save(customer7);
        customerRepository.save(customer8);
        customerRepository.save(customer9);

        System.out.println("Customer Data Loaded = " + customerRepository.count());
    }
}
