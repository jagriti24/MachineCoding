import Impl.Beverages;
import Impl.Factory.BeverageFactory;
import manager.IngredientsManager;
import pojo.IngredientName;
import pojo.IngredientsPojo;

import java.util.List;
import java.util.Map;

/*
 @author : jagriti.singh
 @since  : 21/03/21
*/
public class VendingMachine extends Thread{

    private final Integer noOfOutlets;
    private BeverageFactory beveragesFactory;
    private IngredientsManager ingredientsManager;


    VendingMachine(Integer outlets){
        this.noOfOutlets = outlets;
        this.beveragesFactory = new BeverageFactory();
    }


    public void getBrewedBeverage(Integer option){


        if(!(option>noOfOutlets) && !(option<1 )){
            System.out.println("******************* START **********************************");

            System.out.println("Selected option : " + option);

            Beverages beverages = beveragesFactory.getBeverage(option);
            if(beverages!=null) {
                //validate stock
                synchronized (this) {
                    System.out.println(" Brewing started for : " + beverages.getName());

                    boolean valid = validateStock(beverages, ingredientsManager);

                    if (valid) {
                        ingredientsManager.updateStock(beverages.getIngredients());
                        System.out.println("Current stock after brewing :" + ingredientsManager.getStockPerItem());
                    } else {
                        System.out.println(beverages.getName() + " preparation failed");
                    }
                }
                System.out.println("********************* END ********************************");
            }
        }
        else{
            System.out.println("invalid option :"+ option);
        }

    }

    public void loadIngredients(List<IngredientsPojo> ingredientsList, Integer threshold){
        ingredientsManager = new IngredientsManager(ingredientsList,threshold);
        System.out.println(ingredientsManager.getStockPerItem());
    }

    public void addIngredient(IngredientName name , Integer count){
        ingredientsManager.add(new IngredientsPojo(name, count));
    }

    public void updateStock(IngredientName name , Integer count){
        ingredientsManager.updateStock(name, count);
    }


    private synchronized boolean validateStock(Beverages beverages , IngredientsManager ingredientsManager){
        Map<IngredientName, IngredientsPojo> ingredientsMap = ingredientsManager.getIngredients();
        List<IngredientsPojo> recipie = beverages.getIngredients();

        for(IngredientsPojo a : recipie){
            if(ingredientsMap.containsKey(a.getName())){
                if(ingredientsMap.get(a.getName()).getStockCount()- a.getStockCount() <0){
                    System.out.println(beverages.getName()+" cannot be prepared because "+a.getName()+" is low on stock");
                    return false;
                }
            }
            else{
                System.out.println(beverages.getName()+" cannot be prepared because "+a.getName()+" is missing");
                return false;
            }
        }

        return true;
    }

}
