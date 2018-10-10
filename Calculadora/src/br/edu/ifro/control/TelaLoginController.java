/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifro.control;

import br.edu.ifro.model.Login;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author 01595747222
 */
public class TelaLoginController implements Initializable {
    
    private Label label;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private TextField txtSenha2;
    @FXML
    private Button btnLogin;
    @FXML
    private CheckBox cbMostrar;
    
    @FXML
    public void salvar() throws IOException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("calculadora");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT l FROM Login as l WHERE l.usuario = :usuario");       
        query.setParameter("usuario", txtUsuario.getText());
        List<Login> l = query.getResultList();
        if (!l.isEmpty()) {
            //System.out.println(l[0]);
            Login login = l.get(0);
            String senha = txtSenha2.getText();
            String senha2 = txtSenha.getText();
            if (login.getSenha().equals(senha) || login.getSenha().equals(senha2)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmação de Login");
                alert.setHeaderText("Você realizou Login!");
                alert.setContentText("Login efetuado com sucesso!");
                alert.showAndWait();
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/br/edu/ifro/view/Calculadora.fxml"));
                Scene scene = new Scene(fxmlLoader.load(),319,442);
                Stage stage = new Stage();
                stage.setTitle("Calculadora");
                stage.setScene(scene);
                stage.show();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro de Login");
                alert.setHeaderText("Ooops, houve um erro!");
                alert.setContentText("Verifique sua senha!");

                alert.showAndWait();
                abrirTelaLogin();
            }
        } 
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro de Login");
                alert.setHeaderText("Ooops, houve um erro!");
                alert.setContentText("Verifique seu usuário!");

                alert.showAndWait();
                abrirTelaLogin();
        }
        
        em.close();
        emf.close();
        
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.close();
    }
    
    public void abrirTelaLogin() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/br/edu/ifro/view/TelaLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),458,355);
            
        Stage stage = new Stage();
        stage.setTitle("Tela Login");
        stage.setScene(scene);
        stage.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtSenha.setVisible(true);
        txtSenha2.setVisible(false);
    }    

    @FXML
    private void cadastroLogin(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/br/edu/ifro/view/CadastroLogin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),400,250);
            
            Stage stage = new Stage();
            stage.setTitle("Cadastrar Login");
            stage.setScene(scene);
            stage.show();
    }
    catch(IOException e){

    }
    }

    @FXML
    private void mostrarSenha(ActionEvent event) {
        if(cbMostrar.isSelected()){
            txtSenha2.setText(txtSenha.getText());
            txtSenha2.setVisible(true);
            txtSenha.setVisible(false);            
        }
        else{
            txtSenha.setText(txtSenha2.getText());
            txtSenha.setVisible(true);
            txtSenha2.setVisible(false);        
        }
    }

    @FXML
    private void onEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            salvar();
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.close();
        }
    }
}
