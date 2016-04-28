package ucc;

import static org.junit.Assert.assertEquals;

import bizz.implementations.BizzFactoryImpl;
import bizz.interfaces.BizzFactory;
import dal.DalServices;
import dal.DalServicesImplStub;
import dao.MobilityDaoMock;
import dto.MobilityDto;
import exceptions.BadMobilityStatusException;
import exceptions.NoMobilityException;
import exceptions.OptimisticLockException;
import ucc.implementations.MobilityUcControllerImpl;
import ucc.interfaces.MobilityUcController;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class MobilityUcControllerTest {

  private MobilityDaoMock mobilityDao;
  private ArrayList<MobilityDto> list;
  private MobilityDto mobilityDto;
  private MobilityDto mobilityDto2;
  private MobilityUcController mobilityUcController;

  @Before
  public void setUp() throws Exception {
    BizzFactory bizzFactory = new BizzFactoryImpl();
    mobilityDto = bizzFactory.getMobilityDto();
    mobilityDto2 = bizzFactory.getMobilityDto();
    mobilityDto.setId(1);
    list = new ArrayList<MobilityDto>();
    list.add(mobilityDto);
    mobilityDao = new MobilityDaoMock(list);
    DalServices dalServices = new DalServicesImplStub();
    mobilityUcController = new MobilityUcControllerImpl(dalServices, mobilityDao);

  }

  @Test
  public void testGetAllMobilities() throws SQLException {
    assertEquals(list, mobilityUcController.getAllMobilities());
  }

  @Test
  public void testGetMobilities() throws SQLException {
    assertEquals(list, mobilityUcController.getMobilities());
  }

  @Test
  public void testGetConfirmedMobilities() throws SQLException {
    assertEquals(list, mobilityUcController.getConfirmedMobilities());
  }

  @Test
  public void testGetMyMobilities() throws SQLException {
    assertEquals(list, mobilityUcController.getMyMobilities("Toto"));
  }

  @Test
  public void testAddMobility() {
    mobilityUcController.addMobility(mobilityDto2);
  }

  @Test
  public void testGetAcademicYears() throws SQLException {
    ArrayList<String> listString = new ArrayList<String>();
    listString.add(list.get(0).getAcademicYear());
    assertEquals(listString, mobilityUcController.getAcademicYears());
  }

  @Test
  public void testGetFullPayments() throws SQLException {
    assertEquals(list, mobilityUcController.getFullPayments("2015-2016"));
  }

  @Test
  public void testGetMobilityById() throws SQLException, NoMobilityException {
    assertEquals(mobilityDto, mobilityUcController.getMobilityById(1));
  }

  @Test(expected = NoMobilityException.class)
  public void testGetMobilityById2() throws SQLException, NoMobilityException {
    assertEquals(mobilityDto, mobilityUcController.getMobilityById(2));
  }

  @Test
  public void testCancelMobility() throws SQLException {
    mobilityUcController.cancelMobility(1, 1, 0);
  }

  @Test
  public void testConfirmPartner() throws SQLException {
    mobilityUcController.confirmPartner(mobilityDto);
  }

  @Test
  public void testUpdateMobilityDetails()
      throws BadMobilityStatusException, OptimisticLockException {
    mobilityDto.setStatus("Créée");
    mobilityUcController.updateMobilityDetails(mobilityDto);
  }


  @Test(expected = BadMobilityStatusException.class)
  public void testUpdateMobilityDetails2()
      throws BadMobilityStatusException, OptimisticLockException {
    mobilityDto.setStatus("En attente");
    mobilityUcController.updateMobilityDetails(mobilityDto);
  }

  @Test(expected = BadMobilityStatusException.class)
  public void testUpdateMobilityDetails3()
      throws BadMobilityStatusException, OptimisticLockException {
    mobilityDto.setStatus("Annulee");
    mobilityUcController.updateMobilityDetails(mobilityDto);
  }

}
