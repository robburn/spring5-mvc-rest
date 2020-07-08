package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerMapperTest {

    public static final String NAME = "Joe";
    public static final String LAST_NAME = "Newman";
    public static final long ID = 1L;
    public static final String CUSTOMER_URL_ROOT = "/api/v1/customer/";
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() {
        //given
        Customer customer = new Customer();
        customer.setFirstname(NAME);
        customer.setLastname(LAST_NAME);
        customer.setId(ID);

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then
        assertEquals(NAME, customerDTO.getFirstname());
        assertEquals(LAST_NAME, customerDTO.getLastname());
        assertEquals(CUSTOMER_URL_ROOT + ID, customerDTO.getCustomerUrl());
    }
}