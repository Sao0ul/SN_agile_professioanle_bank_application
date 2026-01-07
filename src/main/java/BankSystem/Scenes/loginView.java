package BankSystem;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;

// ==================== LOGIN VIEW ====================
public class loginView extends StackPane {

    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Hyperlink forgotPasswordLink;
    private Label titleLabel;
    private Label subtitleLabel;

    public loginView() {
        // Container principal avec un fond dégradé
        VBox mainContainer = new VBox(30);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(50));
        mainContainer.setMaxWidth(450);
        mainContainer.getStyleClass().add("login-container");

        // En-tête avec logo et titre
        VBox header = new VBox(10);
        header.setAlignment(Pos.CENTER);

        // Icône de banque (simulée avec un label stylisé)
        Label bankIcon = new Label("🏦");
        bankIcon.getStyleClass().add("bank-icon");

        titleLabel = new Label("BankApplication");
        titleLabel.getStyleClass().add("login-title");

        subtitleLabel = new Label("Welcome to your bank client");
        subtitleLabel.getStyleClass().add("login-subtitle");

        header.getChildren().addAll(bankIcon, titleLabel, subtitleLabel);

        // Formulaire de connexion
        VBox formContainer = new VBox(20);
        formContainer.setAlignment(Pos.CENTER);
        formContainer.setPadding(new Insets(30, 40, 30, 40));
        formContainer.getStyleClass().add("form-container");

        // Champ username
        VBox usernameBox = new VBox(8);
        Label usernameLabel = new Label("Identifiant");
        usernameLabel.getStyleClass().add("field-label");

        usernameField = new TextField();
        usernameField.setPromptText("Entrez votre identifiant");
        usernameField.getStyleClass().add("login-field");
        usernameField.setPrefHeight(45);

        usernameBox.getChildren().addAll(usernameLabel, usernameField);

        // Champ mot de passe
        VBox passwordBox = new VBox(8);
        Label passwordLabel = new Label("password");
        passwordLabel.getStyleClass().add("field-label");

        passwordField = new PasswordField();
        passwordField.setPromptText("Entrez votre mot de passe");
        passwordField.getStyleClass().add("login-field");
        passwordField.setPrefHeight(45);

        passwordBox.getChildren().addAll(passwordLabel, passwordField);

        // Lien mot de passe oublié
        HBox forgotPasswordBox = new HBox();
        forgotPasswordBox.setAlignment(Pos.CENTER_RIGHT);
        forgotPasswordLink = new Hyperlink("Mot de passe oublié ?");
        forgotPasswordLink.getStyleClass().add("forgot-password-link");
        forgotPasswordBox.getChildren().add(forgotPasswordLink);

        // Bouton de connexion
        loginButton = new Button("Se connecter");
        loginButton.getStyleClass().addAll("ViewClassButton", "login-button");
        loginButton.setPrefWidth(Double.MAX_VALUE);
        loginButton.setPrefHeight(50);

        // gestion des evenelemnts au click du bouton de login
        loginButton.addEventHandler(ActionEvent.ACTION, e -> {

            Account acc = dataBaseManager.findAccount(getUsername(), getPassword());

            if (dataBaseManager.isAccountPresent(acc)) {
                acc.setPassword(getPassword());
                dataBaseManager.setConnectedAccount(acc);
                managerView.showView(loginButton, new loggedView(acc));
            } else {
                managerView.showErrorpuppup("account not found try again");
            }
        });

        formContainer.getChildren().addAll(
                usernameBox,
                passwordBox,
                forgotPasswordBox,
                loginButton);

        // Footer avec informations de sécurité
        Label securityInfo = new Label("🔒 Connexion sécurisée SSL");
        securityInfo.getStyleClass().add("security-info");

        mainContainer.getChildren().addAll(header, formContainer, securityInfo);

        this.getChildren().add(mainContainer);
        this.getStyleClass().add("login-view");
    }

    // Getters pour accéder aux composants depuis le contrôleur
    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Hyperlink getForgotPasswordLink() {
        return forgotPasswordLink;
    }
}
