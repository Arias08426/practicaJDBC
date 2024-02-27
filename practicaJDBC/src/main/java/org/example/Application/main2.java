package org.example.Application;

import org.example.Connetion.conexionBD;
import org.example.model.Product;
import org.example.repository.Impl.RepositoryImpl;
import org.example.repository.Repository;

import java.sql.Connection;
import java.sql.SQLException;

public class main2 {
    public static void main(String[] args){
 try (
    Connection conn = conexionBD.getInstance()){
        Repository<Product> repository = new RepositoryImpl();
        System.out.println("***** List products from database");
        repository.list().stream().forEach(System.out::println);
        System.out.println("***** Get by Id: 1");
        System.out.println(repository.byId(1L).toString());
    }catch (
    SQLException e){
        e.printStackTrace();
    }
}
}

