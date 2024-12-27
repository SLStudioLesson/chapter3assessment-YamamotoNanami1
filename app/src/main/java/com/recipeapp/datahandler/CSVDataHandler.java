package com.recipeapp.datahandler;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
    public ArrayList<Recipe> readData() throws IOException {
        ArrayList<Recipe> data = new ArrayList<>();
        // recipes.csvからレシピデータを読み込む
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        if ((line = reader.readLine()) == null) {
            System.out.println("No recipes available.");
        } else{
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
        }
        // リスト形式で返す
        reader.close();
        return data;
    }
    
    @Override
    public void writeData(Recipe recipe) throws IOException {
        // ファイルに書き込む
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
        writer.newLine();
        writer.write(recipe.getName() + ",");
        ArrayList<String> newIngredients = new ArrayList<>();
        for(Ingredient ingredient : recipe.ingredients()){
            newIngredients.add(ingredient.getName());
        }
        String writeIngredients = String.join(", ", newIngredients);
        writer.write(writeIngredients);
        writer.close();
    }

    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        return null;
    }
}

