package mx.cdmadero.tecnm.web_project.data.specifications;

import mx.cdmadero.tecnm.web_project.data.entities.StorageProduct;
import org.springframework.data.jpa.domain.Specification;

public class StorageProductSpecifications {
    public static Specification<StorageProduct> storageIdEqualsTo(Integer storageId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("storage").get("id"), storageId);
    }

    public static Specification<StorageProduct> productIdEqualsTo(Integer storageId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("product").get("id"), storageId);
    }
}
