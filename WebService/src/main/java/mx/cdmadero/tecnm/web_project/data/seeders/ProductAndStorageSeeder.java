package mx.cdmadero.tecnm.web_project.data.seeders;

import mx.cdmadero.tecnm.web_project.data.entities.Location;
import mx.cdmadero.tecnm.web_project.data.entities.Product;
import mx.cdmadero.tecnm.web_project.data.entities.Storage;
import mx.cdmadero.tecnm.web_project.data.entities.StorageProduct;
import mx.cdmadero.tecnm.web_project.data.repositories.LocationRepository;
import mx.cdmadero.tecnm.web_project.data.repositories.ProductRepository;
import mx.cdmadero.tecnm.web_project.data.repositories.StorageProductRepository;
import mx.cdmadero.tecnm.web_project.data.repositories.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Profile("test")
public class ProductAndStorageSeeder implements CommandLineRunner {
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StorageProductRepository storageProductRepository;
    @Autowired
    LocationRepository locationRepository;

    private final List<Product> products = Arrays.asList(
            Product.builder()
                    .UPC("000111222333")
                    .name("Test Product 1")
                    .description("Test Description 1")
                    .lastCost("10")
                    .lastPrice("20")
                    .isActive(true)
                    .build(),
            Product.builder()
                    .UPC("444555666777")
                    .name("Test Product 2")
                    .description("Test Description 2")
                    .lastCost("30")
                    .lastPrice("40")
                    .isActive(true)
                    .build()
    );
    private final List<Storage> storages = Arrays.asList(
            Storage.builder()
                    .name("Test Warehouse 1")
                    .address("Test Address 1")
                    .latitude("19.432608")
                    .longitude("-99.133209")
                    .isActive(true)
                    .build(),
            Storage.builder()
                    .name("Test Warehouse 2")
                    .address("Test Address 2")
                    .latitude("19.501420")
                    .longitude("-99.130576")
                    .isActive(true)
                    .build()
    );

    @Override
    public void run(String... args) throws Exception {
        seedStorages();
        seedProducts();
        seedStorageProduct();
        seedLocations();
    }

    private void seedStorages() {
        storageRepository.saveAll(storages);
        System.out.println("Test storages seeded.");
    }

    private void seedProducts() {
        productRepository.saveAll(products);
        System.out.println("Test products seeded.");
    }

    private void seedStorageProduct() {
        List<StorageProduct> storageProducts = Arrays.asList(
                StorageProduct.builder()
                        .storage(storages.get(0))
                        .product(products.get(0))
                        .stock(15)
                        .build(),
                StorageProduct.builder()
                        .storage(storages.get(0))
                        .product(products.get(1))
                        .stock(56)
                        .build(),
                StorageProduct.builder()
                        .storage(storages.get(1))
                        .product(products.get(1))
                        .stock(25)
                        .build()
        );
        storageProductRepository.saveAll(storageProducts);
    }

    private void seedLocations() {
        List<Location> locations = Arrays.asList(
                Location.builder()
                        .storage(storages.get(0))
                        .product(products.get(0))
                        .hallwayNumber(1)
                        .shelfNumber(2)
                        .level(1)
                        .isActive(true)
                        .build(),
                Location.builder()
                        .storage(storages.get(0))
                        .product(products.get(1))
                        .hallwayNumber(1)
                        .shelfNumber(3)
                        .level(2)
                        .isActive(true)
                        .build(),
                Location.builder()
                        .storage(storages.get(1))
                        .product(products.get(0))
                        .hallwayNumber(2)
                        .shelfNumber(1)
                        .level(1)
                        .isActive(true)
                        .build(),
                Location.builder()
                        .storage(storages.get(1))
                        .product(products.get(0))
                        .hallwayNumber(3)
                        .shelfNumber(2)
                        .level(3)
                        .isActive(true)
                        .build(),
                Location.builder()
                        .storage(storages.get(0))
                        .product(products.get(1))
                        .hallwayNumber(4)
                        .shelfNumber(4)
                        .level(2)
                        .isActive(true)
                        .build()
        );
        locationRepository.saveAll(locations);
    }
}
