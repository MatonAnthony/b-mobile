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
   * @param permission Type of permission (Teacher or Student).
   * @return an ArrayList of partnerDto.
   * @throws SQLException if there is a problem.
   */
  ArrayList<PartnerDto> getPartnerMin(int userId, String permission) throws SQLException;


  PartnerDto getPartnerById(int id) throws SQLException;

  /**
   * Return an ArrayList containing all the partners.
   * 
   * @return an ArrayList of PartnerDto
   * @throws SQLException if an error occurred with the database.
   */
  ArrayList<PartnerDto> getAllPartners() throws SQLException;

  /**
   * Update the details for a partner.
   * 
   * @param partner the partnerDto with the informations.
   */
  void updatePartner(PartnerDto partner);
}
