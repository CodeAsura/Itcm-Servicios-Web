package mx.cdmadero.tecnm.web_project.domain;

import mx.cdmadero.tecnm.web_project.data.entities.IEntity;
import mx.cdmadero.tecnm.web_project.shared.dtos.interfaces.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;


public abstract class CommonOperations<
        T extends IEntity,
        R extends JpaRepository<T, Integer>,
        D extends ResponseDTO
> {

    @Autowired
    protected R repository;

    public List<D> getAll() {
        return repository.findAll()
                .stream()
                .filter(IEntity::isActive)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public D getById(Integer id) {
        T entity = repository.findById(id)
                .filter(IEntity::isActive)
                .orElseThrow(() -> new IllegalArgumentException("Entity not found with ID: " + id));
        return toDTO(entity);
    }

    public D create(D dto) {
        T entity = toEntity(dto);
        entity.setActive(true);
        return toDTO(repository.save(entity));
    }

    public D update(D dto) {
        validateExists(dto.getId());
        T entity = toEntity(dto);
        entity.setActive(true);
        return toDTO(repository.save(entity));
    }

    public void delete(Integer id) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Entity not found with ID: " + id));
        entity.setActive(false);
        repository.save(entity);
    }

    public List<D> getDeleted() {
        return repository.findAll()
                .stream()
                .filter(entity -> !entity.isActive())
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public D restore(Integer id) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Entity not found with ID: " + id));
        entity.setActive(true);
        return toDTO(repository.save(entity));
    }

    public void validateExists(Integer id) {
        if(!repository.existsById(id) || !isActive(id)) {
            throw new IllegalArgumentException("Entity not found with ID: " + id);
        }
    }

    private boolean isActive(Integer id) {
        T entity = repository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Entity not found with ID: " + id));
        return entity.isActive();
    }

    public abstract D toDTO(T entity);

    public abstract T toEntity(D dto);

}