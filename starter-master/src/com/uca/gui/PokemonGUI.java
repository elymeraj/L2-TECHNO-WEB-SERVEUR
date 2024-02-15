package com.uca.gui;

import com.uca.core.PokemonCore;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class PokemonGUI {

    public static String getAllPokemons() throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("pokemons", PokemonCore.getAllPokemons());

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("pokemons/pokemons.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }
}
