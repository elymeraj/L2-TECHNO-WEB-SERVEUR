package com.uca.dao;

import com.uca.entity.Pokemon;

import java.sql.*;
import java.util.ArrayList;

public class PokemonDAO extends _Generic<Pokemon> {

    public ArrayList<Pokemon> getAllPokemons() {
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM pokemons ORDER BY id ASC;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Pokemon pokemon = new Pokemon();
                pokemon.setId(resultSet.getInt("id"));
                pokemon.setName(resultSet.getString("name"));
                pokemon.setType(resultSet.getString("type"));

                pokemons.add(pokemon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pokemons;
    }

    @Override
    public Pokemon create(Pokemon obj) {
        Connection connection = _Connector.getInstance();

        try {
            PreparedStatement statement;

            statement = connection.prepareStatement("INSERT INTO pokemons(name, type) VALUES(?, ?);");
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getType());
            statement.executeUpdate();

            return obj;
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new RuntimeException("Could not create Pokemon in the database!");
        }
    }

    @Override
    public void delete(Pokemon obj) {
        // TODO: Implement delete operation for Pokemon
    }
}
