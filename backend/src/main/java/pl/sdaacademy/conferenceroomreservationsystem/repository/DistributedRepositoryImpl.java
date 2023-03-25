package pl.sdaacademy.conferenceroomreservationsystem.repository;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class DistributedRepositoryImpl<ENTITY> extends SimpleJpaRepository<ENTITY, Integer>
        implements DistributedRepository<ENTITY>, JpaSpecificationExecutor<ENTITY> {
    public DistributedRepositoryImpl(final JpaEntityInformation<ENTITY, Integer> entityInformation, final EntityManager em) {
        super(entityInformation, em);
    }
}
