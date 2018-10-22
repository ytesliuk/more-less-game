import java.util.Scanner;

/**
 * Created by Yuliia Tesliuk on 10/21/2018
 */
class GameController {
    private GameModel model;
    private GameView view;
    private Scanner input;
    private int incorrectInput;

    GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.input = new Scanner (System.in);
    }

    void game(){
        view.showMessage("Let's play a game. I'm thinking about a number in a range [" + model.getLowerBound() + ", " + model.getUpperBound() + "]. Try to guess it!");
        String userInput;
        do {
            userInput = input.nextLine();
            if(isValidInput(userInput)) {
                int value = Integer.parseInt(userInput);
                int guessResult = model.guessDeviation(value);
                if (guessResult != 0) {
                    view.showMessage(userInput + " is not my number! Try a " + ((guessResult > 0) ? "higher " : "lower ") + "one. New range is [" + model.getLowerBound() + ", " + model.getUpperBound() + "]");
                }
            } else {
                view.showMessage("Incorrect input. Please check that you enter an integer within a current range [" + model.getLowerBound() + ", " + model.getUpperBound() + "]");
                incorrectInput++;
            }
        } while (!model.isWin());
        view.showMessage("Congratulations! " + userInput + " is my number");
        view.showMessage(statistics());
    }

    private String statistics() {
        StringBuilder results = new StringBuilder();
        results.append("Total number of valid attempts is ").append(model.getTries().size()).append(".\n")
                .append("Total number of wrong entries is ").append(incorrectInput).append(".\n");
        for (Integer i : model.getTries()) {
            results.append("attempt #").append(model.getTries().indexOf(i) + 1).append(": ")
                    .append(i).append(", deviation is ").append(model.getRandomNumber() - i).append("\n");
        }
        return results.toString();
    }

    private boolean isValidInput(String input){
        try {
            int guess = Integer.parseInt(input);
            return (model.isWithinBounds(guess));
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
