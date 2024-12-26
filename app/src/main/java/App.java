import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import main.java.com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.ui.RecipeUI;
import java.io.*;

public class App {
    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Choose the file format:");
            System.out.println("1. CSV");
            System.out.println("2. JSON");
            System.out.print("Select (1/2): ");
            String choice = reader.readLine();

            //「2」を選択した場合
            if(choice.equals("2")) {
                // JSONDataHandlerのインスタンスを生成
                JSONDataHandler dataHandler = new JSONDataHandler();
                // 生成したインスタンスをcom/recipeappパッケージのRecipeUIに渡す
                RecipeUI recipeUI = new RecipeUI(dataHandler);
                // displayMenuメソッドを呼び出
                recipeUI.displayMenu();
            // 「1」、その他の入力をした場合
            } else {
                // CSVDataHandlerインスタンスを生成
                CSVDataHandler dataHandler = new CSVDataHandler();
                // 生成したインスタンスをcom/recipeappパッケージのRecipeUIに渡す
                RecipeUI recipeUI = new RecipeUI(dataHandler);
                // displayMenuメソッドを呼び出す
                recipeUI.displayMenu();
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}