package pl.sdaacademy.conferenceroomreservationsystem.api;

import pl.sdaacademy.conferenceroomreservationsystem.dto.AbstractBaseDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.DistributedEntity;

import java.util.List;

public interface AbstractCRUDLApi<ENTITY extends DistributedEntity, DTO extends AbstractBaseDTO> {

    DTO save(DTO dto);

    DTO getById(Integer id);

    List<DTO> list();

    Boolean delete(Integer id);
}
