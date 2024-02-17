package com.uca.dao;

import java.sql.*;

public class _Initializer {

    public static void Init(){
        Connection connection = _Connector.getInstance();

        try {
        	PreparedStatement statement;	

			 

        	statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255) , password VARCHAR(255));");
        	statement.executeUpdate(); 

        	statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS pokemons (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), type VARCHAR(255), base_experience INT);");
        	statement.executeUpdate();

        	statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS userPokemon (id INT PRIMARY KEY AUTO_INCREMENT, user_id INT, pokemon_id INT, level INT, FOREIGN KEY (user_id) REFERENCES users(id));");
        	statement.executeUpdate();

        	statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS trades (id INT PRIMARY KEY AUTO_INCREMENT, user_id INT, pokemon_id INT, trade_user_id INT, trade_pokemon_id INT, FOREIGN KEY (user_id) REFERENCES users(id), FOREIGN KEY (pokemon_id) REFERENCES pokemons(id), FOREIGN KEY (trade_user_id) REFERENCES users(id), FOREIGN KEY (trade_pokemon_id) REFERENCES pokemons(id));");
        	statement.executeUpdate();

            
            
        

        } catch (Exception e){
            System.out.println(e.toString());
            throw new RuntimeException("could not create database !");
        }
    }
}
