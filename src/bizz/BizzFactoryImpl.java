package bizz;

class BizzFactoryImpl implements BizzFactory {

  @Override

  /**
   * Renvoie un UserDto vide.
   * 
   * @return Un nouveau UserDto.
   */
  public UserDto getUserDto() {
    return new UserImpl();
  }

}
