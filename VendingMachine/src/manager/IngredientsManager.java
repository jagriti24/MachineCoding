package manager;

import pojo.IngredientName;
import pojo.IngredientsPojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 @author : jagriti.singh
 @since  : 21/03/21
*/
public class IngredientsManager {

    Map<IngredientName, IngredientsPojo> ingredients;

    Map<IngredientName, Integer> threshold;

    public IngredientsManager(List<IngredientsPojo> ingredientsParam, Integer thresholdParam){
        ingredients = new HashMap<>();
        threshold = new HashMap<>();

        if(thresholdParam==null){
            thresholdParam=20;
        }

        for (IngredientsPojo anIngredientsParam : ingredientsParam) {
            if(!ingredients.containsKey(anIngredientsParam.getName())) {
                this.ingredients.put(anIngredientsParam.getName(), anIngredientsParam);
                this.threshold.put(anIngredientsParam.getName(), thresholdParam);
            }
        }
    }

    public void add(IngredientsPojo ingredient){
        if(this.ingredients.containsKey(ingredient.getName())){
            System.out.println("Ingredient already exist");
        }
        else{
            ingredients.put(ingredient.getName(), ingredient);
        }
    }

    public void updateStock(IngredientName name , Integer count){
        if(name!=null && count!=null) {
            ingredients.get(name).setStockCount(count);
        }
    }

    public synchronized void updateStock(List<IngredientsPojo> ingredientsPojos){
        IngredientsPojo b;
        for(IngredientsPojo a : ingredientsPojos){
            if(ingredients.containsKey(a.getName())){
                b= ingredients.get(a.getName());
                b.setStockCount(b.getStockCount()-a.getStockCount());
                if(b.getStockCount()<threshold.get(a.getName())){
                    System.out.println(a.getName()+" is low on stock ! current stock :"+b.getStockCount()+ " , threshold :"+threshold.get(a.getName()));
                }
            }
            else{
                System.out.println(a.getName()+" stock not found in base stock, update failed ");
            }
        }
    }

    public Map<IngredientName,Integer> getStockPerItem(){
        Map<IngredientName,Integer> map = new HashMap<>();
        for(IngredientName n : ingredients.keySet()){
            map.put(n,ingredients.get(n).getStockCount());
        }
        return map;
    }

    public Map<IngredientName, IngredientsPojo> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<IngredientName, IngredientsPojo> ingredients) {
        this.ingredients = ingredients;
    }





}
