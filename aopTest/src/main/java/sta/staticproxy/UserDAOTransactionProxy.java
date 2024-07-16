package sta.staticproxy;


import sta.IUserDAO;
import sta.User;

/**
 * 定义一个静态的代理类
 * UserDAOTransactionProxy 可以被称为静态代理。
 * 静态代理是指在编译时就已经确定了代理类和被代理类的关系，代理类的代码是在编译期间就写好的。
 * 该类的save方法中的除了 realMapper.save(user) 之外都叫做代理类的增强逻辑
 * 当客户端代码使用 UserDAOTransactionProxy 对象调用 save 方法时
 * ，实际上是通过代理类间接调用了 realMapper.save(user) 方法，并在前后添加了额外的处理逻辑
 */
public class UserDAOTransactionProxy implements IUserDAO {

    // 定义了一个IUserDAO接口的引用
    private IUserDAO realMapper;

    public UserDAOTransactionProxy(IUserDAO realMapper){
        this.realMapper = realMapper;
    }

    @Override
    public void save(User user) {

        // 增强逻辑：执行前处理
        System.out.println("proxy-开启事务");

        try {
            realMapper.save(user);
        } catch (Exception e) {
            // 增强逻辑：执行异常时处理
            System.out.println("proxy-异步通知");
        }
        // 增强逻辑：执行后处理
        System.out.println("proxy-提交事务");
    }



}
