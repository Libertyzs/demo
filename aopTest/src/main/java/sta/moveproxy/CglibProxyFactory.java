package sta.moveproxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import sta.UserDAO;

import java.lang.reflect.Method;
public class CglibProxyFactory implements MethodInterceptor {

    private Object target;
    public CglibProxyFactory(Object target){
        this.target = target;
    }

    // 拿到代理对象
    public Object getProxyInstance(){
        //cglib中的类，用来创建代理类
        Enhancer enhancer = new Enhancer();
        //设置代理类的父类为目标对象的类。Cglib通过创建目标类的子类来实现代理
        enhancer.setSuperclass(target.getClass());
        //设置回调函数，即当前实例(CglibProxyFactory实现了MethodInterceptor接口)
        enhancer.setCallback(this);
        //创建代理对象
        return enhancer.create();
    }

    // 工作流程
    // 创建目标对象：通过构造函数传入。
    // 创建代理对象：getProxyInstance方法使用Enhancer类创建代理对象。
    // 方法拦截和增强：当代理对象的方法被调用时，会触发intercept方法：
    // 在方法执行前打印增强信息。
    // 调用目标对象的方法。
    // 在方法执行后打印增强信息。
    // 返回目标方法的结果。

    // 方法的拦截器
    // 参数：
    // obj：代理对象。
    // method：被调用的方法。
    // args：方法参数。
    // proxy：方法代理，用于调用父类的方法。
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("当前代理类继承了UserDAO类:" + (obj instanceof UserDAO));
        System.out.println("CglibProxy-执行前增强");

        // 执行目标对象的方法
        Object returnValue = method.invoke(target, args);

        System.out.println("CglibProxy-执行后增强");
        return returnValue;
    }

}
