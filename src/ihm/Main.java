package ihm;

import bizz.implementations.BizzFactoryImpl;
import bizz.interfaces.BizzFactory;
import dal.DalBackendServices;
import dal.DalServices;
import dal.DalServicesImpl;
import dao.implementations.CancelationDaoImpl;
import dao.implementations.CountryDaoImpl;
import dao.implementations.DepartmentDaoImpl;
import dao.implementations.MobilityDaoImpl;
import dao.implementations.PartnerDaoImpl;
import dao.implementations.ProgramDaoImpl;
import dao.implementations.UserDaoImpl;
import dao.interfaces.CancelationDao;
import dao.interfaces.CountryDao;
import dao.interfaces.DepartmentDao;
import dao.interfaces.MobilityDao;
import dao.interfaces.PartnerDao;
import dao.interfaces.ProgramDao;
import dao.interfaces.UserDao;
import ucc.implementations.CancelationUcControllerImpl;
import ucc.implementations.CountryUcControllerImpl;
import ucc.implementations.DepartmentUcControllerImpl;
import ucc.implementations.MobilityUcControllerImpl;
import ucc.implementations.PartnerUcControllerImpl;
import ucc.implementations.ProgramUcControllerImpl;
import ucc.implementations.UserUcControllerImpl;
import ucc.interfaces.CancelationUcController;
import ucc.interfaces.CountryUcController;
import ucc.interfaces.DepartmentUcController;
import ucc.interfaces.MobilityUcController;
import ucc.interfaces.PartnerUcController;
import ucc.interfaces.ProgramUcController;
import ucc.interfaces.UserUcController;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import utils.ContextManager;

import java.io.IOException;
import java.util.logging.*;

public class Main {

  public static final Logger LOGGER = Logger.getLogger(Servlet.class.getName());

  static {
    LOGGER.setUseParentHandlers(false);
    LOGGER.setLevel(Level.ALL);
    MyLoggerFormatter formatter = new MyLoggerFormatter();
    ConsoleHandler handler = new ConsoleHandler();
    handler.setFormatter(formatter);
    LOGGER.addHandler(handler);
    try {
      Handler fileHandler = new FileHandler(ContextManager.env + ".log", true);
      fileHandler.setFormatter(formatter);
      LOGGER.addHandler(fileHandler);
    } catch (IOException exc) {
      LOGGER.warning("Impossible to log to file");
      exc.printStackTrace();
    }
  }

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

    PartnerDao partnerDao = new PartnerDaoImpl((DalBackendServices) dalServices, bizzFactory);
    PartnerUcController partnerUcController = new PartnerUcControllerImpl(dalServices, partnerDao);

    CancelationDao cancelationDao =
        new CancelationDaoImpl((DalBackendServices) dalServices, bizzFactory);
    CancelationUcController cancelationUcController =
        new CancelationUcControllerImpl(dalServices, cancelationDao);

    Servlet servlet =
        new Servlet(userUcc, bizzFactory, mobilityUcc, countryUcc, departmentUcc, programUcController, partnerUcController, cancelationUcController);

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
    LOGGER.info(server.getState());
  }

}
