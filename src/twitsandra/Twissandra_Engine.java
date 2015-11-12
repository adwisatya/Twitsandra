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

import java.util.Date;

/**
 *
 * @author adwisatya
 */
public class Twissandra_Engine {
    private String hostname;
    private String keyspace;
    private Cluster cluster;
    private Session session;
    
    public Twissandra_Engine(String _hostname, String _keyspace) {
        hostname = _hostname;
        keyspace = _keyspace;
        cluster = Cluster.builder().addContactPoint(hostname).build();
        session = cluster.connect(keyspace);
    }
    public boolean show_user(String username){
        ResultSet result = session.execute("SELECT * from users WHERE username='"+username+"'");
        if (result.getAvailableWithoutFetching()>0) {
            for (Row row : result) {
               // System.out.println("Username: " + row.getString("username") + " password: " + row.getString("password"));
            }
            return true;
        }else{
            System.out.println("tidak ada");
            return false;
        }
    }
    public int check_user(String username, String password){
        ResultSet result = session.execute("SELECT * from users WHERE username='"+username+"'");
        int bool = 2;
        if (result.getAvailableWithoutFetching()>0) {
            for (Row row : result) {
                //System.out.println("Username: " + row.getString("username") + " password: " + row.getString("password"));
                if (row.getString("password").equals(password)) {
                    bool = 0;
                } else {
                    bool = 1;
                }
            }
        }else{
            System.out.println("tidak ada");
        }
        return bool;
    }

    public boolean show_follower(String username, String target){
        ResultSet result = session.execute("SELECT * from followers WHERE username='"+username+"' AND follower='"+target+"'");
        if (result.getAvailableWithoutFetching()>0) {
            return true;
        }else{
            return false;
        }
    }
    public boolean register_user(String username, String password){
        try{
            ResultSet result = session.execute("INSERT INTO users (username,password) VALUES('"+username+"','"+password+"')");
            return true;
        }catch(Exception e){
        }
        return false;
    }
    public boolean follow(String target, String sumber) {
        try {
            Date sejak = new Date();
            if (show_user(target)) {
                if (!show_follower(target, sumber)) {
                    ResultSet result = session.execute("INSERT INTO followers (username,follower,since) VALUES('" + target + "','" + sumber + "', 'NOW')");
                    ResultSet result2 = session.execute("INSERT INTO friends (username,friend,since) VALUES('" + sumber + "','" + target + "', 'NOW')");
                    return true;
                } else {
                    System.out.println("Kamu udah follow dia");
                }
            }else{
                System.out.println("Yang kamu mau follow gak ada");
            }
        } catch (Exception e) {
        }
        return false;
    }
    public boolean tweet(String tweet){
        
        return true;
    }
    public void show_tweet(String username){
        try{
            ResultSet result = session.execute("SELECT * from tweets WHERE username='"+username+"'");
            if(result.getAvailableWithoutFetching()>0){
                for(Row row : result){
                    System.out.println("Username: "+ row.getString("username") + " tweet: "+row.getString("tweet"));
                }
            }else{
                System.out.println("Tidak ada tweet dari "+username);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void teminate_connection(){
        cluster.close();
    }
}
