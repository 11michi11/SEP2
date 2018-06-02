package server.model.persistance;

import model.MyDate;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class DBMAIN {
    public static void main(String[] args) {
        try {
            Database database = new Database("org.postgresql.Driver","jdbc:postgresql://207.154.237.196:5432/ente?currentSchema=test","ente","ente");
//            database.update("INSERT INTO post (postid, type, title, content, authorname, pubdate) VALUES (?,?,?,?,?,?)","postID","Homework","Title","Content","AuthorName",MyDate.convertFromMyDateToTimestamp(new MyDate(2018,8,8,20,0)));
//            database.update("UPDATE post set title=?,content=?,authorname=?,pubdate=? WHERE postid=?","NewTitle","NewContent","NewAuthorName",MyDate.convertFromMyDateToTimestamp(new MyDate(2000,30,8,20,50)),"postID2");
//            database.update("DELETE FROM post WHERE postid=?","postID");
            ArrayList<Object[]> rs = database.query("SELECT * FROM post");
            for (Object[] e:rs) {
                String postID = (String) e[0];
                String type = (String) e[1];
                String title = (String) e[2];
                String content = (String) e[3];
                String authorName = (String) e[4];
                Timestamp pubDateStamp = (Timestamp) e[5];
                System.out.println(postID+"\n"+type+"\n"+title+"\n"+content+"\n"+authorName+"\n"+MyDate.convertFromTimestampToMyDate(pubDateStamp).toString());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
