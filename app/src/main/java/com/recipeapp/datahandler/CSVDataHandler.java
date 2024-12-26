package com.recipeapp.datahandler;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;


public class CSVDataHandler implements DataHandler {
    private String filePath;

    public CSVDataHandler() {
        this.filePath = "app/src/main/resources/recipes.csv";
    }
    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getMode() {
        return "CSV";
    }
    
    @Override
    public ArrayList<Recipe> readData() {
        ArrayList<Recipe> data = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            if ((line = reader.readLine()) == null) {
                throw new Exception();
            }
            while((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                for (int i = 1 ; i < values.length; i++) {
                    Ingredient ingredient = new Ingredient(values[i]);
                    ingredients.add(ingredient);
                }
                Recipe recipe = new Recipe(values[0], ingredients);
                data.add(recipe);
                System.out.println("Recipes:");
            }
            
        } catch (Exception e) {
            System.out.println("No recipes available.");
        }
        return data;
    }
    
    @Override
    public void writeData(Recipe recipe) {
    }

    @Override
    public ArrayList<Recipe> searchData(String keyword) {
        return null;
    }
}

