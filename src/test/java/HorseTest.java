import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void nullNameException(){
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }
    @Test
    public void nullNameMessage(){
        try{
            new Horse(null, 1, 1);
            fail();
        }catch (IllegalArgumentException e){
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings =  {"", "  ", "\t\t", "\n\n\n"})
    public void blankNameException(String name){
IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
assertEquals("Name cannot be blank.", e.getMessage());
    }
@Test
    public void wrongSecondParameterException(){
    assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1, 1));
    }
    @Test
    public void wrongSecondParameterMessage(){
        try{
            new Horse("name", -1, 1);
            fail();
        }catch (IllegalArgumentException e){
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }
    @Test
    public void wrongThirdParameterException(){
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", 1, -1));
    }
    @Test
    public void wrongThirdParameterMessage(){
        try{
            new Horse("name", 1, -1);
            fail();
        }catch (IllegalArgumentException e){
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }

@Test
    public void getName() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("name", 1, 1);
    Field name = horse.getClass().getDeclaredField("name");
    name.setAccessible(true);
    assertEquals("name", name.get(horse));
}
@Test
    public void getSpeed(){
        double speed = 50;
        Horse horse = new Horse("name", speed, 1);
        assertEquals(speed, horse.getSpeed());
}
@Test
    public void setDistance(){
        double distance = 50;
        Horse horse = new Horse("name", 1, distance);
        assertEquals(distance, horse.getDistance());
}
@Test
    public void getDistanceByDefault(){
        Horse horse = new Horse("name", 1);
        assertEquals(0, horse.getDistance());
}
@Test
    public void moveUseWithRandom(){
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("name", 10, 100).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(anyDouble(),anyDouble()));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.9, 1.0, 999.999, 0.0})
    public void move(double random){
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("name", 1, 10);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(random);

            horse.move();

            assertEquals(10 + 1 * random, horse.getDistance());
        }
    }

}
