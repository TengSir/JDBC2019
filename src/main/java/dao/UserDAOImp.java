package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImp  extends BaseDAOImp  implements  UserDAO{
    @Override
    public User login(String username, String password) {
        User user=null;
        ResultSet rs=null;
        PreparedStatement pre=getPreSta("select  * from user where username=? and password=?");
        try{
            pre.setString(1,username);
            pre.setString(2,password);
           rs= pre.executeQuery();
           if(rs.next()){
               user=new User();
               user.setUsername(rs.getString("username"));
               //other property setters
           }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
           closeSource(pre,rs);
        }
        return user;
    }

    @Override
    public boolean register(User user) {
        boolean result=false;
        PreparedStatement pre=null;
        try{

            pre=getPreSta("insert  into user values(?,?,?)");
            pre.setString(1,user.getUsername());
            pre.setString(2,user.getPassword());
            pre.setString(3,user.getnickname());
            int count=pre.executeUpdate();
            result=count>0?true:false;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
          closeSource(pre,null);
        }
        return result;
    }

    @Override
    public boolean updatePassword(User user) {
        return false;
    }

    @Override
    public boolean updateImage(User user, String newImagepath) {
        return false;
    }
}
