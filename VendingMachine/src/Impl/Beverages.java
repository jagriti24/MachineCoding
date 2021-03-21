package Impl;

import pojo.IngredientName;
import pojo.IngredientsPojo;

import java.util.ArrayList;
import java.util.List;

/*
 @author : jagriti.singh
 @since  : 21/03/21
*/
public abstract class Beverages {

    public List<IngredientsPojo> ingredients;

    Beverages(){
        ingredients = new ArrayList<>();
    }


    public List<IngredientsPojo> getIngredients() {
        return ingredients;
    }

    public void updateIngredientQuantity(IngredientName name, Integer quantity) {
        if(quantity != null) {
            for (IngredientsPojo a : ingredients) {
                if (name == a.getName()) {
                    a.setStockCount(quantity);
                }
            }
        }
    }

    public void addIngredientQuantity(IngredientName name, Integer quantity) {
        if(quantity != null) {
            for (IngredientsPojo a : ingredients) {
                if (name == a.getName()) {
                    a.setStockCount(a.getStockCount()+quantity);
                }
            }
        }
    }

    public void removeIngredient(IngredientName name) {
        for (IngredientsPojo a : ingredients) {
            if (name == a.getName()) {
                ingredients.remove(a);
            }
        }
    }

    abstract public String getName();

}
