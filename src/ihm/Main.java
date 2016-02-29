package ihm;

import java.lang.reflect.Constructor;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import bizz.BizzFactory;
import bizz.UserUcController;
import persistance.DalServices;
import persistance.UserDao;

public class Main {

  public static void main(String[] args) throws Exception {

    // instancie un WebAppContext pour configurer le server
    WebAppContext context = new WebAppContext();
    // ce context se charge des URLs commençant par /
    context.setContextPath("/");

    // Injection de dépendance pour les différents UC Controller.

    DalServices dalServices = null;
    Constructor constr = Class.forName("persistance.DalServicesImpl").getDeclaredConstructor();
    constr.setAccessible(true);
    dalServices = (DalServices) constr.newInstance();

    UserDao userDao = null;
    constr = Class.forName("persistance.UserDaoImpl").getDeclaredConstructor();
    constr.setAccessible(true);
    userDao = (UserDao) constr.newInstance();

    UserUcController userUcc = null;
    constr = Class.forName("bizz.UserUcControllerImpl").getDeclaredConstructor(DalServices.class,
        UserDao.class);
    constr.setAccessible(true);
    userUcc = (UserUcController) constr.newInstance(dalServices, userDao);


    // Injection de dépendance pour les différentes factories.
    BizzFactory bizzFactory = null;
    constr = Class.forName("bizz.BizzFactoryImpl").getDeclaredConstructor();
    constr.setAccessible(true);
    bizzFactory = (BizzFactory) constr.newInstance();

    Servlet servlet = new Servlet(userUcc, bizzFactory);
    // Servlet répondra aux requêtes commençant par /home/
    context.addServlet(new ServletHolder(servlet), "/home");

    // Le DefaultServlet sert des fichiers (html, js, css, images, ...). Il est en général ajouté
    // en dernier pour que les autres servlets soient prioritaires sur l'interprétation des URLs.
    context.addServlet(new ServletHolder(new DefaultServlet()), "/");

    context.setWelcomeFiles(new String[] {"index.html"});
    // Quel est le fichier à servir si l’utilisateur va à l’URL racine sans
    // plus de précision.
    context.setResourceBase("src/ihm/www");
    // Où se trouvent les fichiers
    context.setInitParameter("cacheControl", "no-store,nocache,must-revalidate");
    // Dans le protocole HTTP, le serveur dicte le comportement du cache du
    // navigateur. Ici on dit que par défaut, il ne faut pas stocker ni
    // retenir en cache les réponses aux requêtes.

    context.setInitParameter("redirectWelcome", "true");
    // Quand c’est ‘true’, la page de bienvenue est affichée par redirection plutôt que par suivi.
    // En d’autres termes, l’URL change à la page de bienvenue.
    // Dans tous les cas le contenu de cette page sera affichée.
    context.setClassLoader(Thread.currentThread().getContextClassLoader());
    // Pour des raisons de sécurité, il faut préciser sous quelle autorité doit s’effectuer le
    // chargement des classes Java. Ici on utilise simplement l’autorité du Thread en cours.
    context.setMaxFormContentSize(50000000);
    // Spécifie la taille limite des données qu’un frontend peut soumettre à ce back-end.

    // lie le server à un port
    Server server = new Server(8080);
    // ce server utilise ce context
    server.setHandler(context);
    // allons-y
    server.start();

  }

}
