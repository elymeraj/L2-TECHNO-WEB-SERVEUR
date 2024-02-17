import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.uca.entity.*;
import com.google.gson.Gson;


public class PokemonAPI {
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
