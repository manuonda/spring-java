package com.project.two.email.business.mapper;


import com.project.two.email.entity.domain.EmailLogger;
import com.project.two.email.entity.dto.EmailLoggerDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmailLoggerMapper {

    EmailLogger toEntity(EmailLoggerDTO dto);

    EmailLoggerDTO toDTO(EmailLogger entity);

    List<EmailLoggerDTO> toListDTO(List<EmailLogger> list);

    default EmailLogger fromId(String id) {
        if(id == null) {
            return null;
        }
        return new EmailLogger(id,"","","");
    }
}
