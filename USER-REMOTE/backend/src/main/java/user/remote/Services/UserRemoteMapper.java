package user.remote.Services;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import user.remote.Models.UserRemote;

@Mapper(componentModel = "spring")
public interface UserRemoteMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCustomerFromDto(UserRemote dto, @MappingTarget UserRemote entity);
}