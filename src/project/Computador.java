package project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.Objects;

public class Computador {
    public ImageView ComputadorView;  // Variável para armazenar a referência da ImageView do Computador
    private boolean emAnimacao = false;
    public Computador() {}
    // Inicializa a imagem do computador
    public void Computador1(StackPane root, String diretorio) {
        // Criação da imagem do computador
        if(Objects.equals(diretorio, "file:assets/ComputadorAtaque.gif")){
            Image personagem = new Image(diretorio);
            ComputadorView = new ImageView(personagem);  // Atribuindo a referência para ComputadorView
            ComputadorView.setFitWidth(400);
            ComputadorView.setFitHeight(600);
            ComputadorView.setPreserveRatio(true);
            ComputadorView.setTranslateX(500);
            ComputadorView.setTranslateY(160);
            root.getChildren().add(ComputadorView);
        }
        else if (Objects.equals(diretorio, "file:assets/ComputadorMagia.gif")) {
            Image personagem = new Image(diretorio);
            ComputadorView = new ImageView(personagem);  // Atribuindo a referência para ComputadorView
            ComputadorView.setFitWidth(700);
            ComputadorView.setFitHeight(900);
            ComputadorView.setPreserveRatio(true);
            ComputadorView.setTranslateX(500);
            ComputadorView.setTranslateY(200);
            root.getChildren().add(ComputadorView);
        }
        else if (Objects.equals(diretorio, "file:assets/ComputadorDefesa.gif")) {
            Image personagem = new Image(diretorio);
            ComputadorView = new ImageView(personagem);  // Atribuindo a referência para ComputadorView
            ComputadorView.setFitWidth(300);
            ComputadorView.setFitHeight(500);
            ComputadorView.setPreserveRatio(true);
            ComputadorView.setTranslateX(500);
            ComputadorView.setTranslateY(180);
            root.getChildren().add(ComputadorView);
        }
        else if (Objects.equals(diretorio, "file:assets/Computador.png")) {
            Image personagem = new Image(diretorio);
            ComputadorView = new ImageView(personagem);  // Atribuindo a referência para ComputadorView
            ComputadorView.setFitWidth(200);
            ComputadorView.setFitHeight(300);
            ComputadorView.setPreserveRatio(true);
            ComputadorView.setTranslateX(500);
            ComputadorView.setTranslateY(120);
            root.getChildren().add(ComputadorView);

        }else {
            // Caso já exista uma ComputadorView, só altere a imagem
            ComputadorView.setImage(new Image(diretorio));
        }
    }


    // Função para voltar para a imagem normal do computador após um tempo
    public void VoltaParaImagemNormal(StackPane root) {
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(1),
                        event -> {
                            Image personagemNormal = new Image("file:assets/png");
                            ComputadorView.setImage(personagemNormal); // Atualiza a imagem do ComputadorView
                        }
                )
        );
        timeline.setCycleCount(1);
        timeline.play();
    }

    public int atualizarComputador(StackPane root) {
        ImageView ComputadorView = new ImageView();
        int op = RandomUtils.decisaoComputador();
        System.out.println(op);
        if (op == -20) {
            Computador1(root, "file:assets/ComputadorAtaque.gif");
            VoltaParaImagemNormal(root);
            System.out.println("Computador Ataque");
        } else if (op == 20) {
            Computador1(root, "file:assets/ComputadorDefesa.gif");
            VoltaParaImagemNormal(root);
            System.out.println("Computador Defesa");
        } else if (op == -40) {
            Computador1(root, "file:assets/ComputadorMagia.gif");
            VoltaParaImagemNormal(root);
            System.out.println("Computador Magia");
        } else if (op == 40) {
            Computador1(root, "file:assets/ComputadorDefesa.gif");
            VoltaParaImagemNormal(root);
            System.out.println("Computador Defesa Magica");
        }
        return op;
    }
}
