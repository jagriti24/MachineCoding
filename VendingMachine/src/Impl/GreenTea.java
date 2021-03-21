package Impl;

import pojo.IngredientName;
import pojo.IngredientsPojo;

/*
 @author : jagriti.singh
 @since  : 21/03/21
*/
public class GreenTea extends Beverages {

    private static GreenTea greenTea=null;


    public GreenTea(){
        ingredients.add(new IngredientsPojo(IngredientName.HOT_WATER,100));
        ingredients.add(new IngredientsPojo(IngredientName.GINGER_SYRUP,30));
        ingredients.add(new IngredientsPojo(IngredientName.SUGAR_SYRUP,50));
        ingredients.add(new IngredientsPojo(IngredientName.GREEN_MIXTURE,30));
    }

    @Override
    public String getName() {
        return "green_tea";
    }

    public static Beverages getInstance() {
        if(greenTea==null){
            greenTea = new GreenTea();
        }
        return greenTea;
    }
}
