package ucc;

import static org.junit.Assert.assertEquals;

import bizz.implementations.BizzFactoryImpl;
import bizz.interfaces.BizzFactory;
import dal.DalServices;
import dal.DalServicesImplStub;
import dao.CancelationDaoMock;
import dto.CancelationDto;
import ucc.implementations.CancelationUcControllerImpl;
import ucc.interfaces.CancelationUcController;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class CancelationUcControllerImplTest {

  private CancelationDaoMock cancelationDao;
  private ArrayList<CancelationDto> listCancel;
  private CancelationDto cancelationDto;
  private CancelationDto cancelationDto2;
  private CancelationUcController cancelationUcController;

  @Before
  public void setUp() throws Exception {
    BizzFactory bizzFactory = new BizzFactoryImpl();
    cancelationDto = bizzFactory.getCancelationDto();
    cancelationDto.setId(1);;
    cancelationDto.setReason("Poney");;
    cancelationDto.setResponsible("TEACHER");
    cancelationDto.setVerNr(0);
    cancelationDto2 = bizzFactory.getCancelationDto();
    cancelationDto2.setId(2);;
    cancelationDto2.setReason("Poney2");;
    cancelationDto2.setResponsible("TEACHER");
    cancelationDto2.setVerNr(0);
    listCancel = new ArrayList<CancelationDto>();
    listCancel.add(cancelationDto);

    cancelationDao = new CancelationDaoMock(listCancel);
    DalServices dalServices = new DalServicesImplStub();
    cancelationUcController = new CancelationUcControllerImpl(dalServices, cancelationDao);

  }

  @Test
  public void getAllReasonsOfTeacher() throws SQLException {
    assertEquals(listCancel, cancelationUcController.getAllReasonsOfTeacher());
  }

  @Test
  public void insertCancelation() throws SQLException {
    assertEquals(2, cancelationUcController.insertCancelation(cancelationDto2));
  }


}
