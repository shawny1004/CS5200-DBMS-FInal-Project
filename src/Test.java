import Dao.CreditCardDao;
import Dao.LoginDao;
import Dao.UsersDao;
import Model.CreditCard;
import Model.Users;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class Test {

    //initialize dao instance
    LoginDao logindao = LoginDao.getInstance();
    CreditCardDao creditCardDao = CreditCardDao.getInstance();
    UsersDao usersDao = UsersDao.getInstance();

    //initialize parameter
    String time = "2033-10-02 18:48:05.123456";
    Timestamp ts = Timestamp.valueOf(time);
    CreditCard creditCard1 = new CreditCard("555666", ts, "556", 2);

    public void testInsertCredit() throws SQLException {
        String time2 = "2055-05-22 12:13:05";
        Timestamp ts2 = Timestamp.valueOf(time2);
        CreditCard creditCard1 = new CreditCard("33333555", ts2, "111", 1);
        creditCardDao.create(creditCard1);
    }

    public void updateCreditExpirationDate() throws SQLException {
        String newTime = "2017-11-11 22:11:02";
        Timestamp tsNew = Timestamp.valueOf(newTime);
        creditCardDao.updateExpiration(creditCard1, tsNew);
    }

    public void deleteCreditCard() throws SQLException {
        creditCardDao.deleteCredit(creditCard1);
    }

    public void getCreditCardByCreditCard() throws SQLException {
        String cardNumer = "2683643696246633";
        CreditCard creditCard = creditCardDao.getCreditCardByCardNumber(cardNumer);
        System.out.println(creditCard.getCardNumber());
        System.out.println(creditCard.getExpiration());
        System.out.println(creditCard.getCvv());
    }

    public void searchUserByUserId() throws SQLException {
        int userId = 1;
        Users targetUser = usersDao.findUserByUserId(userId);
        System.out.println(targetUser.getNickName());
        System.out.println(targetUser.getEmail());
    }

    public void getAllCreditCards() throws SQLException {
        int userId = 1;
        List<CreditCard> creditCards = creditCardDao.getCreditCardsByUserId(userId);
    }


    public static void main(String[] args) throws Exception {

        Test test = new Test();
//        test.testInsertCredit();
//        test.updateCreditExpirationDate();
//        test.deleteCreditCard();
//        test.searchUserByUserId();
//        test.getCreditCardByCreditCString time = "2033-10-02 18:48:05.123456";
//    Timestamp ts = Timestamp.valueOf(time);
//    CreditCard creditCard1 = new CreditCard("555666", ts, "556", 2);ard();
//        test.searchUserByUserId();
        test.getAllCreditCards();
    }

}
