import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Yuliia Tesliuk on 10/21/2018
 */
public class GameModel {
    private Random random;
    private int randomNumber;
    private int lowerBound;
    private int upperBound;
    private ArrayList<Integer> tries;
    private boolean win;

    GameModel(){
        random = new Random();
        lowerBound = 0;
        upperBound = 100;
        randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        tries = new ArrayList<>();
        win = false;
    }

    GameModel(long seed){
        random = new Random(seed);
        lowerBound = 0;
        upperBound = 100;
        randomNumber = random.nextInt(upperBound + 1);
        tries = new ArrayList<>();
        win = false;
    }

    public int guessDeviation (int userInput) {
        if (randomNumber > userInput) {
            lowerBound = userInput + 1;
        } else if (randomNumber < userInput){
            upperBound = userInput - 1;
        } else {
            win = true;
        }
        tries.add(userInput);
        return randomNumber - userInput;
    }

    public boolean isWithinBounds(int userInput) {
        return (userInput >= lowerBound && userInput <= upperBound);
    }

    public ArrayList<Integer> getTries() {
        return tries;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public boolean isWin() {
        return win;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }
}
