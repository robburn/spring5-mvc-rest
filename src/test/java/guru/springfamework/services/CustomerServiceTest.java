package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.controllers.v1.CustomerController;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.print.attribute.standard.MediaSize;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {
    public static final String NAME = "Joe";
    public static final String LAST_NAME = "Newman";
    public static final Long ID = 1L;
    public static final String CUSTOMER_URL_ROOT = CustomerController.BASE_URL + "/";

    @Mock
    CustomerRepository customerRepository;

    CustomerServiceImpl customerService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE );
    }

    @Test
    public void getAllCustomers() {
        //given
        Customer customer1 = new Customer();
        customer1.setId(ID);
        customer1.setFirstname(NAME);
        customer1.setLastname(LAST_NAME);

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstname("Bob");
        customer2.setLastname("Boberton");

        List<Customer> customers = Arrays.asList(customer1, customer2);

        when(customerRepository.findAll()).thenReturn(customers);

        //when
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        //then
        assertEquals(2L, customerDTOS.size());
    }

    @Test
    public void getCustomerByLastName() {
        //given
        Customer customer1 = new Customer();
        customer1.setId(ID);
        customer1.setFirstname(NAME);
        customer1.setLastname(LAST_NAME);
        when(customerRepository.findByLastname(anyString())).thenReturn(customer1);

        //when
        CustomerDTO customerDTO = customerService.getCustomerByLastName(LAST_NAME);

        //then
        assertEquals(LAST_NAME, customerDTO.getLastname());
        assertEquals(NAME, customerDTO.getFirstname());
        assertEquals(CUSTOMER_URL_ROOT + ID, customerDTO.getCustomerUrl());
    }

    @Test
    public void createNewCustomer() throws Exception {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jim");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());
        savedCustomer.setId(ID);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        //then
        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
        assertEquals(CUSTOMER_URL_ROOT + ID, savedDto.getCustomerUrl());
    }

    @Test
    public void saveCustomerByDTO() throws Exception {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jim");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());
        savedCustomer.setId(ID);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.saveCustomerByDTO(ID, customerDTO);

        //then
        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
        assertEquals(CUSTOMER_URL_ROOT + ID, savedDto.getCustomerUrl());
    }

    @Test
    public void deleteCustomerById() throws Exception {
        customerRepository.deleteById(ID);
        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}