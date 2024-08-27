package org.clevertec.checkcrud.repository;

import org.clevertec.checkcrud.db.ConnectionManager;
import org.clevertec.checkcrud.db.SQLRequests;
import org.clevertec.checkcrud.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductRepositoryImpl implements ProductRepository {

    private final ConnectionManager connectionManager;

    public ProductRepositoryImpl() {
        connectionManager = new ConnectionManager();
    }

    @Override
    public Product save(Product product) {
        try (PreparedStatement preparedStatement = connectionManager.getConnection()
                .prepareStatement(SQLRequests.SAVE_PRODUCT)) {
            preparedStatement.setString(1, product.getDescription());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setBoolean(4, product.isWholesale());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void deleteById(Integer id) {
        try (PreparedStatement preparedStatement = connectionManager.getConnection()
                .prepareStatement(SQLRequests.DELETE_PRODUCT_BY_ID)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product update(Product product) {
        try (PreparedStatement preparedStatement = connectionManager.getConnection()
                .prepareStatement(SQLRequests.UPDATE_PRODUCT)){
            preparedStatement.setString(1, product.getDescription());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setBoolean(4, product.isWholesale());
            preparedStatement.setInt(5, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public Product getById(Integer integer) {
        Product product = new Product();
        try (PreparedStatement preparedStatement = connectionManager.getConnection()
                .prepareStatement(SQLRequests.GET_PRODUCT_BY_ID)) {
            preparedStatement.setInt(1, integer);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product.setDescription(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(3));
                product.setQuantity(resultSet.getInt(4));
                product.setWholesale(resultSet.getBoolean(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}
