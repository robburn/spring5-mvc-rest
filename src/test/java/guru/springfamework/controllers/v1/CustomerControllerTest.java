package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.controllers.RestResponseEntityExceptionHandler;
import guru.springfamework.services.CustomerService;
import guru.springfamework.services.ResourceNotFoundException;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends AbstractRestControllerTest {
    public static final String NAME = "Joe";
    public static final String LAST_NAME = "Newman";
    public static final long ID = 1L;
    public static final String CUSTOMER_URL_ROOT = CustomerController.BASE_URL + "/";
    public static final String CUSTOMER_URL = CUSTOMER_URL_ROOT + "/1";
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
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
        customer2.setCustomerUrl(CUSTOMER_URL_ROOT + "2");

        List<CustomerDTO> customers = Arrays.asList(customer1, customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get(CUSTOMER_URL_ROOT)
                .accept(MediaType.APPLICATION_JSON)
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

        mockMvc.perform(get( CUSTOMER_URL_ROOT + "lastname/" + LAST_NAME)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(NAME)))
                .andExpect(jsonPath("$.lastname", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$.customer_url", equalTo(CUSTOMER_URL)));
    }

    @Test
    public void createNewCustomer() throws Exception {
        //given
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstname(NAME);
        customer1.setLastname(LAST_NAME);

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstname(customer1.getFirstname());
        returnDTO.setLastname(customer1.getLastname());
        returnDTO.setCustomerUrl(CUSTOMER_URL_ROOT + ID);

        when(customerService.createNewCustomer(customer1)).thenReturn(returnDTO);

        mockMvc.perform(post(CUSTOMER_URL_ROOT)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo(NAME)))
                .andExpect(jsonPath("$.customer_url", equalTo(CUSTOMER_URL_ROOT + ID)));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        //given
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstname(NAME);
        customer1.setLastname(LAST_NAME);

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstname(customer1.getFirstname());
        returnDTO.setLastname(customer1.getLastname());
        returnDTO.setCustomerUrl(CUSTOMER_URL_ROOT + ID);

        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(put(CUSTOMER_URL_ROOT + ID)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(NAME)))
                .andExpect(jsonPath("$.lastname", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$.customer_url", equalTo(CUSTOMER_URL_ROOT + ID)));
    }

    @Test
    public void testPatchCustomer() throws Exception {
        //given
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstname(NAME);

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstname(customer1.getFirstname());
        returnDTO.setLastname(LAST_NAME);
        returnDTO.setCustomerUrl(CUSTOMER_URL_ROOT + ID);

        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(patch(CUSTOMER_URL_ROOT + ID)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(NAME)))
                .andExpect(jsonPath("$.lastname", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$.customer_url", equalTo(CUSTOMER_URL_ROOT + ID)));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete(CUSTOMER_URL_ROOT + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(customerService).deleteCustomerById(anyLong());
    }

    @Test
    public void testNotFoundException() throws Exception {
        when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CUSTOMER_URL_ROOT + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}