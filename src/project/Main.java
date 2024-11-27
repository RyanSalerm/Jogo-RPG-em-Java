package project;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Main extends Application {
    private MediaPlayer mediaPlayer;
    private int vidaPersonagem = 200;
    private int vidaComputador = 200;
    Computador computador = new Computador();

    public int getVidaPersonagem() {
        return vidaPersonagem;
    }

    public void setVidaPersonagem(int vidaPersonagem) {
        this.vidaPersonagem = vidaPersonagem;
    }

    public int getVidaComputador() {
        return vidaComputador;
    }

    public void setVidaComputador(int vidaComputador) {
        this.vidaComputador = vidaComputador;
    }

    public void PersonagemBarraVida(StackPane root) {
        Image personagem = new Image("file:assets/person.png");
        ImageView imageView = new ImageView(personagem);
        imageView.setFitWidth(40);
        imageView.setFitHeight(65);
        imageView.setPreserveRatio(true);
        imageView.setTranslateX(-700);
        imageView.setTranslateY(-285);
        root.getChildren().add(imageView);
    }

    public void ComputadorBarraVida(StackPane root) {
        Image personagem = new Image("file:assets/Computadoro.png");
        ImageView imageView = new ImageView(personagem);
        imageView.setFitWidth(50);
        imageView.setFitHeight(75);
        imageView.setPreserveRatio(true);
        imageView.setTranslateX(-690);
        imageView.setTranslateY(-370);
        root.getChildren().add(imageView);
    }

    public void fimDeJogo(StackPane root, Rectangle2D bounds){
        if (getVidaPersonagem() == 0 || getVidaPersonagem() < 0) {
            root.getChildren().clear();
            Image derrotaImage = new Image("file:assets/DEFEAT.png");
            ImageView derrotaView = new ImageView(derrotaImage);
            derrotaView.setFitWidth(bounds.getWidth());
            derrotaView.setFitHeight(bounds.getHeight());
            derrotaView.setPreserveRatio(true);
            root.getChildren().add(derrotaView);
        }

        if (getVidaComputador() == 0 || getVidaComputador() < 0) {
            root.getChildren().clear();
            Image vitoriaImage = new Image("file:assets/WIN.png");
            ImageView vitoriaView = new ImageView(vitoriaImage);
            vitoriaView.setFitWidth(bounds.getWidth());
            vitoriaView.setFitHeight(bounds.getHeight());
            vitoriaView.setPreserveRatio(true);
            root.getChildren().add(vitoriaView);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        // Carregar o arquivo de música
        // caminhoMusica = "file:assets/music.mp3";  // Altere o caminho para o arquivo de música
        //Media musica = new Media(caminhoMusica);
        //mediaPlayer = new MediaPlayer(musica);

        // Configurações do MediaPlayer
        //mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);  // Repetir a música infinitamente
        //mediaPlayer.play();  // Iniciar a reprodução


        Image fundoImagem = new Image("file:assets/Fundo.gif");  // Substitua pelo caminho da sua imagem
        ImageView fundoView = new ImageView(fundoImagem);
        fundoView.setFitWidth(1300);  // Ajuste o tamanho conforme necessário
        fundoView.setFitHeight(1300);
        fundoView.setPreserveRatio(true);

        // Criando o botão e personalizando o estilo
        Button comecarButton = new Button("Comece o jogo");
        comecarButton.setStyle(
                "-fx-font-size: 24px; " +
                        "-fx-padding: 15px 30px; " +
                        "-fx-background-color: #FFB84D; " +  // Cor de fundo do botão (laranja suave)
                        "-fx-text-fill: white; " +  // Cor do texto (branco)
                        "-fx-border-radius: 10px; " +  // Bordas arredondadas
                        "-fx-background-radius: 10px;"  // Bordas arredondadas
        );


        // Definindo ação para o botão
        comecarButton.setOnAction(e -> iniciarJogo(primaryStage));

        // Criando o layout da tela inicial
        StackPane rootInicial = new StackPane();
        rootInicial.getChildren().add(fundoView);  // Adicionando o fundo
        rootInicial.getChildren().add(comecarButton);  // Adicionando o botão sobre o fundo

        // Centralizando o botão
        StackPane.setAlignment(comecarButton, javafx.geometry.Pos.CENTER);

        // Definir a cena inicial
        Scene sceneInicial = new Scene(rootInicial, 600, 400);
        primaryStage.setTitle("Tela Inicial - Magic Fight");
        primaryStage.setScene(sceneInicial);
        primaryStage.show();
    }


    public void iniciarJogo(Stage primaryStage) {
        Usuario usuario = new Usuario(this);
        Image gifFundo = new Image("file:assets/Fundo.gif");
        ImageView gifView = new ImageView(gifFundo);

        gifView.setFitWidth(2745); // Definir o tamanho do fundo
        gifView.setFitHeight(1544); // Definir o tamanho do fundo
        gifView.setPreserveRatio(true); // Garantir que a proporção seja preservada

        StackPane root = new StackPane();
        root.getChildren().add(gifView); // Adicionar o GIF como fundo

        // Definir o restante da tela
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight());
        scene.getStylesheets().add("file:src/project/styles.css");
        primaryStage.getIcons().add(new Image("file:assets/icon.jpg"));
        primaryStage.setTitle("Magic Fight");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        // Adicionar outros elementos
        usuario.Personagem(root);
        PersonagemBarraVida(root);
        ComputadorBarraVida(root);
        usuario.BarraVidaPersonagem(root, vidaPersonagem);
        usuario.BarraVidaComputador(root, vidaComputador);
        usuario.ataquecomum(root, 150, 350);
        usuario.defesacomum(root, 50, 350);
        usuario.magia(root, -50, 350);
        usuario.defesamagica(root, -200, 350);

        System.out.println("VidaPersonagem: "+ getVidaPersonagem());
        System.out.println("VidaComputador: "+ getVidaComputador());

        fimDeJogo(root, bounds);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
