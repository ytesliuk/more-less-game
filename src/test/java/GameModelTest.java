import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Yuliia Tesliuk on 10/22/2018
 */
class GameModelTest {
    private GameModel model;
    private int[] inputs;
    private Map<Integer,Integer> inputDeviationMap;

    @BeforeEach
    void setUp(){
        model = new GameModel(1L); // 97

        inputDeviationMap = new HashMap<>();
        inputDeviationMap.put(model.getLowerBound(),97);
        inputDeviationMap.put(50,47);
        inputDeviationMap.put(model.getUpperBound(), -3);
        inputDeviationMap.put(99,-2);
        inputDeviationMap.put(97, 0);

        // values for isWithinBounds test
        inputs = new int[]{-3,model.getLowerBound() - 1,0,50,100,model.getUpperBound() + 1};

    }

    @Test
    void guessDeviation() {
        for (Integer input : inputDeviationMap.keySet()) {
            int result = model.guessDeviation(input);
            int newLowerBound = input < 97 ? (input + 1) : model.getLowerBound();
            int newUpperBound = input > 97 ? (input - 1) : model.getUpperBound();
            assertAll (
                    "assert all of these",
                    () -> assertEquals((int) inputDeviationMap.get(input),result, "expected" + inputDeviationMap.get(input) + "received" + result),
                    () -> assertEquals(newLowerBound,model.getLowerBound(), "expected lower bound" + newLowerBound + "received" + model.getLowerBound()),
                    () -> assertEquals(newUpperBound,model.getUpperBound(), "expected upper bound" + newUpperBound + "received" + model.getUpperBound())
            );
        }
    }


    @Test
    void isWithinBounds() {
        for (int input : inputs) {
            if (input < model.getLowerBound() || input > model.getUpperBound()) {
                assertFalse(model.isWithinBounds(input), "bounds - [" + model.getLowerBound() + ", " + model.getUpperBound() + "], input - " + input);
            } else {
                assertTrue(model.isWithinBounds(input),"bounds - [" + model.getLowerBound() + ", " + model.getUpperBound() + "], input - " + input);
            }
        }
    }

}