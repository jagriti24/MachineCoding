package Impl.Factory;

import Impl.*;

/*
 @author : jagriti.singh
 @since  : 21/03/21
*/
public class BeverageFactory {

    public Beverages getBeverage(Integer outlet){
       switch(outlet){
            case 1: return HotTea.getInstance();
            case 2: return HotCoffee.getInstance();
            case 3: return BlackTea.getInstance();
            case 4: return GreenTea.getInstance();
            default: ;
        }
        return null;
    }


}
