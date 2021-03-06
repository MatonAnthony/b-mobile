package bizz.implementations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import bizz.enumeration.MobilityState;
import bizz.interfaces.BizzFactory;
import dto.CancelationDto;
import dto.CountryDto;
import dto.DepartmentDto;
import dto.MobilityDto;
import dto.PartnerDto;
import dto.ProgramDto;
import dto.UserDto;

import org.junit.Before;
import org.junit.Test;


/**
 * Created by anthony on 17/04/16.
 */
public class MobilityImplTest {

  private MobilityImpl puppet;
  private BizzFactory bizz;

  private CountryDto countryDto;
  private CancelationDto cancelationDto;
  private DepartmentDto departmentDto;
  private PartnerDto partnerDto;
  private UserDto userDto;
  private ProgramDto programDto;

  @Before
  public void setUp() throws Exception {
    puppet = new MobilityImpl();
    puppet.setId(0);
    puppet.setIdStudent(0);
    puppet.setIdProgram(0);
    puppet.setIdPartner(0);
    puppet.setType("Erabel");
    puppet.setPreferenceOrder(0);
    puppet.setIsoCountry("BE");
    puppet.setIdDepartment("BIN");
    puppet.setQuadrimester(1);
    puppet.setStatus(MobilityState.PENDING.name());
    puppet.setCanceled(false);
    puppet.setDepartureGrantContract(false);
    puppet.setDepartureConventionInternshipSchoolarship(false);
    puppet.setDepartureStudentConvention(false);
    puppet.setDepartureErasmusLanguageTest(false);
    puppet.setDepartureDocAggreement(false);
    puppet.setSoftwareProeco(false);
    puppet.setSoftwareMobilityTools(false);
    puppet.setSoftwareMobi(false);
    puppet.setReturnResidenceCert(false);
    puppet.setReturnTranscript(false);
    puppet.setReturnInternshipCert(false);
    puppet.setReturnFinalReport(false);
    puppet.setReturnErasmusLanguageTest(false);
    puppet.setReturnDocSentHighschool(false);
    puppet.setCancelationReason(0);
    puppet.setAmount(100);
    puppet.setPaymentDate1(false);
    puppet.setPaymentDate2(false);
    puppet.setAcademicYear("2016-2017");
    puppet.setVerNr(0);

    bizz = new BizzFactoryImpl();

    countryDto = bizz.getCountryDto();
    departmentDto = bizz.getDepartmentDto();
    cancelationDto = bizz.getCancelationDto();
    programDto = bizz.getProgramDto();
    partnerDto = bizz.getPartnerDto();
    userDto = bizz.getUserDto();

    puppet.setCountryDto(countryDto);
    puppet.setDepartmentDto(departmentDto);
    puppet.setCancelationDto(cancelationDto);
    puppet.setProgramDto(programDto);
    puppet.setPartnerDto(partnerDto);
    puppet.setStudentDto(userDto);

  }

  @Test
  public void testGetId() throws Exception {
    assertEquals(puppet.getId(), 0);
  }

  @Test
  public void testSetId() throws Exception {
    puppet.setId(1);
    assertEquals(puppet.getId(), 1);
  }

  @Test
  public void testGetStudentDto() throws Exception {
    assertEquals(userDto, puppet.getStudentDto());
  }

  @Test
  public void testSetStudentDto() throws Exception {
    UserDto userDto2 = bizz.getUserDto();
    puppet.setStudentDto(userDto2);
  }

  @Test
  public void testGetProgramDto() throws Exception {
    assertEquals(programDto, puppet.getProgramDto());
  }

  @Test
  public void testSetProgramDto() throws Exception {
    ProgramDto programDto2 = bizz.getProgramDto();
    puppet.setProgramDto(programDto2);
  }

  @Test
  public void testGetPartnerDto() throws Exception {
    assertEquals(partnerDto, puppet.getPartnerDto());
  }

  @Test
  public void testSetPartnerDto() throws Exception {
    PartnerDto partnerDto2 = bizz.getPartnerDto();
    puppet.setPartnerDto(partnerDto2);
  }

  @Test
  public void testGetDepartmentDto() throws Exception {
    assertEquals(departmentDto, puppet.getDepartmentDto());
  }

  @Test
  public void testSetDepartmentDto() throws Exception {
    DepartmentDto departmentDto2 = bizz.getDepartmentDto();
    puppet.setDepartmentDto(departmentDto2);
  }

  @Test
  public void testGetCountryDto() throws Exception {
    assertEquals(countryDto, puppet.getCountryDto());
  }

  @Test
  public void testGetCancelationDto() throws Exception {
    assertEquals(cancelationDto, puppet.getCancelationDto());
  }

  @Test
  public void testSetCancelationDto() throws Exception {
    CancelationDto cancelationDto2 = bizz.getCancelationDto();
    puppet.setCancelationDto(cancelationDto2);
  }

  @Test
  public void testSetCountryDto() throws Exception {
    CountryDto countryDto = bizz.getCountryDto();
    puppet.setCountryDto(countryDto);
  }

  @Test
  public void testGetIdStudent() throws Exception {
    assertEquals(puppet.getIdStudent(), 0);
  }

  @Test
  public void testSetIdStudent() throws Exception {
    puppet.setIdStudent(1);
    assertEquals(puppet.getIdStudent(), 1);
  }

  @Test
  public void testGetIdProgram() throws Exception {
    assertEquals(puppet.getIdProgram(), 0);
  }

  @Test
  public void testSetIdProgram() throws Exception {
    puppet.setIdProgram(1);
    assertEquals(puppet.getIdProgram(), 1);
  }

  @Test
  public void testGetIdPartner() throws Exception {
    assertEquals(puppet.getIdPartner(), 0);
  }

  @Test
  public void testSetIdPartner() throws Exception {
    puppet.setIdPartner(1);
    assertEquals(puppet.getIdPartner(), 1);
  }

  @Test
  public void testGetPreferenceOrder() throws Exception {
    assertEquals(puppet.getPreferenceOrder(), 0);
  }

  @Test
  public void testSetPreferenceOrder() throws Exception {
    puppet.setPreferenceOrder(1);
    assertEquals(puppet.getPreferenceOrder(), 1);
  }

  @Test
  public void testGetIdDepartment() throws Exception {
    assertEquals(puppet.getIdDepartment(), "BIN");
  }

  @Test
  public void testSetIdDepartment() throws Exception {
    puppet.setIdDepartment("BIM");
    assertEquals(puppet.getIdDepartment(), "BIM");
  }

  @Test
  public void testGetQuadrimester() throws Exception {
    assertEquals(puppet.getQuadrimester(), 1);
  }

  @Test
  public void testSetQuadrimester() throws Exception {
    puppet.setQuadrimester(2);
    assertEquals(puppet.getQuadrimester(), 2);
  }

  @Test
  public void testGetVerNr() throws Exception {
    assertEquals(puppet.getVerNr(), 0);
  }

  @Test
  public void testSetVerNr() throws Exception {
    puppet.setVerNr(1);
    assertEquals(puppet.getVerNr(), 1);
  }

  @Test
  public void testGetCancelationReason() throws Exception {
    assertEquals(puppet.getCancelationReason(), 0);
  }

  @Test
  public void testSetCancelationReason() throws Exception {
    puppet.setCancelationReason(1);
    assertEquals(puppet.getCancelationReason(), 1);
  }

  @Test
  public void testGetType() throws Exception {
    assertEquals(puppet.getType(), "Erabel");
  }

  @Test
  public void testSetType() throws Exception {
    puppet.setType("Erasmus");
    assertEquals(puppet.getType(), "Erasmus");
  }

  @Test
  public void testGetIsoCountry() throws Exception {
    assertEquals(puppet.getIsoCountry(), "BE");
  }

  @Test
  public void testSetIsoCountry() throws Exception {
    puppet.setIsoCountry("FR");
    assertEquals(puppet.getIsoCountry(), "FR");
  }

  @Test
  public void testGetStatus() throws Exception {
    assertEquals(puppet.getStatus(), MobilityState.PENDING.name());
  }

  @Test
  public void testSetStatus() throws Exception {
    puppet.setStatus(MobilityState.SECOND_PAYMENT_REQUESTED.name());
    assertEquals(puppet.getStatus(), MobilityState.SECOND_PAYMENT_REQUESTED.name());
  }

  @Test
  public void testGetAcademicYear() throws Exception {
    assertEquals(puppet.getAcademicYear(), "2016-2017");
  }

  @Test
  public void testSetAcademicYear() throws Exception {
    puppet.setAcademicYear("2017-2018");
    assertEquals(puppet.getAcademicYear(), "2017-2018");
  }

  @Test
  public void testIsCanceled() throws Exception {
    assertEquals(puppet.isCanceled(), false);
  }

  @Test
  public void testSetCanceled() throws Exception {
    puppet.setCanceled(true);
    assertEquals(puppet.isCanceled(), true);
  }

  @Test
  public void testIsDepartureGrantContract() throws Exception {
    assertEquals(puppet.isDepartureGrantContract(), false);

  }

  @Test
  public void testSetDepartureGrantContract() throws Exception {
    puppet.setDepartureGrantContract(true);
    assertEquals(puppet.isDepartureGrantContract(), true);
  }

  @Test
  public void testIsDepartureConventionInternshipSchoolarship() throws Exception {
    assertEquals(puppet.isDepartureConventionInternshipSchoolarship(), false);
  }

  @Test
  public void testSetDepartureConventionInternshipSchoolarship() throws Exception {
    puppet.setDepartureConventionInternshipSchoolarship(true);
    assertEquals(puppet.isDepartureConventionInternshipSchoolarship(), true);
  }

  @Test
  public void testIsDepartureStudentConvention() throws Exception {
    assertEquals(puppet.isDepartureStudentConvention(), false);
  }

  @Test
  public void testSetDepartureStudentConvention() throws Exception {
    puppet.setDepartureStudentConvention(true);
    assertEquals(puppet.isDepartureStudentConvention(), true);
  }

  @Test
  public void testIsDepartureErasmusLanguageTest() throws Exception {
    assertEquals(puppet.isDepartureErasmusLanguageTest(), false);
  }

  @Test
  public void testSetDepartureErasmusLanguageTest() throws Exception {
    puppet.setDepartureErasmusLanguageTest(true);
    assertEquals(puppet.isDepartureErasmusLanguageTest(), true);
  }

  @Test
  public void testIsDepartureDocAggreement() throws Exception {
    assertEquals(puppet.isDepartureDocAggreement(), false);
  }

  @Test
  public void testSetDepartureDocAggreement() throws Exception {
    puppet.setDepartureDocAggreement(true);
    assertEquals(puppet.isDepartureDocAggreement(), true);
  }

  @Test
  public void testIsDepartDocSentHighschool() throws Exception {
    assertEquals(puppet.isDepartDocSentHighschool(), false);
  }

  @Test
  public void testSetDepartDocSentHighschool() throws Exception {
    puppet.setDepartDocSentHighschool(true);
    assertEquals(puppet.isDepartDocSentHighschool(), true);
  }

  @Test
  public void testIsSoftwareProeco() throws Exception {
    assertEquals(puppet.isSoftwareProeco(), false);
  }

  @Test
  public void testSetSoftwareProeco() throws Exception {
    puppet.setSoftwareProeco(true);
    assertTrue(puppet.isSoftwareProeco());
  }

  @Test
  public void testIsSoftwareMobilityTools() throws Exception {
    assertFalse(puppet.isSoftwareMobilityTools());
  }

  @Test
  public void testSetSoftwareMobilityTools() throws Exception {
    puppet.setSoftwareMobilityTools(true);
    assertTrue(puppet.isSoftwareMobilityTools());
  }

  @Test
  public void testIsSoftwareMobi() throws Exception {
    assertFalse(puppet.isSoftwareMobi());
  }

  @Test
  public void testSetSoftwareMobi() throws Exception {
    puppet.setSoftwareMobi(true);
    assertTrue(puppet.isSoftwareMobi());
  }

  @Test
  public void testIsReturnResidenceCert() throws Exception {
    assertFalse(puppet.isReturnResidenceCert());
  }

  @Test
  public void testSetReturnResidenceCert() throws Exception {
    puppet.setReturnResidenceCert(true);
    assertTrue(puppet.isReturnResidenceCert());
  }

  @Test
  public void testIsReturnTranscript() throws Exception {
    assertFalse(puppet.isReturnTranscript());
  }

  @Test
  public void testSetReturnTranscript() throws Exception {
    puppet.setReturnTranscript(true);
    assertTrue(puppet.isReturnTranscript());
  }

  @Test
  public void testIsReturnInternshipCert() throws Exception {
    assertFalse(puppet.isReturnInternshipCert());
  }

  @Test
  public void testSetReturnInternshipCert() throws Exception {
    puppet.setReturnInternshipCert(true);
    assertTrue(puppet.isReturnInternshipCert());
  }

  @Test
  public void testIsReturnFinalReport() throws Exception {
    assertFalse(puppet.isReturnFinalReport());
  }

  @Test
  public void testSetReturnFinalReport() throws Exception {
    puppet.setReturnFinalReport(true);
    assertTrue(puppet.isReturnFinalReport());
  }

  @Test
  public void testIsReturnErasmusLanguageTest() throws Exception {
    assertFalse(puppet.isReturnErasmusLanguageTest());
  }

  @Test
  public void testSetReturnErasmusLanguageTest() throws Exception {
    puppet.setReturnErasmusLanguageTest(true);
    assertTrue(puppet.isReturnErasmusLanguageTest());
  }

  @Test
  public void testIsReturnDocSentHighschool() throws Exception {
    assertFalse(puppet.isReturnDocSentHighschool());
  }

  @Test
  public void testSetReturnDocSentHighschool() throws Exception {
    puppet.setReturnDocSentHighschool(true);
    assertTrue(puppet.isReturnDocSentHighschool());
  }

  @Test
  public void testClone() throws Exception {
    MobilityImpl clone = (MobilityImpl) puppet.clone();
    assertEquals(puppet, clone);
  }

  @Test
  public void testGetPaymentDate1() {
    assertFalse(puppet.getPaymentDate1());
  }

  @Test
  public void testGetPaymentDate2() {
    assertFalse(puppet.getPaymentDate2());
  }

  @Test
  public void testSetPaymentDate1() {
    puppet.setPaymentDate1(true);
    assertTrue(puppet.getPaymentDate1());
  }

  @Test
  public void testSetPaymentDate2() {
    puppet.setPaymentDate2(true);
    assertTrue(puppet.getPaymentDate2());
  }

  @Test
  public void testGetAmount() {
    assertEquals(100, puppet.getAmount(), 0);
  }

  @Test
  public void testSetAmount() {
    puppet.setAmount(200);
    assertEquals(200, puppet.getAmount(), 0);
  }

  @Test
  public void testEquals() throws CloneNotSupportedException {
    MobilityDto puppet2 = (MobilityDto) puppet.clone();
    assertTrue(puppet.equals(puppet2));
  }

  @Test
  public void testHashCode() throws CloneNotSupportedException {
    MobilityDto puppet2 = (MobilityDto) puppet.clone();
    assertEquals(puppet.hashCode(), puppet2.hashCode());
  }

  @Test
  public void testEquals2() throws CloneNotSupportedException {
    MobilityDto puppet2 = bizz.getMobilityDto();
    assertFalse(puppet.equals(puppet2));
  }

}
