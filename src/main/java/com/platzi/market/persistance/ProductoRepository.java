package com.platzi.market.persistance;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.persistance.crud.ProductoCrudRepository;
import com.platzi.market.persistance.entity.Producto;
import com.platzi.market.persistance.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired 
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){
        return mapper.toProducts( (List<Producto>) productoCrudRepository.findAll());
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> scarseProducts = productoCrudRepository.findByCantidadStockLessThanEstado(quantity,true);
        return scarseProducts.map(products -> mapper.toProducts(products));
    }

    @Override
    public Optional<Product> getProduct(int idProduct){
        return productoCrudRepository.findById(idProduct).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }
}
