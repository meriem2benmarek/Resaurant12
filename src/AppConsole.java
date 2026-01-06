import java.util.*;
import java.io.*;

public class AppConsole {

    private static ArrayList<Category> categories = new ArrayList<>();
    private static Order order = new Order();
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isAdmin = false;

    public static void main(String[] args) {
        login();
        setupCategories();
        showMenu();
    }

    // ================= LOGIN =================
    private static void login() {
        System.out.print("Username: ");
        String user = scanner.nextLine();
        System.out.print("Password: ");
        String pass = scanner.nextLine();

        if (user.equals("admin") && pass.equals("1234")) {
            isAdmin = true;
            System.out.println("\n[Admin connecté]\n");
        } else {
            System.out.println("\n[Mode client]\n");
        }
    }

    // ================= info =================
    private static void setupCategories() {
        Category marocain = new Category("PLATS MAROCAINS");
        marocain.addItem("Couscous Royal", 70);
        marocain.addItem("Tajine Poulet", 55);
        marocain.addItem("Tajine Viande", 70);
        marocain.addItem("Pastilla Poulet", 65);
        marocain.addItem("Harira", 15);
        marocain.addItem("Rfissa", 60);

        Category italien = new Category("PLATS ITALIENS");
        italien.addItem("Pizza Margherita", 40);
        italien.addItem("Pizza 4 Fromages", 55);
        italien.addItem("Pizza Pepperoni", 60);
        italien.addItem("Lasagnes Bolognaise", 65);
        italien.addItem("Spaghetti Carbonara", 50);
        italien.addItem("Risotto aux Champignons", 55);

        Category francais = new Category("PLATS FRANÇAIS");
        francais.addItem("Boeuf Bourguignon", 75);
        francais.addItem("Coq au Vin", 70);
        francais.addItem("Ratatouille", 45);
        francais.addItem("Quiche Lorraine", 50);
        francais.addItem("Gratin Dauphinois", 45);

        Category libanais = new Category("PLATS LIBANAIS");
        libanais.addItem("Houmous", 25);
        libanais.addItem("Falafel", 30);
        libanais.addItem("Chawarma Poulet", 45);
        libanais.addItem("Taboulé", 25);
        libanais.addItem("Kebab", 40);

        Category boissons = new Category("BOISSONS");
        boissons.addItem("Coca-Cola", 10);
        boissons.addItem("Fanta", 10);
        boissons.addItem("Eau Minérale", 5);
        boissons.addItem("Jus d’Orange", 15);
        boissons.addItem("Café", 12);
        boissons.addItem("Thé Marocain", 8);

        Category desserts = new Category("DESSERTS");
        desserts.addItem("Tiramisu", 30);
        desserts.addItem("Crème Brûlée", 28);
        desserts.addItem("Cheesecake", 32);
        desserts.addItem("Pâtisseries Marocaines", 25);
        desserts.addItem("Mousse au Chocolat", 27);

        categories.add(marocain);
        categories.add(italien);
        categories.add(francais);
        categories.add(libanais);
        categories.add(boissons);
        categories.add(desserts);
    }

    // ================= MENU =================
    private static void showMenu() {
        while (true) {
            System.out.println("\n===== MENU =====");
            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ". " + categories.get(i).getName());
            }
            if (isAdmin) System.out.println("9. Gestion du menu");
            System.out.println("0. Facture & Quitter");

            System.out.print("Choix: ");
            int ch = scanner.nextInt();
            scanner.nextLine();

            if (ch == 0) {
                showInvoice();
                return;
            } else if (ch == 9 && isAdmin) {
                gestionMenu();
            } else if (ch >= 1 && ch <= categories.size()) {
                showCategory(categories.get(ch - 1));
            } else {
                System.out.println("Choix invalide");
            }
        }
    }

    // ================= CATEGORY =================
    private static void showCategory(Category cat) {
        while (true) {
            System.out.println("\n--- " + cat.getName() + " ---");
            for (int i = 0; i < cat.getItems().size(); i++) {
                MenuItem m = cat.getItems().get(i);
                System.out.println((i + 1) + ". " + m.getName() + " - " + m.getPrice() + " DH");
            }
            System.out.println("0. Retour");

            System.out.print("Choix: ");
            int ch = scanner.nextInt();
            scanner.nextLine();

            if (ch == 0) return;

            if (ch >= 1 && ch <= cat.getItems().size()) {
                order.addItem(cat.getItems().get(ch - 1));
                System.out.println("Ajouté à la commande");
            } else {
                System.out.println("Choix invalide");
            }
        }
    }

    // ================= ADMIN =================
    private static void gestionMenu() {
        while (true) {
            System.out.println("\n--- GESTION DU MENU ---");
            System.out.println("1. Ajouter plat (fixe)");
            System.out.println("2. Ajouter plat dynamique");
            System.out.println("3. Supprimer plat");
            System.out.println("4. Modifier prix");
            System.out.println("5. Sauvegarder menu");
            System.out.println("6. Supprimer catégorie");
            System.out.println("7. Nettoyer catégorie");
            System.out.println("0. Retour");

            System.out.print("Choix: ");
            int ch = scanner.nextInt();
            scanner.nextLine();

            switch (ch) {
                case 0: return;
                case 1: ajouterPlat(); break;
                case 2: ajouterPlatDynamique(); break;
                case 3: supprimerPlat(); break;
                case 4: modifierPrix(); break;
                case 5: saveMenu(); break;
                case 6: supprimerCategory(); break;
                case 7: nettoyerCategory(); break;
                default: System.out.println("Choix invalide !");
            }
        }
    }

    // ====== Méthodes Admin ======
    private static void ajouterPlat() {
        Category cat = categories.get(0);
        System.out.print("Nom du plat: ");
        String name = scanner.nextLine();
        System.out.print("Prix: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        cat.addItem(name, price);
        System.out.println("Plat ajouté");
    }

    private static void ajouterPlatDynamique() {
        System.out.println("\n--- Choisissez la catégorie ---");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
        System.out.print("Choix: ");
        int catIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (catIndex < 0 || catIndex >= categories.size()) {
            System.out.println("Choix invalide !");
            return;
        }

        Category cat = categories.get(catIndex);

        System.out.print("Nom du plat: ");
        String name = scanner.nextLine();
        System.out.print("Prix: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        cat.addItem(name, price);
        System.out.println("Plat '" + name + "' ajouté dans '" + cat.getName() + "' avec succès !");
    }

    private static void supprimerPlat() {
        Category cat = categories.get(0);

        if (cat.getItems().isEmpty()) {
            System.out.println("Aucun plat");
            return;
        }

        for (int i = 0; i < cat.getItems().size(); i++) {
            System.out.println((i + 1) + ". " + cat.getItems().get(i).getName());
        }

        System.out.print("Choix: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < cat.getItems().size()) {
            cat.getItems().remove(index);
            System.out.println("Plat supprimé");
        } else {
            System.out.println("Choix invalide");
        }
    }

    private static void modifierPrix() {
        Category cat = categories.get(0);

        for (int i = 0; i < cat.getItems().size(); i++) {
            MenuItem m = cat.getItems().get(i);
            System.out.println((i + 1) + ". " + m.getName() + " - " + m.getPrice());
        }

        System.out.print("Plat: ");
        int index = scanner.nextInt() - 1;
        System.out.print("Nouveau prix: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        if (index >= 0 && index < cat.getItems().size()) {
            cat.getItems().get(index).setPrice(price);
            System.out.println("Prix modifié");
        } else {
            System.out.println("Choix invalide");
        }
    }

    private static void saveMenu() {
        try (FileWriter fw = new FileWriter("menu.txt")) {
            for (Category c : categories) {
                fw.write(c.getName() + "\n");
                for (MenuItem m : c.getItems()) {
                    fw.write("- " + m.getName() + " : " + m.getPrice() + " DH\n");
                }
            }
            System.out.println("Menu sauvegardé");
        } catch (IOException e) {
            System.out.println("Erreur fichier");
        }
    }

    private static void supprimerCategory() {
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
        System.out.print("Choisissez la catégorie à supprimer: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < categories.size()) {
            Category cat = categories.remove(index);
            System.out.println("Catégorie '" + cat.getName() + "' supprimée !");
        } else {
            System.out.println("Choix invalide !");
        }
    }

    private static void nettoyerCategory() {
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
        System.out.print("Choisissez la catégorie à nettoyer: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < categories.size()) {
            Category cat = categories.get(index);
            cat.getItems().clear();
            System.out.println("Toutes les plats de '" + cat.getName() + "' ont été supprimés !");
        } else {
            System.out.println("Choix invalide !");
        }
    }

    // ================= FACTURE =================
    private static void showInvoice() {
        System.out.println("\n===== FACTURE =====");
        for (OrderItem oi : order.getItems()) {
            System.out.println(oi.getItem().getName() + " x" + oi.getQuantity() + " = " + oi.getTotal());
        }
        System.out.println("TOTAL = " + order.getTotal() + " DH");
    }
}


