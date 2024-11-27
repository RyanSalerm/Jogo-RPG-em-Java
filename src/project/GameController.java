package project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameController {

    @FXML
    private ImageView backgroundImage, userCharacterImage, aiCharacterImage;
    @FXML
    private Label userHealthLabel, aiHealthLabel;
    @FXML
    private Button attackButton;
    @FXML
    private TextArea gameLog;

    private PlayerCharacter player;
    private AICharacter ai;

    @FXML
    public void initialize() {
        backgroundImage.setImage(new Image("/assets/Fundo.gif"));
        userCharacterImage.setImage(new Image("/assets/person.png"));
        aiCharacterImage.setImage(new Image("/assets/Computador.png"));

        player = new PlayerCharacter("Hero", 100, 50, 30, 40, 20);
        ai = new AICharacter("Villain", 100, 45, 35, 35, 25);


        updateHealthBars();
    }

    @FXML
    private void handleAttackAction() {
        int attackValue = player.attack(ai, "common");
        gameLog.appendText("Player attacks with " + attackValue + " power!\n");
        if (ai.getHealth() <= 0) {
            gameLog.appendText("Computer defeated!\n");
            attackButton.setDisable(true);
        }
        updateHealthBars();
        handleAIAction();
    }

    private void handleAIAction() {
        int attackValue = ai.attack(player, "common");
        gameLog.appendText("Computer attacks with " + attackValue + " power!\n");
        if (player.getHealth() <= 0) {
            gameLog.appendText("Player defeated!\n");
            attackButton.setDisable(true);
        }
        updateHealthBars();
    }

    private void updateHealthBars() {
        userHealthLabel.setText("Health: " + player.getHealth());
        aiHealthLabel.setText("Health: " + ai.getHealth());
    }
}
