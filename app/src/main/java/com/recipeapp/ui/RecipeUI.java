package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }
    
    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                        addRecipes();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    public void displayRecipes() {
        try {
            ArrayList<Recipe> data = dataHandler.readData();
            // 受け取ったデータを表示する
            for(Recipe recipe : data){
                System.out.println("-----------------------------------");
                System.out.println("Recipe Name: "+ recipe.getName());
                System.out.print("Main Ingredients: ");
                ArrayList<String> newIngredients = new ArrayList<>();
                for(Ingredient ingredient : recipe.ingredients()){
                    newIngredients.add(ingredient.getName());
                }
                System.out.println(String.join(",", newIngredients));
            }
        } catch (IOException e){
            // エラーが起きた場合の表示
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void addRecipes() {

        try {
            ArrayList<Ingredient> ingredients = new ArrayList<>();

            System.out.println("Adding a new recipe");
            System.out.print("Enter recipe name: ");
            // レシピ名の入力
            String recipeName = reader.readLine();

            while(true) {
                System.out.print("Enter ingredients (type 'done' when finished): ");
                // 材料名の入力
                String ingredientName = reader.readLine();
                // done と入力されたらbreak
                if (ingredientName.equals("done")){
                    break;
                } else {
                    Ingredient ingredient = new Ingredient(ingredientName);
                    ingredients.add(ingredient);
                    continue;
                }
                
            }
            Recipe recipe = new Recipe(recipeName, ingredients);
            dataHandler.writeData(recipe);
            System.out.println("Recipe added successfully." );
        
        } catch (IOException e) {
            // エラーが起きた場合の表示
            System.out.println("Error reading file: " + e.getMessage());
        }

    }
}
