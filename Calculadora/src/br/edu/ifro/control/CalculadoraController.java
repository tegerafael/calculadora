/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifro.control;

import br.edu.ifro.model.Historico;
import br.edu.ifro.model.Historico;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author 01595747222
 */
public class CalculadoraController implements Initializable {

    @FXML
    private Button btnUm;
    @FXML
    private Button btnDois;
    @FXML
    private Button btnTres;
    @FXML
    private Button btnQuatro;
    @FXML
    private Button btnCinco;
    @FXML
    private Button btnSeis;
    @FXML
    private Button btnNove;
    @FXML
    private Button btnOito;
    @FXML
    private Button btnSete;
    @FXML
    private Button btnZero;
    @FXML
    private Button btnResultado;
    @FXML
    private Button btnMultiplicar;
    @FXML
    private Button btnDividir;
    @FXML
    private Button btnLimpar;
    @FXML
    private Button btnMais;
    @FXML
    private Button btnMenos;
    @FXML
    private TextField txtDisplay;
    @FXML
    private Button btnPonto;

    private double operando1, operando2, resultado;
    private String operador;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void resultado(ActionEvent event) {
        if(! txtDisplay.getText().equals(""))
                operando2 = Double.parseDouble(txtDisplay.getText());
                switch(operador){
                    case "+" : resultado = operando1 + operando2;
                    break;
                    case "-" : resultado = operando1 - operando2;
                    break;
                    case "*" : resultado = operando1 * operando2;
                    break;
                    case "/" : resultado = operando1 / operando2;
                    break;
                }
                txtDisplay.setText("" + resultado);
                
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("calculadora");
                EntityManager em = emf.createEntityManager();
                Historico histo1;
                histo1 = new Historico();

                histo1.setPrimeiroValor(operando1);
                histo1.setSegundoValor(operando2);
                histo1.setOperador(operador);
                histo1.setResultado(resultado);
                
                em.getTransaction().begin();
                em.persist(histo1);
                em.getTransaction().commit();
    }

    @FXML
    private void limpar(ActionEvent event) {
        txtDisplay.setText("");
    }

    @FXML
    private void um(ActionEvent event) {
        txtDisplay.setText(txtDisplay.getText() + "1");
    }

    @FXML
    private void dois(ActionEvent event) {
        txtDisplay.setText(txtDisplay.getText() + "2");
    }

    @FXML
    private void tres(ActionEvent event) {
        txtDisplay.setText(txtDisplay.getText() + "3");
    }

    @FXML
    private void quarto(ActionEvent event) {
        txtDisplay.setText(txtDisplay.getText() + "4");
    }

    @FXML
    private void cinco(ActionEvent event) {
        txtDisplay.setText(txtDisplay.getText() + "5");
    }

    @FXML
    private void seis(ActionEvent event) {
        txtDisplay.setText(txtDisplay.getText() + "6");
    }

    @FXML
    private void nove(ActionEvent event) {
        txtDisplay.setText(txtDisplay.getText() + "9");
    }

    @FXML
    private void oito(ActionEvent event) {
        txtDisplay.setText(txtDisplay.getText() + "8");
    }

    @FXML
    private void sete(ActionEvent event) {
        txtDisplay.setText(txtDisplay.getText() + "7");
    }

    @FXML
    private void zero(ActionEvent event) {
        txtDisplay.setText(txtDisplay.getText() + "0");
    }

    @FXML
    private void ponto(ActionEvent event) {
        txtDisplay.setText(txtDisplay.getText() + ".");
    }

    @FXML
    private void mais(ActionEvent event) {
        if(!txtDisplay.getText().equals("")){
            operando1 = Double.parseDouble(txtDisplay.getText());
            operador = "+";
            txtDisplay.setText("");
        }
    }

    @FXML
    private void menos(ActionEvent event) {
        if(!txtDisplay.getText().equals("")){
            operando1 = Double.parseDouble(txtDisplay.getText());
            operador = "-";
            txtDisplay.setText("");
        }
    }

    @FXML
    private void multiplicar(ActionEvent event) {
        if(!txtDisplay.getText().equals("")){
            operando1 = Double.parseDouble(txtDisplay.getText());
            operador = "*";
            txtDisplay.setText("");
        }
    }
    
    @FXML
    private void dividir(ActionEvent event) {
        if(!txtDisplay.getText().equals("")){
            operando1 = Double.parseDouble(txtDisplay.getText());
            operador = "/";
            txtDisplay.setText("");
        }
    }

    @FXML
    private void lista(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/br/edu/ifro/view/ListagemCalculadora.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),600,400);
            
        Stage stage = new Stage();
        stage.setTitle("Tela Login");
        stage.setScene(scene);
        stage.show();
    }
    
}
