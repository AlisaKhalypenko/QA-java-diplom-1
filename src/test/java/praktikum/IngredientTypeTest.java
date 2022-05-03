package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class IngredientTypeTest {
    private final int listItemNumber;
    private final IngredientType expected;

    public IngredientTypeTest(int listItemNumber, IngredientType expected) {
        this.listItemNumber = listItemNumber;
        this.expected = expected;
    }

   @Parameterized.Parameters
    public static Object[][] Constant() {
        return new Object[][] {
                {0, IngredientType.SAUCE},
                {1, IngredientType.FILLING},
        };
    }

    @Test
    public void ingredientTypeContainsRightVaues(){
        IngredientType[] ingredientType = IngredientType.values();
        String [] expected = {"SAUCE", "FILLING"};
        Arrays.equals(expected, ingredientType);
    }

    @Test
    public void theSequenceOfIngredientTypesIsCorrect(){
        List<IngredientType> ingredientType = Arrays.asList(IngredientType.values());
        IngredientType actual = ingredientType.get(listItemNumber);
        Assert.assertEquals(expected, actual);
    }

}
