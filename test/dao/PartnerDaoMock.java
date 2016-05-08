package dao;

import dao.interfaces.PartnerDao;
import dto.PartnerDto;
import exceptions.MalformedIbanException;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PartnerDaoMock implements PartnerDao {

  private ArrayList<PartnerDto> db;

  public PartnerDaoMock(ArrayList<PartnerDto> db) {
    this.db = db;
  }

  @Override
  public void createPartner(PartnerDto partner) {
    db.add(partner);
  }

  @Override
  public ArrayList<PartnerDto> getPartnersMin(int userId, String permission) {
    return db;
  }

  @Override
  public PartnerDto getPartnerById(int id) {
    for (int i = 0; i < db.size(); i++) {
      if (db.get(i).getId() == id) {
        return db.get(i);
      }
    }
    throw new NoSuchElementException();
  }

  @Override
  public ArrayList<PartnerDto> getAllPartners() {
    return db;
  }

  @Override
  public ArrayList<PartnerDto> getTeacherPartners() {
    return db;
  }

  @Override
  public ArrayList<PartnerDto> getPartnersWithoutMobility() {
    return db;
  }

  @Override
  public int updatePartner(PartnerDto partner) {
    return 1;
  }

  @Override
  public ArrayList<PartnerDto> getDeletedPartners() throws MalformedIbanException {
    return db;
  }

  @Override
  public int setDeleted(PartnerDto dto) {
    return 1;
  }

  @Override
  public ArrayList<PartnerDto> getPartnersForStudentList(int userId) {
    return db;
  }



}
