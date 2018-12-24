package com.example.libjava;

import java.util.ArrayList;

import sun.rmi.runtime.Log;

public class JokeClass {

    public static ArrayList<String> jokes(){

        ArrayList<String> joke = new ArrayList<>();
        joke.add("What did the left eye say to the right eye? \n Between us, something smells!");
        joke.add("What did one plate say to the other plate? \n Dinner is on me!");
        joke.add("What do you call a droid that takes the long way around? \n R2 detour.");
        joke.add("What is a witch’s favorite subject in school? \n Spelling!");
        joke.add("When does a joke become a “dad” joke? \n When the punchline is a parent.");


        return joke;
    }

}
