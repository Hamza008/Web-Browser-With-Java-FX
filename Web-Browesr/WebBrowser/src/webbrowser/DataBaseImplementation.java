
package webbrowser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DataBaseImplementation {
    
    String flag;

    public DataBaseImplementation(String flag) {
        this.flag = flag;
    }
    
    
    
    
    public List<Link> selectAll() {
        
        List<Link> historyList = new ArrayList<Link>();
	Connection connection = null;
	Statement statement = null;
        ResultSet resultSet = null;

        
        try {
                connection = ConnectionConfig.getConnection();
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM RAD."+flag);

                while (resultSet.next()) {
                       
                       
                       Link link = new Link();
                       link.setLink(resultSet.getString("LINK"));
                       historyList.add(link);
                }

        } catch (Exception e) {
                e.printStackTrace();
        } finally {
                if (resultSet != null) {
                        try {
                                resultSet.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
                if (statement != null) {
                        try {
                                statement.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
                if (connection != null) {
                        try {
                                connection.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
        }

        return historyList; //To change body of generated methods, choose Tools | Templates.
    }
     
      public void insert(Link link) {
        
        Connection connection = null;
	PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionConfig.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO "+flag+"(Link)" +
					"VALUES (?)");
                       // preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(1, link.getLink());
			//preparedStatement.setString(3, student.getLastName());
			preparedStatement.executeUpdate();
			System.out.println("");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	
    }
      
      
       public void delete() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
                connection = ConnectionConfig.getConnection();
                preparedStatement = connection.prepareStatement("DELETE FROM "+flag);
               // preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();

               // System.out.println("");

        } catch (Exception e) {
                e.printStackTrace();
        } finally {
                if (preparedStatement != null) {
                        try {
                                preparedStatement.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
                if (connection != null) {
                        try {
                                connection.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
        } //To change body of generated methods, choose Tools | Templates.
    }

     
    
    
    
}
