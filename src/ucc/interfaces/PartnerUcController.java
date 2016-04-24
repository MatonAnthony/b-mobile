package ucc.interfaces;

import dto.PartnerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PartnerUcController {

  /**
   * Add new partner to DB for current user.
   *
   * @param partner patrnerDto.
   * @throws SQLException if there is a problem.
   */
  void addPartner(PartnerDto partner) throws SQLException;

  /**
   * Return ArrayList containing partners for of one user stored in database
   *
   * @param userId Id of user who want to see his partner encoded.
   * @return an ArrayList of partnerDto.
   * @throws SQLException if there is a problem.
   */
  ArrayList<PartnerDto> getPartnerMin(int userId) throws SQLException;


  PartnerDto getPartnerById(int id) throws SQLException;
}
