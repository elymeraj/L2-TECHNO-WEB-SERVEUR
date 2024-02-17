package com.uca.security;

import com.uca.core.UserCore;
import com.uca.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mindrot.jbcrypt.BCrypt;
import com.google.gson.Gson;

import java.util.*;

public class doLogin {

    // Note : In real life, this line should be (and must be !) in a configuration file, not in source code !
    private final static String TOKEN = "QVAlKTzo1zW9VwfGvJtrFZiSOzzEzEyb4Q4qdYIYncKqhd4l7Iasgq8LbesvH01Jk8kA49HNt9fq4M4Lpjpjvysyso7egZNlmHSU";

    public static User introspect(String token) {
        if (token == null || token.isEmpty()) {
            return null;
        }
        try {

            Claims claims = Jwts.parser().setSigningKey(TOKEN).parseClaimsJws(token).getBody();

            User entity = new User();
            entity.setUsername(claims.get("emitter", String.class));
            entity.setId(claims.get("uuid", Integer.class));

            return entity;
        } catch (Exception e) {
            return null;
        }
    }


    public static String login(User entity) throws IllegalArgumentException {

        if (entity.getUsername() == null) {
            throw new IllegalArgumentException("name could not be null");
        }
        if (entity.getPassword() == null || entity.getPassword().isEmpty()) {
            throw new IllegalArgumentException("password could not be null");
        }

        User user;
        try {
            user = UserCore.getByIdentifier(entity.getUsername(), true);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("User does not exists.");
        }

        if (!entity.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

     
  
     
           
       

        UserCore.ajouterPokemon(user.getId()) ;
        Map<String, Object> claims = new HashMap<>();

        claims.put("uuid", user.getId());
        claims.put("id", user.getId());
        claims.put("emitter", user.getUsername());
       try {
    String token = Jwts.builder()
            .setClaims(claims)
            .setId(UUID.randomUUID().toString())
            .setSubject(user.getUsername())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // Expiration after 24 hours
            .signWith(SignatureAlgorithm.HS512, TOKEN)
            .compact();
    
    System.out.println("Token generated successfully: " + token);
    
    return token;
} catch (Exception e) {
    System.out.println("Error generating token: " + e.getMessage());
    e.printStackTrace();
    
    // Vous pouvez ajouter un code de gestion de l'erreur ici, par exemple :
    throw new RuntimeException("Failed to generate token", e);
}


    }
}
