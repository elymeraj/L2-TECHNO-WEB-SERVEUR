package com.uca.core;

import com.uca.dao.PokemonDAO;
import com.uca.entity.Pokemon;

import java.util.ArrayList;

public class PokemonCore {

    public static ArrayList<Pokemon> getAllPokemons() {
        return new PokemonDAO().getAllPokemons();
    }
    
    public static Pokemon createPokemon(String name, String type) throws Exception {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(name);
        pokemon.setType(type);

        return new PokemonDAO().create(pokemon);
    }

}
