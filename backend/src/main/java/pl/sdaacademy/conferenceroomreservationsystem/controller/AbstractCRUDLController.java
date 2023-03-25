package pl.sdaacademy.conferenceroomreservationsystem.controller;

import org.springframework.web.bind.annotation.*;
import pl.sdaacademy.conferenceroomreservationsystem.api.AbstractCRUDLApi;
import pl.sdaacademy.conferenceroomreservationsystem.dto.AbstractBaseDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.DistributedEntity;

import java.util.List;

abstract public class AbstractCRUDLController<ENTITY extends DistributedEntity, DTO extends AbstractBaseDTO> {
    private final AbstractCRUDLApi<ENTITY, DTO> api;

    public AbstractCRUDLController(AbstractCRUDLApi<ENTITY, DTO> api) {
        this.api = api;
    }

    @PostMapping
    public DTO save(@RequestBody DTO dto) {
        return api.save(dto);
    }

    @GetMapping("/{id}")
    public DTO getById(@PathVariable Integer id) {
        return api.getById(id);
    }

    @GetMapping("/list")
    public List<DTO> list() {
        return api.list();
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return api.delete(id);
    }
}
