package com.uca.core;

import com.uca.dao.*;
import com.uca.security.*;
import com.uca.entity.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;


import java.util.ArrayList;
public class UserCore {

    public static ArrayList<User> getAllUsers() {
        return new UserDAO().getAllUsers();
    }

    public static ArrayList<Pokemon> getPokemonsUser( int id){
        return new UserDAO().getPokemonsUser(id);
        
    }

     public static Pokemon ajouterPokemon(int id) 
     {
         Pokemon p = getRandomPokemon();
         return new UserDAO().ajouterPokemon(id,p);

     }



    
    public static User createUser(String username, String password) throws Exception {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        
        return new UserDAO().create(user);
    }

     public static User getByIdentifier(String username, boolean keepPassword) throws IllegalArgumentException {
        User user = new UserDAO().getByIdentifier(username);
        if (user.getId() == 0) {
            throw new IllegalArgumentException("This user does not exists.");
        }
        if (!keepPassword) {
            user.setPassword(null);
        }

        return user;
    }




    private static final String API_BASE_URL = "https://pokeapi.co/api/v2/pokemon-species/";

    public static Pokemon getRandomPokemon() {
        try {
            int randomPokemonId = getRandomPokemonId();
            String apiUrl = API_BASE_URL + randomPokemonId;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            Gson gson = new Gson();
            Pokemon pokemon = gson.fromJson(response.toString(), Pokemon.class);

            return pokemon;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static int getRandomPokemonId() {
        // Générer un ID de Pokémon aléatoire entre 1 et 1008
        return (int) (Math.random() * 1008) + 1;
    }








}
