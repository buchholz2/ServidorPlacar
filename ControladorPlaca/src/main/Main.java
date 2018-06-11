package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage primaryStage;
    public static Scene sceneBasquete, scenePrincipal;
    public static Class thisClass;
    public static Socket cliente;
    public static ObjectInputStream entrada;
    public static ObjectOutputStream saida;

    /**
     * A classe principal da aplicação em JavaFX
     */
    public Main() throws IOException {
        thisClass = getClass();
    }

    /**
     * Inicia o layout da aplicação
     *
     * @throws java.io.IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        setStage(primaryStage);

        Font.loadFont(this.getClass().getResource("/estilos/fontes/digi.ttf").toExternalForm(), 23.8);
        Font.loadFont(this.getClass().getResource("/estilos/fontes/SoccerLeague.ttf").toExternalForm(), 23.8);

        loadScene("/view/FXMLLoginCadastro.fxml");

    }

    public static void main(String[] args) throws IOException {
        setSocket(new Socket("localhost", 12345));
        entrada = new ObjectInputStream(getSocket().getInputStream());
        saida = new ObjectOutputStream(getSocket().getOutputStream());
        launch(args);
    }

    public void setStage(Stage s) {
        this.primaryStage = s;
    }

    public static Stage getStage() {
        return primaryStage;
    }

    public static void loadScene(String local) {

        try {
            Parent root = FXMLLoader.load(thisClass.getClass().getResource(local));
            scenePrincipal = new Scene(root);
            Platform.runLater(() -> {
                
                primaryStage.setScene(scenePrincipal);
                primaryStage.centerOnScreen();
                primaryStage.setResizable(false);
                primaryStage.setTitle("Controlador");
                primaryStage.show();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String mandaMSG(String msg) throws IOException {

        saida.writeUTF(msg);
        saida.flush();

        String retorno = entrada.readUTF();
        System.out.println(retorno);

//        entrada.close();
//        saida.close();
        return retorno;
    }

    private static void setSocket(Socket sok) {
        Main.cliente = sok;
    }

    private static Socket getSocket() {
        return Main.cliente;
    }

}
