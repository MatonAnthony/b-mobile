package dao;

import dao.interfaces.MobilityDao;
import dto.MobilityDto;
import exceptions.NoMobilityException;

import java.util.ArrayList;

public class MobilityDaoMock implements MobilityDao {

  private ArrayList<MobilityDto> db;

  public MobilityDaoMock(ArrayList<MobilityDto> list) {
    this.db = list;
  }

  @Override
  public void createMobility(MobilityDto mobilityDto) {}

  @Override
  public ArrayList<MobilityDto> getAllMobilities() {
    return db;
  }

  @Override
  public ArrayList<MobilityDto> getFullMobilities() {
    return db;
  }

  @Override
  public ArrayList<MobilityDto> getFullConfirmedMobilities() {
    return db;
  }

  @Override
  public ArrayList<MobilityDto> getFullMyMobilities(String user) {
    return db;
  }

  @Override
  public ArrayList<String> getAllAcademicYears() {
    ArrayList<String> list = new ArrayList<String>();
    list.add(db.get(0).getAcademicYear());
    return list;
  }

  @Override
  public ArrayList<MobilityDto> getFullPayments(String academicYear) {
    return db;
  }

  @Override
  public MobilityDto getMobilityById(int id) throws NoMobilityException {
    for (int i = 0; i < db.size(); i++) {
      if (db.get(i).getId() == id) {
        return db.get(i);
      }
    }
    throw new NoMobilityException();
  }

  @Override
  public void cancelMobility(int idMobility, int idCancelation, int verNr) {}

  @Override
  public void confirmPartner(MobilityDto mobilityDto) {}

  @Override
  public int updateMobilityDetails(MobilityDto mobility) {
    return 1;
  }

}
