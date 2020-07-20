package guru.springframework.api.v1.mapper;

import guru.springframework.controllers.v1.CustomerController;
import guru.springframework.domain.Customer;
import guru.springframework.model.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    public static final String CUSTOMER_URL_ROOT = CustomerController.BASE_URL +"/";

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(expression = "java(createCustomerUrl(customer))", target = "customerUrl")
    CustomerDTO customerToCustomerDTO(Customer customer);

    default String createCustomerUrl(Customer customer) {
        return CUSTOMER_URL_ROOT + customer.getId().toString();
    }

    Customer customerDtoToCustomer(CustomerDTO customerDTO);

}
