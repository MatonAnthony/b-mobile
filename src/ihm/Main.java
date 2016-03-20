package ihm;

import bizz.BizzFactory;
import bizz.BizzFactoryImpl;
import dal.DalBackendServices;
import dal.DalServices;
import dal.DalServicesImpl;
import dao.CountryDao;
import dao.CountryDaoImpl;
import dao.DepartmentDao;
import dao.DepartmentDaoImpl;
import dao.MobilityDao;
import dao.MobilityDaoImpl;
import dao.ProgramDao;
import dao.ProgramDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import ucc.CountryUcController;
import ucc.CountryUcControllerImpl;
import ucc.DepartmentUcController;
import ucc.DepartmentUcControllerImpl;
import ucc.MobilityUcController;
import ucc.MobilityUcControllerImpl;
import ucc.ProgramUcController;
import ucc.ProgramUcControllerImpl;
import ucc.UserUcController;
import ucc.UserUcControllerImpl;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {

  /**
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
    MobilityDao mobilityDao = new MobilityDaoImpl((DalBackendServices) dalServices, bizzFactory);
    MobilityUcController mobilityUcc = new MobilityUcControllerImpl(dalServices, mobilityDao);
    CountryDao countryDao = new CountryDaoImpl((DalBackendServices) dalServices, bizzFactory);
    CountryUcController countryUcc = new CountryUcControllerImpl(dalServices, countryDao);
    DepartmentDao departmentDao =
        new DepartmentDaoImpl((DalBackendServices) dalServices, bizzFactory);
    DepartmentUcController departmentUcc =
        new DepartmentUcControllerImpl(dalServices, departmentDao);
    ProgramDao programDao = new ProgramDaoImpl((DalBackendServices) dalServices, bizzFactory);
    ProgramUcController programUcController = new ProgramUcControllerImpl(dalServices, programDao);
    Servlet servlet = new Servlet(userUcc, bizzFactory, mobilityUcc, countryUcc, departmentUcc,
        programUcController);

    // Gestion des servlets
    context.addServlet(new ServletHolder(servlet), "/home");
    context.addServlet(new ServletHolder(new DefaultServlet()), "/");

    // Parametres du serveur
    context.setWelcomeFiles(new String[] {"index.html"});
    context.setResourceBase("www/assets/");
    context.setInitParameter("cacheControl", "no-store,nocache,must-revalidate");
    context.setInitParameter("redirectWelcome", "true");
    context.setClassLoader(Thread.currentThread().getContextClassLoader());
    context.setMaxFormContentSize(50000000);
    Server server = new Server(8888);
    server.setHandler(context);

    server.start();

  }

}
