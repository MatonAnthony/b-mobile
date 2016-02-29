package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;

interface DalBackendServices {

  PreparedStatement prepare(String query) throws SQLException;

}
