import org.junit.Before;
import org.junit.Test;
import pojo.IngredientName;
import pojo.IngredientsPojo;

import java.util.ArrayList;
import java.util.List;


/* 
 @author : jagriti.singh
 @since  : 21/03/21
*/

public class VendingMachineTest {

    private VendingMachine vendingMachine;
    private List<IngredientsPojo> ingredientsList;

    @Before
    public void  initialise(){

        /* could not complete all test cases */


        ingredientsList = new ArrayList<>();
        ingredientsList.add(new IngredientsPojo(IngredientName.HOT_WATER,500));
        ingredientsList.add(new IngredientsPojo(IngredientName.HOT_MILK,500));
        ingredientsList.add(new IngredientsPojo(IngredientName.TEA_LEAF_SYRUP,100));
        ingredientsList.add(new IngredientsPojo(IngredientName.GINGER_SYRUP,100));
        ingredientsList.add(new IngredientsPojo(IngredientName.SUGAR_SYRUP,100));

        vendingMachine = new VendingMachine(5);
        vendingMachine.loadIngredients(ingredientsList,30);
    }

    @Test
    public void getSingleBrewedBeverage() {
        vendingMachine.getBrewedBeverage(1);
    }

    @Test
    public void getTwoBrewedBeverage() {
        vendingMachine.getBrewedBeverage(1);
        vendingMachine.getBrewedBeverage(2);
    }

    @Test
    public void brewWithMissingIngredient() {
        vendingMachine.getBrewedBeverage(4);
    }

    @Test
    public void invalidOption() {
        vendingMachine.getBrewedBeverage(10);
    }

    @Test
    public void callAll() {
        vendingMachine.getBrewedBeverage(1);
        vendingMachine.getBrewedBeverage(2);
        vendingMachine.getBrewedBeverage(3);
        vendingMachine.getBrewedBeverage(4);
        vendingMachine.getBrewedBeverage(5);
    }

    @Test
    public void validateStock() {
    }
}
