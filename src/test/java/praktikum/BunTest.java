package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

public class BunTest {

    @Test
    public void getNameReturnBunName(){
        Bun bun = new Bun("GrandBulka", 300);
        String name = bun.getName();
        Assert.assertEquals("GrandBulka", name);
    }

    @Test
    public void getPriceReturnBunPrice(){
        Bun bun = new Bun("GrandBulka", 300.58f);
        float actual = bun.getPrice();
        float expected = 300.58f;
        Assert.assertEquals(expected, actual, 0);
    }

}
