package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {
    public static final String NAME = "Joe";
    public static final String LAST_NAME = "Newman";
    public static final long ID = 1L;
    public static final String CUSTOMER_URL = "/api/v1/customer/1";
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testListCustomers() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstname(NAME);
        customer1.setLastname(LAST_NAME);
        customer1.setCustomerUrl(CUSTOMER_URL);

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstname("Bob");
        customer2.setLastname("Boberton");
        customer2.setCustomerUrl("/api/v1/customer/2");

        List<CustomerDTO> customers = Arrays.asList(customer1, customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void testGetByLastNameCustomers() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstname(NAME);
        customer1.setLastname(LAST_NAME);
        customer1.setCustomerUrl(CUSTOMER_URL);

        when(customerService.getCustomerByLastName(anyString())).thenReturn(customer1);

        mockMvc.perform(get("/api/v1/customers/lastname/" + LAST_NAME)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(NAME)))
                .andExpect(jsonPath("$.lastname", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$.customerUrl", equalTo(CUSTOMER_URL)));
    }
}