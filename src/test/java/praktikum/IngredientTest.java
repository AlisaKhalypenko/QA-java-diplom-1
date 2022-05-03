package praktikum;

import org.junit.Assert;
import org.junit.Test;

public class IngredientTest {

    @Test
    public void getNameReturnIngredientName(){
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100 );
        String name = ingredient.getName();
        Assert.assertEquals("hot sauce", name);
    }

    @Test
    public void getPriceReturnIngredientPrice(){
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 300.58f);
        float actual = ingredient.getPrice();
        float expected = 300.58f;
        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void getTypeReturnIngredientType(){
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 300.58f);
        IngredientType type = ingredient.getType();
        IngredientType expected = IngredientType.SAUCE;
        Assert.assertEquals(expected, type);
    }

}
