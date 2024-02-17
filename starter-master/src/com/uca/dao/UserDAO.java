package com.uca.dao;

import com.uca.entity.*;

import java.sql.*;
import java.util.*;
import java.sql.ResultSet;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;


public class UserDAO extends _Generic<User> {
	

	public ArrayList<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<>();
	    try {
	        PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM users ORDER BY id desc LIMIT 4 ;");
			
		
	        ResultSet resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            User user = new User();
	            user.setId(resultSet.getInt("id"));
	            user.setUsername(resultSet.getString("username"));
	            user.setPassword(resultSet.getString("password"));

	            users.add(user);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return users;
	}

//--------------------------------------------------------
 public Pokemon ajouterPokemon(int userId, Pokemon pokemon) {
    try {
        // Vérifier si l'utilisateur existe
        User user = getById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User does not exist.");
        }

        // Insérer le Pokémon dans la table user_pokemons
		int niveau = 1;
        PreparedStatement preparedStatement = this.connect.prepareStatement("INSERT INTO userPokemon (user_id, pokemon_id, level) VALUES (?, ?, ?);");
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, pokemon.getId());
        preparedStatement.setInt(3, niveau);
        preparedStatement.executeUpdate();

        // Récupérer l'ID généré du nouvel enregistrement
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int pokemonId = generatedKeys.getInt(1);
            pokemon.setId(pokemonId);
        }

        return pokemon;
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}


//---------------------------------------
public ArrayList<Pokemon> getPokemonsUser(int userId) {
    ArrayList<Pokemon> pokemons = new ArrayList<>();
    try {
        PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT pokemon_id FROM userPokemon WHERE user_id = ?;");
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
			
            int pokemonId = resultSet.getInt("pokemon_id");
            // Appeler l'API pour récupérer les détails du Pokémon
            Pokemon pokemon = getPokemonById(pokemonId); // Supposons que vous avez une méthode pour récupérer un Pokémon par son ID depuis l'API
            
			System.out.println(pokemon.getId());
			pokemons.add(pokemon);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return pokemons;
}








	//------------------------
	@Override

	public User create(User obj)  { 
		Connection connection = _Connector.getInstance();

        try {
            PreparedStatement statement;

            //Init articles table
         
        statement = connection.prepareStatement("INSERT INTO users(username, password) VALUES(?,?);");
        statement.setString(1, obj.getUsername());
        statement.setString(2, obj.getPassword());
        statement.executeUpdate();

        return obj;
        
        } catch (Exception e){
            System.out.println(e.toString());
            throw new RuntimeException("could not create database !");

        }
        
	}

	
	 @Override
	    public void delete(User obj) {
	        //TODO !
	    }




    public User getById(int id) throws IllegalArgumentException {
        try {
            User user = new User();
	            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM users WHERE id = ? LIMIT 1;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = parseFromResultSet(resultSet);
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Could not count user.");
        }
    }


	 public User getByIdentifier(String username) throws IllegalArgumentException {
        try {
            User user = new User();
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM users WHERE username = ? LIMIT 1;");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = parseFromResultSet(resultSet);
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Could not count users.");
        }
    }


	 private User parseFromResultSet(ResultSet resultSet) throws IllegalArgumentException {
		try{
        User entity = new User();
        entity.setId(resultSet.getInt("id"));
        entity.setUsername(resultSet.getString("username"));
        entity.setPassword(resultSet.getString("password"));
        return entity;}

		catch (Exception e){return null ;}
    }


	 private static final String API_BASE_URL = "https://pokeapi.co/api/v2/pokemon";

    public static Pokemon getPokemonById(int pokemonId) {
        try {
            // Construire l'URL de l'API pour récupérer les détails du Pokémon par son ID
            URL url = new URL(API_BASE_URL + "/" + pokemonId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Lire les données de la réponse JSON
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Convertir les données JSON en un objet Pokemon
            Pokemon pokemon = convertJsonToPokemon(response.toString());

            return pokemon;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Méthode pour convertir la réponse JSON en un objet Pokemon
    private static Pokemon convertJsonToPokemon(String json) {
        Gson gson = new Gson();
        Pokemon pokemon = gson.fromJson(json, Pokemon.class);
        return pokemon;
    }

	

    
}
