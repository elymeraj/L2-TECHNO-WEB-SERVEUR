package com.uca;
import com.uca.entity.*;

import com.uca.dao._Initializer;
import com.uca.gui.*;

import static spark.Spark.*;

import com.uca.security.doLogin;
import spark.Request;
import java.util.*;



import com.uca.core.*;
public class StartServer {

    public static void main(String[] args) {
        //Configure Spark
        staticFiles.location("/static/");
        port(8081);


        _Initializer.Init();

        //Defining our routes
        get("/users", (req, res) -> {
            User connectedUser = getAuthenticatedUser(req);
            return UserGUI.getAllUsers(connectedUser);
        });

        //---------------------------------------------------------------
        get("/users/:userId/pokemons", (req, res) -> {
            User connectedUser = getAuthenticatedUser(req);

            if (connectedUser == null) {
                res.redirect("/", 301);
                return null;
            }

            int userId = Integer.parseInt(req.params("userId"));
            return UserGUI.getPokemonsUser(connectedUser,userId);
            
            
            });

            get("/", (req, res) -> {
                res.redirect("/users", 301);
        
            
               return null;
            });
        //-----------------------------------------------------------
        post("/users", (req, res) -> {
            User user = UserCore.createUser(req.queryParams("username"), req.queryParams("password"));
          
            res.redirect("/users/" + user.getId(), 301);
            return null;
        });


        //----------------------------------------------------------------------
        //----------------------------------------------------------------------
        get("/pokemons", (req, res) -> {
            return PokemonGUI.getAllPokemons();
        });
        //-----------------------------------------------------------
       post("/pokemons", (req, res) -> {
                String name = req.queryParams("name");
                String type = req.queryParams("type");
                
                // Créer un nouvel objet Pokémon en utilisant les données reçues
                Pokemon pokemon = PokemonCore.createPokemon(name, type);
                
                // Appel à une méthode appropriée pour enregistrer le Pokémon dans la base de données ou effectuer d'autres opérations nécessaires
                
                res.redirect("/pokemons/" + pokemon.getId(), 301);
                return null;
       });



       //-------------------------------------------------------------------------

      



        
 post("/login", (req, res) -> {
            if (req.queryParams("username") != null) {
                String username = req.queryParams("username");
                String password = req.queryParams("password");
                User userEntity = new User();
                userEntity.setUsername(username);
                userEntity.setPassword(password);
                res.cookie("/", "auth", doLogin.login(userEntity), 36000, false, true);
                
            }
            res.redirect("/", 301);
            return "";
        });

        get("/logout", (req, res) -> {
            res.removeCookie("auth");
            res.redirect("/", 301);
            return "";
        });

}

  private static User getAuthenticatedUser(Request req) {
        String token = req.cookie("auth");
        return token == null ? null : doLogin.introspect(token);
    }


}