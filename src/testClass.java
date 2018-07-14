import Dao.LoginDao;

public class testClass {

  public static void main(String[] args) throws Exception {
    
    LoginDao logindao = LoginDao.getInstance();
    System.out.println(logindao.login("usernamesample1","passwordsample1"));
    System.out.println("deleNoti9977".substring(7));
  }
}
