package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    public static final String CUSTOMER_URL_ROOT = "/api/v1/customer/";

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(expression = "java(createCustomerUrl(customer))", target = "customerUrl")
    CustomerDTO customerToCustomerDTO(Customer customer);

    default String createCustomerUrl(Customer customer) {
        return CUSTOMER_URL_ROOT + customer.getId().toString();
    }

    Customer customerDtoToCustomer(CustomerDTO customerDTO);

}
