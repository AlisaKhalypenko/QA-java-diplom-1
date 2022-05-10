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

    @Test
    public void setBunsMethodIsCalled(){
        Burger burger = new Burger();
        Bun bun = new Bun("black bun", 100);
        String bunName;
        burger.setBuns(bun);
        bunName = burger.bun.getName();

        Assert.assertEquals("black bun", bunName);
    }

    @Test
    public void addIngredientMethodIsCalled(){
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient (IngredientType.SAUCE, "hot sauce", 100);
        List<Ingredient> ingredients = new ArrayList<>();
        int burgerIngredientsListSize;
        ingredients.add(ingredient);
        burger.addIngredient(ingredient);
        burgerIngredientsListSize = burger.ingredients.size();

        Assert.assertFalse( burger.ingredients.isEmpty());
        Assert.assertEquals(1, burgerIngredientsListSize);
    }

    @Test
    public void removeIngredientRemovesAnItemFromTheList(){
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient (IngredientType.SAUCE, "hot sauce", 100);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);

        Assert.assertTrue( burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientMovesAnItemFromTheList(){
        Burger burger = new Burger();
        Ingredient ingredient1 = new Ingredient (IngredientType.SAUCE, "hot sauce", 100);
        Ingredient ingredient2 = new Ingredient (IngredientType.SAUCE, "cold sauce", 100);
        Ingredient primaryIngredientInBurgerIngredientsList;
        String nameOfPrimaryIngredient;
        List<Ingredient> ingredients = new ArrayList<>();

        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(1, 0);
        primaryIngredientInBurgerIngredientsList = burger.ingredients.get(0);
        nameOfPrimaryIngredient = primaryIngredientInBurgerIngredientsList.getName();

        Assert.assertEquals("cold sauce", nameOfPrimaryIngredient);
    }

    /*к этому тесту вот совсем не понимаю комментарий..."в  методе getPrice участвует не только булочка, но и ингредиенты со своими ценами.
    И это у тебя оказалось не проверенным" - изначально булку замокировала, а ингредиент добавлен же в тесте, и цена у него указана 100,
    и сумма итоговая ожидаемая (была) 300: цена бургера 100*2 + цена ингредиенрта 100...и тест прошёл. Т.е., ингредиент проверен тоже...
    Попыталась замокировать ингредиент, но неверно, видимо, что-то делала. Когда мокировала ингрединет, получала NullPointerException.
    Нууу... в итоге просто добавила к бургеру ещё 1 ингредиент, чтобы убедиться, что в расчёт попадает стоимость обоих ингредиентов списка.*/
    @Mock
    Bun bun;

    @Test
    public void getPriceReturnCorrectPrice(){
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient (IngredientType.SAUCE, "hot sauce", 100);
        List<Ingredient> ingredients = new ArrayList<>();
        Mockito.when(bun.getPrice()).thenReturn(100.00f);
        float actual;
        float expected;

        ingredients.add(ingredient);
        ingredients.add(ingredient);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);

        actual = burger.getPrice();
        expected = 400.00f;
        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void receiptIsCorrect(){
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient (IngredientType.SAUCE, "hot sauce", 100);
        List<Ingredient> ingredients = new ArrayList<>();
        String receiptText;
        Mockito.when(bun.getPrice()).thenReturn(100.00f);
        Mockito.when(bun.getName()).thenReturn("black bun");

        ingredients.add(ingredient);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        receiptText = burger.getReceipt();
        Assert.assertEquals("(==== black bun ====)\r\n" +
                "= sauce hot sauce =\r\n" +
                "(==== black bun ====)\r\n" +
                "\r\n" +
                "Price: 300,000000\r\n", receiptText);
    }
}
