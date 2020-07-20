package guru.springframework.api.v1.mapper;

import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.controllers.v1.VendorController;
import guru.springframework.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {
    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);
    public static final String CUSTOMER_URL_ROOT = VendorController.BASE_URL +"/";

    Vendor vendorDTOToVendor(VendorDTO vendorDTO);

    @Mapping(expression = "java(createVendorUrl(vendor))", target = "vendorUrl")
    VendorDTO vendorToVendorDTO(Vendor vendor);

    default String createVendorUrl(Vendor vendor) {
        return CUSTOMER_URL_ROOT + vendor.getId().toString();
    }
}
