import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.exceptions.base.MockitoException;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class HippodromeTest {
    @Test
    public void nullNameException(){
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }
    @Test
    public void nullNameMessage(){
        try{
            new Hippodrome(null);
            fail();
        }catch (IllegalArgumentException e){
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }
    @Test
    public void emptyHorseListException(){
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }
    @Test
    public void emptyHorseListMessage(){
       assertEquals("Horses cannot be empty.", (assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>())).getMessage()));
    }
    @Test
    public void getHorses(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30 ; i++) {
            horses.add(new Horse("horse" + i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void move(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horse = mock(Horse.class);
           horses.add(horse);
        }

        new Hippodrome(horses).move();

        for (Horse horse : horses) {
         verify(horse).move();
        }
    }

    @Test
    public void getWinner(){
        Horse horse1 = new Horse("Bucephalus", 1, 4);
        Horse horse2 = new Horse("Ace of Spades", 1, 3);
        Horse horse3 = new Horse("Zephyr", 1, 2);
        Horse horse4 = new Horse("Blaze", 1, 1);

                Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3, horse4));
                assertSame(horse1, hippodrome.getWinner());
    }
}
