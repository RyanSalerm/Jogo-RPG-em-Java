package project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.util.Duration;

public class Usuario {
    public int resultado;
    public int op;
    private ImageView personagemView;
    private final Main main;
    //public Usuario() {}
    public Usuario(Main main) {
        this.main = main;
    }
    Computador computador = new Computador();

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public void setOp(int op) {
        this.op = op;
    }

    public int getResultado() {
        return resultado;
    }

    public int getOp() {
        return op;
    }

    public void atualizaBarraVida(int usu, int com){
        int vidaPersonagem = main.getVidaPersonagem();
        int vidaComputador = main.getVidaComputador();
        System.out.println("Usuário: "+ usu);
        System.out.println("Computador: "+com);

        if(usu > 0 && com > 0){
            //É defesa x defesa ok
            usu = vidaPersonagem + usu;
            com = vidaComputador + com;
            System.out.println("Usuário: "+ usu);
            System.out.println("Computador: "+com);

            if(usu > 200){
                main.setVidaPersonagem(200);
            }
            else if(com > 200){
                main.setVidaComputador(200);
            }
            else{
                main.setVidaPersonagem(usu);
                main.setVidaComputador(com);
            }
        }
        else if(usu < 0 && com < 0){
            //É ataque x ataque; ok
            int usu1 = vidaPersonagem + com;
            com = vidaComputador + usu;
            System.out.println("Usuário: "+ usu1);
            System.out.println("Computador: "+com);

            main.setVidaPersonagem(usu1);
            main.setVidaComputador(com);
        }
        else if(usu > 0 && com < 0){
            //É defesa x ataque; ok
            usu = (vidaPersonagem + com) + usu;
            System.out.println("Usuário: "+ usu);
            System.out.println("Computador: "+vidaComputador);

            if(usu > 200){
                main.setVidaPersonagem(200);
            } else {
                main.setVidaPersonagem(usu);
            }

        }
        else if(usu < 0 && com > 0){
            //É ataque x defesa; ok
            com = (vidaComputador + usu) + com;
            System.out.println("Usuário: "+ vidaPersonagem);
            System.out.println("Computador: "+com);

            if(com > 200){
                main.setVidaComputador(200);
            } else{
                main.setVidaComputador(com);
            }
        }
    }

    public void Personagem(StackPane root) {
        // Carregar a imagem do personagem (inicial)
        Image personagem = new Image("file:assets/person.png");
        personagemView = new ImageView(personagem);

        // Definir o tamanho da imagem
        personagemView.setFitWidth(220); // Largura de 800 pixels
        personagemView.setFitHeight(320); // Altura de 1200 pixels
        personagemView.setPreserveRatio(true); // Manter a proporção da imagem

        // Posicionar a imagem
        personagemView.setTranslateX(-500); // Move 500 pixels para a esquerda
        personagemView.setTranslateY(220); // Move 100 pixels para baixo

        // Adicionar o personagem ao layout
        root.getChildren().add(personagemView);
    }

    public int decisaoUsuario(String acao) {
        return switch (acao.toLowerCase()) {
            case "ataque" -> -20; // Ataque = -20
            case "defesa" -> 20;  // Defesa = +20
            case "magia" -> -40; // Magia = -40
            case "defesamagica" -> 40;  // Defesa Mágica = +40
            default -> throw new IllegalArgumentException("Ação desconhecida: " + acao);
        };
    }

    public void BarraVidaPersonagem(StackPane root, double vida) {
        for (int i = 0; i < 10; i++) {
            Rectangle risquinho = new Rectangle(20, 20); // Cada risquinho terá 20px de largura
            risquinho.setFill(i < (vida / 20) ? Color.YELLOW : Color.DARKGRAY); // Define a cor conforme a vida
            risquinho.setTranslateX(-650 + (i * 20)); // Posicionar os risquinhos lado a lado
            risquinho.setTranslateY(-350); // A posição Y de todos os risquinhos
            root.getChildren().add(risquinho); // Adicionar o risquinho ao layout
        }
    }

    public void BarraVidaComputador(StackPane root, double vida) {
        for (int i = 0; i < 10; i++) {
            Rectangle risquinho = new Rectangle(20, 20); // Cada risquinho terá 20px de largura
            risquinho.setFill(i < (vida / 20) ? Color.BLUE : Color.DARKGRAY); // Define a cor conforme a vida
            risquinho.setTranslateX(-650 + (i * 20)); // Posicionar os risquinhos lado a lado
            risquinho.setTranslateY(-270); // A posição Y de todos os risquinhos
            root.getChildren().add(risquinho); // Adicionar o risquinho ao layout
        }
    }

    public void ataquecomum(StackPane root, double posX, double posY) {
        Button attackButton = new Button("Atacar");

        // Ajustar posição
        attackButton.setTranslateX(posX);
        attackButton.setTranslateY(posY);

        // Evento de clique do botão
        attackButton.setOnAction(e -> {
            // Alterar para a imagem de ataque
            Image personagemAtacando = new Image("file:assets/personAtaque.gif");
            personagemView.setImage(personagemAtacando);

            // Chamar a função decisaoUsuario com a ação "ataque"
            int resultado = decisaoUsuario("ataque"); // Chama a função com a ação "ataque"
            System.out.println("Resultado da ação 'Ataque': " + resultado); // Exemplo de como capturar o resultado
            int op = computador.atualizarComputador(root);

            // Voltar para a imagem normal após 1 segundo
            Timeline timeline = new Timeline(
                    new KeyFrame(
                            Duration.seconds(1),
                            event -> {
                                Image personagemNormal = new Image("file:assets/person.png");
                                personagemView.setImage(personagemNormal);
                            }
                    )
            );
            timeline.setCycleCount(1);
            timeline.play();

            atualizaBarraVida(resultado, op);
            BarraVidaPersonagem(root, main.getVidaPersonagem());
            BarraVidaComputador(root, main.getVidaComputador());

            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            main.fimDeJogo(root, bounds);
        });

        // Adicionar o botão ao layout
        root.getChildren().add(attackButton);
    }

    public void defesacomum(StackPane root, double posX, double posY) {
        Button defesaButton = new Button("Defesa");

        // Ajustar posição
        defesaButton.setTranslateX(posX);
        defesaButton.setTranslateY(posY);

        // Evento de clique do botão
        defesaButton.setOnAction(e -> {
            // Alterar para a imagem de defesa
            Image personagemDefendendo = new Image("file:assets/personDefesa.gif");
            personagemView.setImage(personagemDefendendo);

            // Chamar a função decisaoUsuario com a ação "defesa"
            int resultado = decisaoUsuario("defesa");
            System.out.println("Resultado da ação 'Defesa': " + resultado);
            int op = computador.atualizarComputador(root);
            // Voltar para a imagem normal após 1 segundo
            Timeline timeline = new Timeline(
                    new KeyFrame(
                            Duration.seconds(1),
                            event -> {
                                Image personagemNormal = new Image("file:assets/person.png");
                                personagemView.setImage(personagemNormal);
                            }
                    )
            );
            timeline.setCycleCount(1);
            timeline.play();
            //atualizaBarraVida(resultado, op);
            this.resultado = resultado;
            this.op = op;

            atualizaBarraVida(resultado, op);
            BarraVidaPersonagem(root, main.getVidaPersonagem());
            BarraVidaComputador(root, main.getVidaComputador());

            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            main.fimDeJogo(root, bounds);
        });
        root.getChildren().add(defesaButton);
    }

    public void magia(StackPane root, double posX, double posY) {
        Button magiaButton = new Button("Magia");

        // Ajustar posição
        magiaButton.setTranslateX(posX);
        magiaButton.setTranslateY(posY);

        // Evento de clique do botão
        magiaButton.setOnAction(e -> {
            // Alterar para a imagem de magia
            Image personagemMagia = new Image("file:assets/personMagia.gif");
            personagemView.setImage(personagemMagia);

            // Chamar a função decisaoUsuario com a ação "magia"
            int resultado = decisaoUsuario("magia");
            System.out.println("Resultado da ação 'Magia': " + resultado);
            int op = computador.atualizarComputador(root);
            // Voltar para a imagem normal após 1 segundo
            Timeline timeline = new Timeline(
                    new KeyFrame(
                            Duration.seconds(1),
                            event -> {
                                Image personagemNormal = new Image("file:assets/person.png");
                                personagemView.setImage(personagemNormal);
                            }
                    )
            );
            timeline.setCycleCount(1);
            timeline.play();
            //atualizaBarraVida(resultado, op);
            this.resultado = resultado;
            this.op = op;

            atualizaBarraVida(resultado, op);
            BarraVidaPersonagem(root, main.getVidaPersonagem());
            BarraVidaComputador(root, main.getVidaComputador());

            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            main.fimDeJogo(root, bounds);
        });
        root.getChildren().add(magiaButton);
    }

    public void defesamagica(StackPane root, double posX, double posY) {
        BarraVidaPersonagem(root, 200);
        Button defesaMagicaButton = new Button("Defesa Mágica");

        // Ajustar posição
        defesaMagicaButton.setTranslateX(posX);
        defesaMagicaButton.setTranslateY(posY);

        // Evento de clique do botão
        defesaMagicaButton.setOnAction(e -> {
            // Alterar para a imagem de defesa mágica
            Image personagemDefesaMagica = new Image("file:assets/personDefesaMagica.gif");
            personagemView.setImage(personagemDefesaMagica);

            // Chamar a função decisaoUsuario com a ação "defesamagica"
            int resultado = decisaoUsuario("defesamagica");
            System.out.println("Resultado da ação 'Defesa Mágica': " + resultado);
            int op = computador.atualizarComputador(root);
            // Voltar para a imagem normal após 1 segundo
            Timeline timeline = new Timeline(
                    new KeyFrame(
                            Duration.seconds(1),
                            event -> {
                                Image personagemNormal = new Image("file:assets/person.png");
                                personagemView.setImage(personagemNormal);
                            }
                    )
            );
            timeline.setCycleCount(1);
            timeline.play();
            this.resultado = resultado;
            this.op = op;

            atualizaBarraVida(resultado, op);
            BarraVidaPersonagem(root, main.getVidaPersonagem());
            BarraVidaComputador(root, main.getVidaComputador());

            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            main.fimDeJogo(root, bounds);
        });

        // Adicionar o botão ao layout
        root.getChildren().add(defesaMagicaButton);
    }
}
