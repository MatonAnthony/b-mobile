
package ucc;

import static org.junit.Assert.assertEquals;

import bizz.implementations.BizzFactoryImpl;
import bizz.interfaces.BizzFactory;
import dal.DalServices;
import dal.DalServicesImplStub;
import dao.DepartmentDaoMock;
import dao.interfaces.DepartmentDao;
import dto.DepartmentDto;
import exceptions.NoDepartmentException;
import ucc.implementations.DepartmentUcControllerImpl;
import ucc.interfaces.DepartmentUcController;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class DepartmentUcControllerImplTest {

  private ArrayList<DepartmentDto> list;
  private DepartmentDto departmentDto;
  private DepartmentUcController departmentUcc;

  /**
   * @throws java.lang.Exception If there is an error.
   */
  @Before
  public void setUp() throws Exception {
    BizzFactory bizzFactory = new BizzFactoryImpl();
    departmentDto = bizzFactory.getDepartmentDto();
    departmentDto.setId("BIN");
    departmentDto.setLabel("Informatique");
    departmentDto.setVerNr(0);
    list = new ArrayList<DepartmentDto>();
    list.add(departmentDto);

    DepartmentDao departmentDao = new DepartmentDaoMock(list);
    DalServices dalServices = new DalServicesImplStub();
    departmentUcc = new DepartmentUcControllerImpl(dalServices, departmentDao);
  }

  /**
   * Test method for
   * {@link ucc.implementations.DepartmentUcControllerImpl#DepartmentUcControllerImpl (dal.DalServices, dao.DepartmentDao)}
   * 
   */
  @Test
  public void testDepartmentUcControllerImpl() {

  }

  /**
   * Test method for {@link ucc.implementations.DepartmentUcControllerImpl#getAllDepartments()}.
   */
  @Test
  public void testGetAllDepartments() throws SQLException {
    assertEquals(list, departmentUcc.getAllDepartments());
  }

  @Test
  public void getDepartementById() throws NoDepartmentException, SQLException {
    assertEquals(departmentDto, departmentUcc.getDepartementsById("BIN"));
  }

  @Test(expected = NoDepartmentException.class)
  public void getDepartementById2() throws NoDepartmentException, SQLException {
    departmentUcc.getDepartementsById("BZA");
  }

  @Test
  public void getDepartmentByLabel() throws NoDepartmentException, SQLException {
    assertEquals(departmentDto, departmentUcc.getDepartmentByLabel("Informatique"));
  }

  @Test(expected = NoDepartmentException.class)
  public void getDepartmentByLabel2() throws NoDepartmentException, SQLException {
    assertEquals(departmentDto, departmentUcc.getDepartmentByLabel("Ramasseur de balle"));
  }

}
