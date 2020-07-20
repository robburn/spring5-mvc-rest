package guru.springframework.services;

import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.api.v1.model.VendorListDTO;

public interface VendorService {

    VendorDTO createNewVendor(VendorDTO vendorDTO);
    void deleteVendorById(Long id);
    VendorListDTO getAllVendors();
    VendorDTO getVendorById(Long id);
    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);
    VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

}
