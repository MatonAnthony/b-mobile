package dao;

import dal.DalBackendServices;

public class UserDaoImpl implements UserDao {

  private DalBackendServices dalBackendServices;

  public UserDaoImpl(DalBackendServices dalBackendServices) {
    this.dalBackendServices = dalBackendServices;
  }

  @Override
  public boolean createUser() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void read() {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean update() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean delete() {
    // TODO Auto-generated method stub
    return false;
  }

}
