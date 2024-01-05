
package application;
import java.sql.*;
public class Conn {
    Connection c;
    Statement s;
        
    public Conn(){
        try{
       
       c= DriverManager.getConnection("jdbc:mysql://localhost/hotelmanagementsystem","root","");
       s=c.createStatement();
//       System.out.println(s);

    }catch(Exception e){
        System.out.println(e);
    }
    }
    
    public static void main(String args[]) {
        new Conn();
    }
}
