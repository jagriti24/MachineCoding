package Impl;

import pojo.IngredientName;
import pojo.IngredientsPojo;

/*
 @author : jagriti.singh
 @since  : 21/03/21
*/
public class HotCoffee extends Beverages {

    private static HotCoffee hotCoffee=null;

    public HotCoffee(){
        ingredients.add(new IngredientsPojo(IngredientName.HOT_WATER,100));
        ingredients.add(new IngredientsPojo(IngredientName.HOT_MILK,400));
        ingredients.add(new IngredientsPojo(IngredientName.TEA_LEAF_SYRUP,30));
        ingredients.add(new IngredientsPojo(IngredientName.GINGER_SYRUP,30));
        ingredients.add(new IngredientsPojo(IngredientName.SUGAR_SYRUP,50));
    }


    @Override
    public String getName() {
        return "hot_coffee";
    }

    public static Beverages getInstance() {
        if(hotCoffee==null){
            hotCoffee = new HotCoffee();
        }
        return hotCoffee;
    }
}
