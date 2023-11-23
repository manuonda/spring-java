package com.project.two.email.business.mapper;


import com.project.two.email.entity.domain.EmailLogo;
import com.project.two.email.entity.dto.EmailLogDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmailLogMapper {

    EmailLogo toEntity(EmailLogDTO dto);

    EmailLogDTO toDTO(EmailLogo entity);

    List<EmailLogDTO> toListDTO(List<EmailLogo> list);

    default EmailLogo fromId(String id) {
        if(id == null) {
            return null;
        }
        return new EmailLogo(id,"","");
    }
}
