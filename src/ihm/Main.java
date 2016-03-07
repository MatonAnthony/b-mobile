package ihm;

import bizz.BizzFactory;
import bizz.BizzFactoryImpl;
import dal.DalBackendServices;
import dal.DalServices;
import dal.DalServicesImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import ucc.UserUcController;
import ucc.UserUcControllerImpl;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {

  /**
   * 
   * Le point d'entree de mon application.
   * 
   * @param args Les parametres de notre application.
   * @throws Exception Les exceptions renvoyees par l'application
   */
  public static void main(String[] args) throws Exception {

    WebAppContext context = new WebAppContext();
    context.setContextPath("/");

    // Injection des dépendances.
    DalServices dalServices = new DalServicesImpl();
    BizzFactory bizzFactory = new BizzFactoryImpl();
    UserDao userDao = new UserDaoImpl((DalBackendServices) dalServices, bizzFactory);
    UserUcController userUcc = new UserUcControllerImpl(dalServices, userDao);
    Servlet servlet = new Servlet(userUcc, bizzFactory);

    // Gestion des servlets
    context.addServlet(new ServletHolder(servlet), "/home");
    context.addServlet(new ServletHolder(new DefaultServlet()), "/");

    // Parametres du serveur
    context.setWelcomeFiles(new String[] {"index.html"});
    context.setResourceBase("www/assets");
    context.setInitParameter("cacheControl", "no-store,nocache,must-revalidate");
    context.setInitParameter("redirectWelcome", "true");
    context.setClassLoader(Thread.currentThread().getContextClassLoader());
    context.setMaxFormContentSize(50000000);
    Server server = new Server(8080);
    server.setHandler(context);

    server.start();

  }

}
