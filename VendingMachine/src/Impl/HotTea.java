package Impl;

import pojo.IngredientName;
import pojo.IngredientsPojo;

/*
 @author : jagriti.singh
 @since  : 21/03/21
*/
public class HotTea extends Beverages{

    private static HotTea hotTea=null;

    private HotTea(){
        ingredients.add(new IngredientsPojo(IngredientName.HOT_WATER,200));
        ingredients.add(new IngredientsPojo(IngredientName.HOT_MILK,100));
        ingredients.add(new IngredientsPojo(IngredientName.TEA_LEAF_SYRUP,30));
        ingredients.add(new IngredientsPojo(IngredientName.GINGER_SYRUP,10));
        ingredients.add(new IngredientsPojo(IngredientName.SUGAR_SYRUP,10));
    }

    @Override
    public String getName() {
        return "hot_tea";
    }


    public static HotTea getInstance() {
        if(hotTea==null){
            hotTea = new HotTea();
        }
        return hotTea;
    }
}
