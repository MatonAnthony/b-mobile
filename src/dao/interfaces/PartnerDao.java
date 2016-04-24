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
   * @return An ArrayList with all partners.
   */
  ArrayList<PartnerDto> getPartnersMin(int userId);

  /**
   * Get a partner.
   *
   * @param id Id of partner.
   * @return A partnerDto.
   */
  PartnerDto getPartnerById(int id);
}
