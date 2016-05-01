package dao.interfaces;

import dto.PartnerDto;

import java.util.ArrayList;

public interface PartnerDao {

  /**
   * Add partner to DB.
   *
   * @param partner DTO partner
   */
  void createPartner(PartnerDto partner);

  /**
   * Get all partners created by one user.
   *
   * @param userId Id of user.
   * @param permission Type of permission (Teacher or Student).
   * @return An ArrayList with all partners.
   */
  ArrayList<PartnerDto> getPartnersMin(int userId, String permission);

  /**
   * Get a partner.
   *
   * @param id Id of partner.
   * @return A partnerDto.
   */
  PartnerDto getPartnerById(int id);

  /**
   * Get all the partners of the database.
   * 
   * @return An ArrayList of PartnerDto
   */
  ArrayList<PartnerDto> getAllPartners();

  /**
   * Update the partner details in the Database whith the informations in the dto.
   * 
   * @param partner the dto with the informations.
   * @return the number of rows modified
   */
  int updatePartner(PartnerDto partner);

  /**
   * Get all the partners of the database added by a teacher.
   * 
   * @return An arraylist of PartnerDto
   */
  ArrayList<PartnerDto> getTeacherPartners();
}
