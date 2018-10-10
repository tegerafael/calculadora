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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author 01595747222
 */
public class CadastroLoginController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private PasswordField txtSenha2;
    @FXML
    private Button btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void salvar() {
        if(txtUsuario.getText().equals("") || txtSenha.getText().equals("") || txtSenha2.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Está faltando alguma informação!");
        }
        
        else if(txtSenha.getText().equals(txtSenha2.getText())){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("calculadora");
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT l FROM Login as l WHERE l.usuario = :usuario");        
            //Query query = em.createQuery("SELECT l FROM Login as l WHERE l.usuario = :usuario and l.senha = :senha");
            query.setParameter("usuario", txtUsuario.getText());
            //query.setParameter("senha", txtSenha.getText());
            //Login login1 = (Login) query.getSingleResult();
            List<Login> l = query.getResultList();
            
            if (!l.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro de Cadastro");
                alert.setHeaderText("Ooops, houve um erro!");
                alert.setContentText("Nome de usuário já existente!");

                alert.showAndWait();
            } else {
                Login login1 = new Login();
                login1.setUsuario(txtUsuario.getText());
                login1.setSenha(txtSenha.getText());
                
                em.getTransaction().begin();                
                em.persist(login1);                
                em.getTransaction().commit();
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmação de Cadastro");
                alert.setHeaderText("Você realizou um Cadastro!");
                alert.setContentText("Usuário cadastrado com sucesso!");
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Cadastro");
            alert.setHeaderText("Ooops, houve um erro!");
            alert.setContentText("As senhas não conferem!");

            alert.showAndWait();
        }
    }

    @FXML
    private void limpar(ActionEvent event) {
        txtUsuario.setText("");
        txtSenha.setText("");
        txtSenha2.setText("");
        txtUsuario.requestFocus();
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onEnter(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER){
            salvar();
    }
}
    
}