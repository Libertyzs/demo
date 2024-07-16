package sta;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy {
    private Object target;
    public JdkProxy(Object target){
        this.target = target;
    }

    public Object getInstance(){
        // 使用proxy的方法创建一个代理类实例
        // 参数1：获取被代理对象的类加载器，用于加载代理对象类的字节码
        // 参数2：获取被代理对象是实现的接口数组，代理对象需要实现的接口
        // 参数3：实现InvocationHandler接口，定义代理对象方法的调用处理逻辑。
        // 就是说这个接口中invoke中的method就是我们被代理对象中实现的方法，我们可以在invoke中增强原本方法的逻辑。
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new InvocationHandler() {
            // 参数1：代理实例本身
            // 参数2: 被调用的方法对象
            // 参数3: 调用方法时的参数
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("接口类型"+(proxy instanceof IUserDAO));
                System.out.println("方法增强");
                Object result = method.invoke(target, args);
                System.out.println("方法执行完");

                return result;
            }
        });
    }


}
