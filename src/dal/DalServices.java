package dal;

public interface DalServices {

  void startTransaction();

  void commitTransaction();

  void rollbackTransaction();


}
