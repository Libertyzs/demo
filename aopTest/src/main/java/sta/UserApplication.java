package sta;

import sta.moveproxy.CglibProxyFactory;
import sta.staticproxy.StaticProxy;

/**
 * 测试类：
 * proxy-开启事务
 * UserDAO执行了更新/修改用户的操作
 * proxy-提交事务
 * 通过执行结果你就会发现，我们的代理类在这里只做了增强处理的一个逻辑，原来的save依然执行
 */
public class UserApplication {
    public static void main(String[] args) {
        // IUserDAO userDAO = new UserDAO();
        // UserDAOTransactionProxy userDAOTransactionProxy = new UserDAOTransactionProxy(userDAO);
        // userDAOTransactionProxy.save(new User());

        // // 真实对象（原对象）
        // IUserDAO realMapper = new UserDAO();
        // // 从代理工厂获取代理对象 类型强转
        // // 这里我们并没有在编译之前就创造一个UserDAO的代理类，而是在运行的时候通过反射动态的创建一个代理类
        // IUserDAO mapper = (IUserDAO) new JdkProxyFactory(realMapper).getProxyInstance();
        // // 执行IUserDAO接口对象的方法
        // mapper.save(new User());

        //
        // // 目标对象
        // UserDAO target = new UserDAO();
        // // cglib代理对象
        // UserDAO proxy = (UserDAO) new CglibProxyFactory(target).getProxyInstance();
        // // 执行代理对象方法
        // proxy.save(new User());


        // IUserDAO iUserDAO = new UserDAO();
        // StaticProxy staticProxy = new StaticProxy(iUserDAO);
        // staticProxy.save(new User());

        IUserDAO userDAO = new UserDAO();
        JdkProxy jdkProxy = new JdkProxy(userDAO);
        IUserDAO userDAO1 = (IUserDAO) jdkProxy.getInstance();
        userDAO1.save(new User());
    }
}
