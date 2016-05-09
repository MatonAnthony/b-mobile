package ucc;

import static org.junit.Assert.assertEquals;

import bizz.implementations.BizzFactoryImpl;
import bizz.interfaces.BizzFactory;
import dal.DalServices;
import dal.DalServicesImplStub;
import dao.PartnerDaoMock;
import dto.DepartmentDto;
import dto.PartnerDto;
import exceptions.MalformedIbanException;
import ucc.implementations.PartnerUcControllerImpl;
import ucc.interfaces.PartnerUcController;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class PartnerUcControllerImplTest {

  private PartnerDaoMock partnerDao;
  private ArrayList<PartnerDto> list;
  private ArrayList<DepartmentDto> listDepartments;
  private PartnerDto partnerDto;
  private PartnerDto partnerDto2;
  private DepartmentDto departmentDto;
  private PartnerUcController partnerUcController;

  @Before
  public void setUp() throws Exception {
    BizzFactory bizzFactory = new BizzFactoryImpl();
    partnerDto = bizzFactory.getPartnerDto();
    partnerDto.setId(1);;
    partnerDto.setIdUser(1);
    partnerDto.setVerNr(0);
    partnerDto2 = bizzFactory.getPartnerDto();
    partnerDto2.setId(2);
    partnerDto2.setIdUser(2);
    partnerDto2.setVerNr(0);
    list = new ArrayList<PartnerDto>();
    list.add(partnerDto);
    listDepartments = new ArrayList<DepartmentDto>();
    listDepartments.add(departmentDto);

    partnerDao = new PartnerDaoMock(list, listDepartments);
    DalServices dalServices = new DalServicesImplStub();
    partnerUcController = new PartnerUcControllerImpl(dalServices, partnerDao);
  }


  @Test
  public void testAddPartner() throws SQLException {
    partnerUcController.addPartner(partnerDto2, listDepartments);
  }

  @Test
  public void testGetPartnerMin() throws SQLException {
    assertEquals(list, partnerUcController.getPartnerMin(1, "STUDENT"));
  }

  @Test
  public void testGetPartnerById() throws SQLException, MalformedIbanException {
    assertEquals(partnerDto, partnerUcController.getPartnerById(1));
  }

  @Test
  public void testGetAllPartners() throws SQLException, MalformedIbanException {
    assertEquals(list, partnerUcController.getAllPartners());
  }

  @Test
  public void testGetTeacherPartners() throws SQLException, MalformedIbanException {
    assertEquals(list, partnerUcController.getTeacherPartners());
  }

  @Test
  public void testUpdatePartner() {
    partnerUcController.updatePartner(partnerDto2, listDepartments);
  }

  @Test
  public void testGetDeletedPartner() throws SQLException, MalformedIbanException {
    assertEquals(list, partnerUcController.getDeletedPartners());
  }

  @Test
  public void testChangeDeletion() throws SQLException {
    partnerUcController.changeDeletion(partnerDto);
  }

}
