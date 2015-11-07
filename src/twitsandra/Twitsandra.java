/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitsandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 *
 * @author adwisatya
 */
public class Twitsandra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Cluster cluster;
        Session session;
        
        cluster = Cluster.builder().addContactPoint("localhost").build();
        session = cluster.connect("mykeyspace");
        
        // Taruh insert record di sini
        session.execute("INSERT INTO users (username, password) VALUES ('aryya','pwdaryya')");
        
        ResultSet result = session.execute("SELECT * from users WHERE username='aryya'");
        for(Row row : result){
            System.out.println("Username: "+ row.getString("username") + " password: "+row.getString("password"));
        }
        cluster.close();
    }
}
