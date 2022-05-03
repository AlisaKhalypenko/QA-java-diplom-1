package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Burger burger;

    @Test
    public void setBunsMethodIsCalled(){
        Bun bun = new Bun("black bun", 100);
        burger.setBuns(bun);
        Mockito.verify(burger).setBuns(Mockito.any());
    }

    @Test
    public void addIngredientMethodIsCalled(){
        Ingredient ingredient = new Ingredient (IngredientType.SAUCE, "hot sauce", 100);
        burger.addIngredient(ingredient);
        Mockito.verify(burger).addIngredient(Mockito.any());
    }

    @Test
    public void removeIngredientRemovesAnItemFromTheList(){
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient (IngredientType.SAUCE, "hot sauce", 100);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        Assert.assertEquals(true, burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientMovesAnItemFromTheList(){
        Burger burger = new Burger();
        Ingredient ingredient1 = new Ingredient (IngredientType.SAUCE, "hot sauce", 100);
        Ingredient ingredient2 = new Ingredient (IngredientType.SAUCE, "cold sauce", 100);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(1, 0);

        Ingredient firstPosition = burger.ingredients.get(0);
        String str = firstPosition.getName();
        Assert.assertEquals("cold sauce", str);
    }

    @Mock
    Bun bun;

    @Test
    public void getPriceReturnCorrectPrice(){
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient (IngredientType.SAUCE, "hot sauce", 100);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        Mockito.when(bun.getPrice()).thenReturn(100.00f);

        float actual = burger.getPrice();
        float expected = 300.00f;
        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void receiptIsCorrect(){
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient (IngredientType.SAUCE, "hot sauce", 100);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        Mockito.when(bun.getPrice()).thenReturn(100.00f);
        Mockito.when(bun.getName()).thenReturn("black bun");

        String str = burger.getReceipt();
        Assert.assertTrue("(==== black bun ====)\n" +
                "= sauce hot sauce =\n" +
                "(==== black bun ====)\n" +
                "\n" +
                "Price: 300,000000", true);
    }
}
