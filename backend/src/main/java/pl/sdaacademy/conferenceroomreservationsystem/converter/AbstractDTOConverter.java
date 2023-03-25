package pl.sdaacademy.conferenceroomreservationsystem.converter;

import org.springframework.util.CollectionUtils;
import pl.sdaacademy.conferenceroomreservationsystem.dto.AbstractBaseDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.DistributedEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

abstract public class AbstractDTOConverter<ENTITY extends DistributedEntity, DTO extends AbstractBaseDTO> {

    public abstract DTO convert(final ENTITY entity);

    public void convert(final ENTITY entity, final DTO dto) {
        dto.setId(entity.getId());
        dto.setCreated(entity.getCreated());
        dto.setModified(entity.getModified());
    }

    public List<DTO> convertList(final List<ENTITY> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }

        return entities.stream().map(this::convert).collect(Collectors.toList());
    }
}
