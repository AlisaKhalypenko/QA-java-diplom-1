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
        Mockito.verify(burger).setBuns(bun);
    }

    @Test
    public void addIngredientMethodIsCalled(){
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient (IngredientType.SAUCE, "hot sauce", 100);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        burger.addIngredient(ingredient);
        Assert.assertEquals(false, burger.ingredients.isEmpty());
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

        Ingredient primaryIngredientInBurgerIngredientsList = burger.ingredients.get(0);
        String nameOfPrimaryIngredient = primaryIngredientInBurgerIngredientsList.getName();
        Assert.assertEquals("cold sauce", nameOfPrimaryIngredient);
    }

    @Test
    public void getPriceReturnCorrectPrice(){
        Burger burger = new Burger();
        Bun bun = new Bun("black bun", 100);
        Ingredient ingredient = new Ingredient (IngredientType.SAUCE, "hot sauce", 100);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        float actual = burger.getPrice();
        float expected = 300.00f;
        Assert.assertEquals(expected, actual, 0);
    }

    @Mock
    Bun bun;

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

        String receiptText = burger.getReceipt();
        Assert.assertEquals("(==== black bun ====)\r\n" +
                "= sauce hot sauce =\r\n" +
                "(==== black bun ====)\r\n" +
                "\r\n" +
                "Price: 300,000000\r\n", receiptText);
    }
}
