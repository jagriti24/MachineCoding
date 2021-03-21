package Impl;

import pojo.IngredientName;
import pojo.IngredientsPojo;

/*
 @author : jagriti.singh
 @since  : 21/03/21
*/
public class BlackTea extends Beverages {

    private static BlackTea blackTea=null;

    public BlackTea(){
        ingredients.add(new IngredientsPojo(IngredientName.HOT_WATER,300));
        ingredients.add(new IngredientsPojo(IngredientName.TEA_LEAF_SYRUP,30));
        ingredients.add(new IngredientsPojo(IngredientName.GINGER_SYRUP,30));
        ingredients.add(new IngredientsPojo(IngredientName.SUGAR_SYRUP,50));
    }

    @Override
    public String getName() {
        return "hot_coffee";
    }

    public static Beverages getInstance() {
        if(blackTea==null){
            blackTea = new BlackTea();
        }
        return blackTea;
    }

}
