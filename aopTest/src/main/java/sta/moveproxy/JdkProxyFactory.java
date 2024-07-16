package sta.moveproxy;

import sta.IUserDAO;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyFactory {
    private Object target;
    public JdkProxyFactory(Object target){
        this.target = target;
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("当前类实现了IUserDAO接口：" + (proxy instanceof IUserDAO));
                        System.out.println("JdkProxy-执行前增强");

                        Object result = method.invoke(target, args);
                        System.out.println("JdkProxy-执行后增强");
                        return result;
                    }
                });
    }

}
