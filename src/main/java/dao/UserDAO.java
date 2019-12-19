package dao;

/**
 * 定义user模块的dao应该声明的方法集合
 */
public interface UserDAO {

    public User login(String username,String password);
    public boolean register(User user);
    public boolean updatePassword(User user);
    public boolean updateImage(User user,String newImagepath);

}
class User{}
