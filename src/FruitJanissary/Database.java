package FruitJanissary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {


    public static ResultSet getConnection(String connectionString, int connectionType) throws Exception{
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/fruitjanissary?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
            String username = "admin1";
            String password = "123321";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);

            Statement myStat = (Statement) conn.createStatement();

            if(connectionType==1){                                                      //1 is write
                myStat.executeUpdate(connectionString);
            }else if(connectionType == 0){                                              //0 is read
                ResultSet myRs = myStat.executeQuery(connectionString);
                return myRs;
            }

        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

}
