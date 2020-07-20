package guru.springframework.api.v1.mapper;

import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    // @Mapping(source = "getId", target = "id")
    //@Mapping(source = "getid", target = "id")
    CategoryDTO categoryToCategoryDTO(Category category);
}
