package dao;

import dao.interfaces.UserDao;
import dto.UserDto;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class UserDaoMock implements UserDao {

  private ArrayList<UserDto> db;

  public UserDaoMock(ArrayList<UserDto> db) {
    this.db = db;
  }

  @Override
  public UserDto getUserByUserName(String username) throws NoSuchElementException {
    for (int i = 0; i < db.size(); i++) {
      if (db.get(i).getPseudo().equals(username)) {
        return db.get(i);
      }
    }
    throw new NoSuchElementException();
  }

  @Override
  public void createUser(UserDto userdto) {
    db.add(userdto);
  }

  @Override
  public UserDto getUserById(int id) throws NoSuchElementException {
    for (int i = 0; i < db.size(); i++) {
      if (db.get(i).getId() == id) {
        return db.get(i);
      }
    }
    throw new NoSuchElementException();
  }

  @Override
  public ArrayList<UserDto> getAllUsers() {
    return db;
  }

  @Override
  public int changePermissionsForUserById(UserDto user) {
    return 1;
  }

  @Override
  public int updateUser(UserDto userEdited) {
    return 1;
  }

  @Override
  public boolean userExists(String username) {
    for (int i = 0; i < db.size(); i++) {
      if (db.get(i).getPseudo().equals(username)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int countUser() {
    return db.size();
  }

}
