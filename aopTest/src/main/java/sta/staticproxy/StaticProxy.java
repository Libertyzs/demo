package sta.staticproxy;

import sta.IUserDAO;
import sta.User;
import sta.UserDAO;

public class StaticProxy implements IUserDAO {

    private IUserDAO  iUserDAO;

    public StaticProxy(IUserDAO userDAO){
        iUserDAO = userDAO;
    }


    @Override
    public void save(User user) {
        System.out.println("开启proxy静态代码");

        try {
            iUserDAO.save(user);
        } catch (Exception e) {
            // 增强逻辑：执行异常时处理
            System.out.println("proxy-异步通知");
        }
        // 增强逻辑：执行异常时处理
        System.out.println("关闭proxy静态代理");

    }
}
