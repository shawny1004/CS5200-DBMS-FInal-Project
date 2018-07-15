import Dao.*;
import Model.Contract;
import Model.CreditCard;
import Model.FAQ;
import Model.Users;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class Test {

    //initialize dao instance
    LoginDao logindao = LoginDao.getInstance();
    CreditCardDao creditCardDao = CreditCardDao.getInstance();
    UsersDao usersDao = UsersDao.getInstance();
    ContractDao contractDao = ContractDao.getInstance();
    FAQDao faqdao = FAQDao.getInstance();

    //initialize creditcard
    String time = "2033-10-02 18:48:05.123456";
    Timestamp ts = Timestamp.valueOf(time);
    CreditCard creditCard1 = new CreditCard("555666", ts, "556", 2);

    //initialize contract
    int needId = 8862;
    String type = "People";
    int TypeId = 3442;
    int quantity = 1;
    Timestamp currentTS = new Timestamp(System.currentTimeMillis());
    Contract contract = new Contract(needId, type, TypeId, quantity, currentTS);

    //initialize faq
    int faqId = 10;
    int projectId = 3355;
    String question = "This is a question";
    String answer = "New Answer";
    String time2 = "2011-11-22 22:33:44";
    Timestamp ts2 = Timestamp.valueOf(time2);
    boolean isAvailable = true;
    int userId = 5;
    FAQ faq1 = new FAQ(faqId, projectId, question, answer, ts2, isAvailable, userId);

    public void testInsertCredit() throws SQLException {
        String time2 = "2055-05-22 12:13:05";
        Timestamp ts2 = Timestamp.valueOf(time2);
        CreditCard creditCard2 = new CreditCard("33333555", ts2, "111", 1);
        creditCardDao.create(creditCard2);
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

    public void createContract() throws SQLException {
        contractDao.createContact(contract);
    }

    public void getContractById() throws SQLException {
        int contractId = 5;
        Contract contract = contractDao.getContractById(contractId);
        System.out.println(contract.getNeedID());
        System.out.println(contract.getTypeID());
        System.out.println(contract.getQuantity());
    }

    public void deleteContractById() throws SQLException {
        int contractId = 4;
        contractDao.deleteContractById(contractId);
    }

    public void getRecentContract() {
        int lastNDays = 3;
        contractDao.getRecentContract(lastNDays);
    }

    public void updateContractQuantity() throws SQLException {
        int quantity = 50;
        Contract currentContract = contractDao.getContractById(10);
        contractDao.updateContract(currentContract, quantity);
    }

    public void insertFAQ() throws SQLException {
        faqdao.create(faq1);
    }

    public void deleteFAQ() throws SQLException {
        int userId = 8048;
        faqdao.deleteFAQByUserId(userId);
    }

    public void searchFAQByUserId() throws SQLException {
        int userId = 678;
        faqdao.getFAQsByUserId(userId);
    }

    public void updateFAQ() throws SQLException {
        String questionStr = "this is a new question";
        String answerStr = "this is a new answer";
        faqdao.updateFAQ(faq1, questionStr, answerStr);
    }

    public static void main(String[] args) throws Exception {

        Test test = new Test();
//        test.testInsertCredit();
//        test.updateCreditExpirationDcontractsate();
//        test.deleteCreditCard();
//        test.searchUserByUserId();
//        test.getCreditCardByCreditCString time = "2033-10-02 18:48:05.123456";
//    Timestamp ts = Timestamp.valueOf(time);
//    CreditCard creditCard1 = new CreditCard("555666", ts, "556", 2);ard();
//        test.searchUserByUserId();
//        test.getAllCreditCards();
//        test.createContract();
//        test.getContrFAQactById();deleteContractById
//        test.deleteContractById();
//        test.getRecentContract();
//        test.updateContractQuantity();
//        test.insertFAQ();
//        test.deleteFAQ();
//        test.searchFAQByUserId();
        test.updateFAQ();
    }


}
