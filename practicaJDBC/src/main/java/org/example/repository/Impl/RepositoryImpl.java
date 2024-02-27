package org.example.repository.Impl;


import org.example.Connetion.conexionBD;
import org.example.repository.Repository;
import org.example.model.Product;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class RepositoryImpl implements Repository {
    private Connection getConnection() throws SQLException {
        return conexionBD.getInstance();
    }
    private Product createProduct(ResultSet resultSet) throws
            SQLException {
        Product producto = new Product();
        producto.setId(resultSet.getLong("id"));
        producto.setName(resultSet.getString("name"));
        producto.setPrice(resultSet.getDouble("price"));
        producto.setRegister_date(
                resultSet.getDate("register_date")!=null?
                        LocalDate.from(resultSet.getDate("register_date")
                                .toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime()) : null);
        return producto;
    }

    public List<Product> list() {
        List<Product> productoList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from product")) {
            while (resultSet.next()) {
                Product producto = createProduct(resultSet);
                productoList.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productoList;
    }

    public Product byId(Long id) {
        Product producto = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * FROM product WHERE id =?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                producto = createProduct(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public void delete(Long id) {

    }
}

