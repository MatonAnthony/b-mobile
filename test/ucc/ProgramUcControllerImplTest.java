package ucc;

import static org.junit.Assert.assertEquals;

import bizz.implementations.BizzFactoryImpl;
import bizz.interfaces.BizzFactory;
import dal.DalServices;
import dal.DalServicesImplStub;
import dao.ProgramDaoMock;
import dto.ProgramDto;
import exceptions.NoProgramException;
import ucc.implementations.ProgramUcControllerImpl;
import ucc.interfaces.ProgramUcController;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProgramUcControllerImplTest {

  private ProgramDaoMock programDao;
  private ArrayList<ProgramDto> list;
  private ProgramDto programDto;
  private ProgramDto programDto2;
  private ProgramUcController programUcController;

  @Before
  public void setUp() throws Exception {
    BizzFactory bizzFactory = new BizzFactoryImpl();
    programDto = bizzFactory.getProgramDto();
    programDto.setId(1);;
    programDto.setDescription("Europe");
    programDto.setName("Erasmus");
    programDto.setVerNr(0);
    programDto2 = bizzFactory.getProgramDto();
    programDto2.setId(2);
    programDto2.setDescription("Belgique");
    programDto2.setName("Erabel");
    programDto2.setVerNr(0);
    list = new ArrayList<ProgramDto>();
    list.add(programDto);
    list.add(programDto2);

    programDao = new ProgramDaoMock(list);
    DalServices dalServices = new DalServicesImplStub();
    programUcController = new ProgramUcControllerImpl(dalServices, programDao);
  }

  @Test
  public void testGetAllPrograms() throws SQLException {
    assertEquals(list, programUcController.getAllPrograms());
  }

  @Test
  public void testGetProgramByName() throws SQLException, NoProgramException {
    assertEquals(programDto2, programUcController.getProgramByName("Erabel"));
  }

  @Test(expected = NoProgramException.class)
  public void testGetProgramByName2() throws SQLException, NoProgramException {
    assertEquals(programDto2, programUcController.getProgramByName("Fame"));
  }

}
