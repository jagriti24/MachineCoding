package pojo;

/*
 @author : jagriti.singh
 @since  : 21/03/21
*/

public class IngredientsPojo {

    IngredientName name;

    Integer stockCount;

    public IngredientsPojo(IngredientName name, Integer stockCount) {
        this.name = name;
        this.stockCount = stockCount;
    }

    public IngredientName getName() {
        return name;
    }

    public void setName(IngredientName name) {
        this.name = name;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

}
