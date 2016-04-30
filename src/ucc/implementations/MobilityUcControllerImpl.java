package ucc.implementations;

import dal.DalServices;
import dao.interfaces.MobilityDao;
import dto.MobilityDto;
import exceptions.BadMobilityStatusException;
import exceptions.NoMobilityException;
import exceptions.OptimisticLockException;
import ucc.interfaces.MobilityUcController;

import java.sql.SQLException;
import java.util.ArrayList;

public class MobilityUcControllerImpl implements MobilityUcController {

  private DalServices dalServices = null;
  private MobilityDao mobilityDao = null;

  /**
   * Create a new Use Case Controller for mobilities.
   *
   * @param dalServices The dalServices used by the Use case controller.
   * @param mobilityDao The dao used by the Use case controller.
   */
  public MobilityUcControllerImpl(DalServices dalServices, MobilityDao mobilityDao) {
    this.mobilityDao = mobilityDao;
    this.dalServices = dalServices;

  }

  @Override
  public ArrayList<MobilityDto> getAllMobilities() throws SQLException {
    dalServices.openConnection();
    ArrayList<MobilityDto> mobilities = mobilityDao.getAllMobilities();
    dalServices.closeConnection();
    return mobilities;
  }

  @Override
  public ArrayList<MobilityDto> getMobilities() throws SQLException {
    dalServices.openConnection();
    ArrayList<MobilityDto> mobilities = mobilityDao.getFullMobilities();
    dalServices.closeConnection();
    return mobilities;
  }

  @Override
  public ArrayList<MobilityDto> getConfirmedMobilities() throws SQLException {
    dalServices.openConnection();
    ArrayList<MobilityDto> mobilities = mobilityDao.getFullConfirmedMobilities();
    dalServices.closeConnection();
    return mobilities;
  }

  @Override
  public ArrayList<MobilityDto> getMyMobilities(String user) throws SQLException {
    dalServices.openConnection();
    ArrayList<MobilityDto> mobilities = mobilityDao.getFullMyMobilities(user);
    dalServices.closeConnection();
    return mobilities;
  }

  @Override
  public void addMobility(MobilityDto mobility) {

    // TODO (Martin) Throw les exceptions custom plutot que de la catch
    try {
      dalServices.openConnection();
      dalServices.startTransaction();
      mobilityDao.createMobility(mobility);

      dalServices.commitTransaction();
      dalServices.closeConnection();
    } catch (SQLException exc) {
      exc.printStackTrace();
      try {
        dalServices.rollbackTransaction();
        dalServices.closeConnection();
      } catch (SQLException exc1) {
        exc1.printStackTrace();
      }
    }

  }

  @Override
  public ArrayList<String> getAcademicYears() throws SQLException {
    dalServices.openConnection();
    ArrayList<String> academicYears = mobilityDao.getAllAcademicYears();
    dalServices.closeConnection();
    return academicYears;
  }

  @Override
  public ArrayList<MobilityDto> getFullPayments() throws SQLException {
    dalServices.openConnection();
    ArrayList<MobilityDto> payments = mobilityDao.getFullPayments();
    dalServices.closeConnection();
    return payments;
  }


  @Override
  public MobilityDto getMobilityById(int id) throws SQLException, NoMobilityException {
    dalServices.openConnection();
    MobilityDto mobility = mobilityDao.getMobilityById(id);
    dalServices.closeConnection();
    return mobility;
  }

  @Override
  public void cancelMobility(int idMobility, int idCancelation, int verNr) throws SQLException {
    dalServices.openConnection();
    dalServices.startTransaction();
    mobilityDao.cancelMobility(idMobility, idCancelation, verNr);
    dalServices.commitTransaction();
    dalServices.closeConnection();
  }

  @Override
  public void confirmPartner(MobilityDto mobilityDto) {
    try {
      dalServices.openConnection();
      dalServices.startTransaction();
      mobilityDao.confirmPartner(mobilityDto);
      dalServices.commitTransaction();
      dalServices.closeConnection();
    } catch (SQLException exc) {
      exc.printStackTrace();
      try {
        dalServices.rollbackTransaction();
        dalServices.closeConnection();
      } catch (SQLException exc1) {
        exc1.printStackTrace();
      }
    }


  }

  @Override
  public void updateMobilityDetails(MobilityDto mobility)
      throws BadMobilityStatusException, OptimisticLockException {

    if (mobility.getStatus().equals("En attente")) {
      throw new BadMobilityStatusException("Une mobilité en attente ne peut pas être modifiée");
    }
    if (mobility.getStatus().equals("Annulee")) {
      throw new BadMobilityStatusException("Une mobilité annulée ne peut pas être modifiée");
    }
    if (mobility.getAmount() < 0) {
      throw new NumberFormatException("Le montant ne peut pas être négatif");
    }

    try {
      dalServices.openConnection();
      dalServices.startTransaction();

      if ((mobility.isDepartDocSentHighschool()
          || mobility.isDepartureConventionInternshipSchoolarship()
          || mobility.isDepartureDocAggreement() || mobility.isDepartureErasmusLanguageTest()
          || mobility.isDepartureGrantContract() || mobility.isDepartureStudentConvention())
          && mobility.getStatus().equals("Créée")) {
        mobility.setStatus("En préparation");
      }

      if (mobility.isDepartDocSentHighschool()
          && mobility.isDepartureConventionInternshipSchoolarship()
          && mobility.isDepartureDocAggreement() && mobility.isDepartureErasmusLanguageTest()
          && mobility.isDepartureGrantContract() && mobility.isDepartureStudentConvention()
          && mobility.getStatus().equals("En préparation")) {
        mobility.setStatus("A payer");
      }

      if (mobility.getPaymentDate1() && (mobility.getStatus().equals("A payer")
          || mobility.getStatus().equals("En préparation"))) {
        mobility.setStatus("Paiement demandé");
      }

      if (mobility.isReturnDocSentHighschool() && mobility.isReturnErasmusLanguageTest()
          && mobility.isReturnFinalReport() && mobility.isReturnInternshipCert()
          && mobility.isReturnTranscript() && mobility.isDepartureConventionInternshipSchoolarship()
          && mobility.getStatus().equals("Paiement demandé")) {
        mobility.setStatus("A payer solde");
      }

      if (mobility.getPaymentDate2() && mobility.getStatus().equals("A payer solde")) {
        mobility.setStatus("Paiement du solde demandé");
      }

      int rowUpdated = mobilityDao.updateMobilityDetails(mobility);

      if (rowUpdated == 1) {
        dalServices.commitTransaction();
      } else {
        dalServices.rollbackTransaction();
        throw new OptimisticLockException(
            "Cette mobilité a été modifiée entre temps, veuillez rafraichir la page "
                + "et recommencer");
      }
    } catch (SQLException exc) {
      exc.printStackTrace();
      try {
        dalServices.rollbackTransaction();
      } catch (SQLException exc1) {
        exc1.printStackTrace();
      }
    } finally {
      try {
        dalServices.closeConnection();
      } catch (SQLException exc) {
        exc.printStackTrace();
      }
    }

  }

}
