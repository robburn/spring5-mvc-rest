package guru.springfamework.services;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;

import java.util.List;

public interface VendorService {

    VendorDTO createNewVendor(VendorDTO vendorDTO);
    void deleteVendorById(Long id);
    VendorListDTO getAllVendors();
    VendorDTO getVendorById(Long id);
    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);
    VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

}
