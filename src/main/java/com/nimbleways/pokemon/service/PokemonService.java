package com.nimbleways.pokemon.service;

import com.nimbleways.pokemon.entitie.Pokemon;
import com.nimbleways.pokemon.repository.PokemonRepository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Scanner;

@Service
@CrossOrigin("*")
public class PokemonService {
    private PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository)

    {
        this.pokemonRepository = pokemonRepository;
    }

    public void initPokemon() {
        try {
            Scanner myReader = new Scanner(new ClassPathResource("data.txt").getFile());

            System.out.println(myReader);
            while (myReader.hasNextLine()) {

                String[] data = myReader.nextLine().split("[,]");

                Pokemon p = new Pokemon(Long.parseLong(data[0]), data[1], Integer.parseInt(data[2]),
                        Integer.parseInt(data[3]));

                this.pokemonRepository.save(p);

            }

            System.out.println(" inialised ------------------------**** ");
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public List<Pokemon> getAll() {
        return this.pokemonRepository.findAll();
    }
}
