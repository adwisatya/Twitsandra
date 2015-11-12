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
import com.datastax.driver.core.utils.UUIDs;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
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
    public boolean is_user_exist(String username){
        ResultSet result = session.execute("SELECT * from users WHERE username='"+username+"'");
        if (result.getAvailableWithoutFetching()>0) {
            return true;
        }else{
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
                    return false;
                }
            }else{
                System.out.println("Yang kamu mau follow gak ada");
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    public void tweet(String username, String tweet){
        try{
            UUID tweet_id =  UUID.randomUUID();
            String follower;
            ResultSet result = session.execute("INSERT INTO tweets (tweet_id,username,body) VALUES("+tweet_id+",'"+username+"','"+tweet+"')");
            insert_to_userline(tweet_id, username);
            insert_to_timeline(tweet_id,username);
            ResultSet result2 = session.execute("SELECT * from followers WHERE username='"+username+"'");
            for (Row row : result2) {
                follower = row.getString("follower");
                insert_to_timeline(tweet_id,follower);
            }
            System.out.println("Tweeted!");
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public void insert_to_userline(UUID tweet_id, String username){
        try{
            ResultSet result = session.execute("INSERT INTO userline (username,time,tweet_id) VALUES(" + username + ",'NOW()','" + tweet_id + "')");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void insert_to_timeline(UUID tweet_id, String username){
        try{
            ResultSet result = session.execute("INSERT INTO timeline (username,time,tweet_id) VALUES(" + username + ",'NOW()','" + tweet_id + "')");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void show_tweet(String username){
        try{
            ResultSet result = session.execute("SELECT * from tweets WHERE username='"+username+"'");
            if(result.getAvailableWithoutFetching()>0){
                System.out.println(username+" tweet's:");
                for(Row row : result){
                    System.out.println(row.getString("body"));
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
    public String toMD5(String input) throws NoSuchAlgorithmException{
        /* taken from http://www.mkyong.com/java/java-md5-hashing-example/ */
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
