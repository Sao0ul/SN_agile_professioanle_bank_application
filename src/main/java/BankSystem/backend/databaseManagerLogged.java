package BankSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BankSystem.dataBaseManager;

public class databaseManagerLogged {

    public static Boolean isNameFound(String name) {
        String request1 = "SELECT * FROM accounts WHERE name=?";
        try (PreparedStatement ps1 = dataBaseManager.getConn().prepareStatement(request1)) {
            ps1.setString(1, name);
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            // Gère les erreurs SQL
            System.out.println("Erreur lors de la recherche du compte : " + e.getMessage());
        }
        return false;
    }

    public static void transferTo(String nameAccount1, int montant) {

        // Prépare la requête SQL avec des paramètres (évite l'injection SQL)
        String request1 = "SELECT * FROM accounts WHERE name=?";

        // Crée un PreparedStatement pour exécuter la requête
        try (PreparedStatement ps1 = dataBaseManager.getConn().prepareStatement(request1)) {

            // Définit le premier paramètre (nom du compte) la methode va remplacer le ?
            // numero 1 par la valeur de nameAccount
            ps1.setString(1, nameAccount1);

            // Exécute la requête et récupère les résultats
            ResultSet rs = ps1.executeQuery();

            // Vérifie s'il y a un résultat (compte trouvé)
            if (rs.next()) {
                // code pour deposer de l'argent sur le compte destinataire
                String request = "UPDATE accounts SET balance=balance +? WHERE name=?";

                try (PreparedStatement ps = dataBaseManager.getConn().prepareStatement(request)) {

                    ps.setInt(1, montant);
                    ps.setString(2, nameAccount1);

                    int rowsUpdated = ps.executeUpdate(); // met à jour la base, retourne le nombre de lignes modifiées
                    // ResultSet rs = ps.executeQuery(); // exécute la requête et récupère les
                    // résultats ici on veut les modifier putot
                    if (rowsUpdated == 1) {
                        // tout c'est bien passe
                    } else {
                        managerView.showErrorpuppup("many account found that's should be impossible");
                    }
                } catch (SQLException e) {
                    // Gère les erreurs SQL
                    managerView.showErrorpuppup("Erreur lors de la recherche du compte ou base de donne indisponible");
                }
            }
        } catch (SQLException e) {
            // Gère les erreurs SQL
            System.out.println("Erreur lors de la recherche du compte : " + e.getMessage());
        }
        return;
    }

}
