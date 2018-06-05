package controladorplaca;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.Main;

public class FXMLControladorPlacarController implements Initializable {

    private boolean fimcrono = true;
    private int pontosV = 0;
    private int pontosL = 0;

    @FXML
    private AnchorPane aPJanelaP;

    @FXML
    private Label jLCronometro;

    @FXML
    private Label jLPontosLocal;

    @FXML
    private Label jLPontosVisitante;

    @FXML
    private Label jLFaltasVisitante;

    @FXML
    private Label jLFaltasLocal;

    @FXML
    private Label jLRodada1;

    @FXML
    private Label jLRodada2;

    @FXML
    private Label jLRodada3;

    @FXML
    private Label jLRodada4;

    @FXML
    private Label jLRodadaEX;

    @FXML
    private Label jLTempoLancamento;

    @FXML
    private Button jBAlteraNome;

    @FXML
    private Button jBMaisUmPontosL;

    @FXML
    private Button jBMenosUmPontosL;

    @FXML
    private Button jBMaisDoisPontosL;

    @FXML
    private Button jBMenosDoisPontosL;

    @FXML
    private Button jBMaisTresPontosL;

    @FXML
    private Button jBMenosTresPontosL;

    @FXML
    private TextField jTFAlteraNomeLocal;

    @FXML
    private Button jBMaisUmPontosV;

    @FXML
    private Button jBMenosUmPontosV;

    @FXML
    private Button jBMaisDoisPontosV;

    @FXML
    private Button jBMenosDoisPontosV;

    @FXML
    private Button jBMaisTresPontosV;

    @FXML
    private Button jBMenosTresPontosV;

    @FXML
    private TextField jTFAlteraNomeVisitante;

    @FXML
    private Button jBMaisFaltaL;

    @FXML
    private Button jBMenosFaltaL;

    @FXML
    private Button jBMaisFaltaV;

    @FXML
    private Button jBMenosFaltaV;

    @FXML
    private Button jBNovoQuarto;

    @FXML
    private Button jBIniciaCrono;

    @FXML
    private Button jBReiniciaLancamento;

    @FXML
    private Button jBReiniciaCrono;

    @FXML
    private Button jBDefineCrono;

    @FXML
    private ToggleButton jTBPausaCrono;

    @FXML
    private TextField jTFDefineCrono;

    @FXML
    private Button jBRestauraTudo;

    @FXML
    void alteraNomes(MouseEvent event) {

    }

    @FXML
    void defineValorCrono(MouseEvent event) {

    }

    @FXML
    void iniciaCrono(MouseEvent event) throws IOException {
        String retorno = Main.mandaMSG("#INICIA_CRONO$" + jTFDefineCrono.getText());
        if (retorno.equals("CRONOS_INICIADO")) {
            String corte[] = jTFDefineCrono.getText().split("\\:");
            int min = Integer.parseInt(corte[0]);
            int seg = Integer.parseInt(corte[1]);
            //int mili = Integer.parseInt(corte[3]);
            System.out.println("Chegou Incia Cronos Preview");
            Thread th = new Thread(iniciaCronosPreview(jLCronometro, min, seg, 00));
            th.setDaemon(true);
            th.start();
        }
    }

    @FXML
    void maisDoisL(MouseEvent event) throws IOException {
        String retorno = Main.mandaMSG("#TIME$LOCAL$SOMA_PONTO$DOIS$");
        if (retorno.equals("#OK")) {
            pontosL = pontosL + 2;
            if (pontosL > 9) {
                jLPontosLocal.setText("" + pontosL);
            } else {
                jLPontosLocal.setText("0" + pontosL);
            }

        }
    }

    @FXML
    void maisDoisV(MouseEvent event) throws IOException {
        String retorno = Main.mandaMSG("#TIME$VISITANTE$SOMA_PONTO$DOIS$");
        if (retorno.equals("#OK")) {
            pontosV = pontosV + 2;
            if (pontosV > 9) {
                jLPontosVisitante.setText("" + pontosV);
            } else {
                jLPontosVisitante.setText("0" + pontosV);
            }

        }
    }

    @FXML
    void maisFaltaL(MouseEvent event) {

    }

    @FXML
    void maisFaltaV(MouseEvent event) {

    }

    @FXML
    void maisTresL(MouseEvent event) throws IOException {
        String retorno = Main.mandaMSG("#TIME$LOCAL$SOMA_PONTO$TRES$");
        if (retorno.equals("#OK")) {
            pontosL = pontosL + 3;
            if (pontosL > 9) {
                jLPontosLocal.setText("" + pontosL);
            } else {
                jLPontosLocal.setText("0" + pontosL);
            }

        }
    }

    @FXML
    void maisTresV(MouseEvent event) throws IOException {
        String retorno = Main.mandaMSG("#TIME$VISITANTE$SOMA_PONTO$TRES$");
        if (retorno.equals("#OK")) {
            pontosV = pontosV + 3;
            if (pontosV > 9) {
                jLPontosVisitante.setText("" + pontosV);
            } else {
                jLPontosVisitante.setText("0" + pontosV);
            }

        }
    }

    @FXML
    void maisUmL(MouseEvent event) throws IOException {
        String retorno = Main.mandaMSG("#TIME$LOCAL$SOMA_PONTO$UM$");
        if (retorno.equals("#OK")) {
            pontosL = pontosL + 1;
            if (pontosL > 9) {
                jLPontosLocal.setText("" + pontosL);
            } else {
                jLPontosLocal.setText("0" + pontosL);
            }

        }
    }

    @FXML
    void maisUmV(MouseEvent event) throws IOException {
        String retorno = Main.mandaMSG("#TIME$VISITANTE$SOMA_PONTO$UM$");
        if (retorno.equals("#OK")) {
            pontosV = pontosV + 1;
            if (pontosV > 9) {
                jLPontosVisitante.setText("" + pontosV);
            } else {
                jLPontosVisitante.setText("0" + pontosV);
            }

        }
    }

    @FXML
    void menosDoisL(MouseEvent event) throws IOException {
        String retorno = Main.mandaMSG("#TIME$LOCAL$SUB_PONTO$DOIS$");
        if (retorno.equals("#OK")) {
            pontosL = pontosL - 2;
            if (pontosL > 9) {
                jLPontosLocal.setText("" + pontosL);
            } else {
                jLPontosLocal.setText("0" + pontosL);
            }

        }
    }

    @FXML
    void menosDoisV(MouseEvent event) throws IOException {
        String retorno = Main.mandaMSG("#TIME$VISITANTE$SUB_PONTO$DOIS$");
        if (retorno.equals("#OK")) {
            pontosV = pontosV - 2;
            if (pontosV > 9) {
                jLPontosVisitante.setText("" + pontosV);
            } else {
                jLPontosVisitante.setText("0" + pontosV);
            }
        }
    }

    @FXML
    void menosFaltaL(MouseEvent event) {

    }

    @FXML
    void menosFaltaV(MouseEvent event) {

    }

    @FXML
    void menosTresL(MouseEvent event) throws IOException {
        String retorno = Main.mandaMSG("#TIME$LOCAL$SUB_PONTO$TRES$");
        if (retorno.equals("#OK")) {
            pontosL = pontosL - 3;
            if (pontosL > 9) {
                jLPontosLocal.setText("" + pontosL);
            } else {
                jLPontosLocal.setText("0" + pontosL);
            }
        }
    }

    @FXML
    void menosTresV(MouseEvent event) throws IOException {
        String retorno = Main.mandaMSG("#TIME$VISITANTE$SUB_PONTO$TRES$");
        if (retorno.equals("#OK")) {
            pontosV = pontosV - 3;
            if (pontosV > 9) {
                jLPontosVisitante.setText("" + pontosV);
            } else {
                jLPontosVisitante.setText("0" + pontosV);
            }
        }
    }

    @FXML
    void menosUmL(MouseEvent event) throws IOException {
        String retorno = Main.mandaMSG("#TIME$LOCAL$SUB_PONTO$UM$");
        if (retorno.equals("#OK")) {
            pontosL = pontosL - 1;
            if (pontosL > 9) {
                jLPontosLocal.setText("" + pontosL);
            } else {
                jLPontosLocal.setText("0" + pontosL);
            }
        }
    }

    @FXML
    void menosUmV(MouseEvent event) throws IOException {
        String retorno = Main.mandaMSG("#TIME$VISITANTE$SUB_PONTO$UM$");
        if (retorno.equals("#OK")) {

            pontosV = pontosV - 1;
            if (pontosV > 9) {
                jLPontosVisitante.setText("" + pontosV);
            } else {
                jLPontosVisitante.setText("0" + pontosV);
            }
        }
    }

    @FXML
    void novoQuarto(MouseEvent event) {

    }

    @FXML
    void pausaCrono(MouseEvent event) throws IOException {
        if(jTBPausaCrono.isSelected() == true){
            Main.mandaMSG("#PAUSA_CRONOS");
        } else if (jTBPausaCrono.isSelected() == false){
            Main.mandaMSG("#CONTINUA_CRONOS");
        }
        
    }

    @FXML
    void reiniciaCrono(MouseEvent event) {

    }

    @FXML
    void reiniciaLancamento(MouseEvent event) {

    }

    @FXML
    void restauraTudo(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public String mandaMensagem(String msg) throws IOException {

        Socket cliente = new Socket("localhost", 12345);

        ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
        ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());

        saida.writeUTF(msg);
        saida.flush();

        String retorno = entrada.readUTF();
        System.out.println(retorno);

        return retorno;
    }

    private Task iniciaCronosPreview(Label l, int min, int seg, int mili) {

        Task task = new Task<Void>() {
            int m = min;
            int s = seg;
            int ms = mili;
            String minutos;
            String segundos;
            String milisegundos;

            @Override
            public Void call() throws Exception {
                while (fimCrono()) {

                    if (m > 9) {
                        minutos = "" + m;
                    } else {
                        minutos = "0" + m;
                    }
                    if (s > 9) {
                        segundos = "" + s;
                    } else {
                        segundos = "0" + s;
                    }
                    if (ms > 9) {
                        milisegundos = "" + ms;
                    } else {
                        milisegundos = "0" + ms;
                    }
                    Platform.runLater(() -> {
                        l.setText(minutos + ":" + segundos + ":" + milisegundos);
                    });

                    if (m == 0) {
                        if (s == 0) {
                            if (ms == 0) {
                                fimcrono = false;
                                URL url = getClass().getResource("/estilos/apito.wav");
                                AudioClip audio = Applet.newAudioClip(url);
                                audio.play();
                            }
                        }
                    }

                    ms--;

                    if (ms < 0) {
                        ms = 99;
                        s--;
                    }
                    if (s < 0) {
                        s = 59;
                        m--;
                    }
                    Thread.sleep(10);
                }
                return null;
            }
        };
        return task;
    }

    public boolean fimCrono() {
        return fimcrono;
    }
}
