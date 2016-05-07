package ucc.interfaces;

import dto.PartnerDto;
import exceptions.MalformedIbanException;

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


  PartnerDto getPartnerById(int id) throws SQLException, MalformedIbanException;

  /**
   * Return an ArrayList containing all the partners.
   * 
   * @return an ArrayList of PartnerDto
   * @throws SQLException if an error occurred with the database.
   * @throws MalformedIbanException If the Iban is malformed.
   */
  ArrayList<PartnerDto> getAllPartners() throws SQLException, MalformedIbanException;

  /**
   * Return an ArrayList containing all the partners added by the teacher.
   * 
   * @return an ArrayList of PartnerDto
   * @throws SQLException if an error occurred with the database.
   * @throws MalformedIbanException If the Iban is malformed.
   */
  ArrayList<PartnerDto> getTeacherPartners() throws SQLException, MalformedIbanException;

  /**
   * Update the details for a partner.
   * 
   * @param partner the partnerDto with the informations.
   */
  void updatePartner(PartnerDto partner);

  /**
   * Return an ArrayList containing all partners who do not have mobility.
   * 
   * @return an ArraylList of PartnerDto.
   * @throws SQLException If an error occurred with the database.
   */
  ArrayList<PartnerDto> getPartnersWithoutMobility() throws SQLException;

  /**
   * Return an ArrayList containing all deleted partners
   * 
   * @return an ArrayList of PartnerDto.
   * @throws SQLException If an error occurred with the database.
   * @throws MalformedIbanException If the Iban is malformed.
   */
  ArrayList<PartnerDto> getDeletedPartners() throws SQLException, MalformedIbanException;

  /**
   * Change the partner to "deleted" or not.
   * 
   * @param partnerDto The partner to set.
   * @throws SQLException If an error occurred with the database.
   */
  void changeDeletion(PartnerDto partnerDto) throws SQLException;
}
