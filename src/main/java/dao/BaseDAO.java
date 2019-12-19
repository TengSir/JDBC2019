package dao;

/**
 * 这里面一般用来定义所有子类需要使用的到一些公共的参数和方法
 */
public interface BaseDAO {

    public static final String driverName="oracle.jdbc.driver.OracleDriver";
    String url="jdbc:oracle:thin:@localhost:1521:xe";
    String username="bankadmin";
    String password="bankadmin";
}
