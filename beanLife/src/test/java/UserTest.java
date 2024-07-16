import config.SpringConfig;
import lifecycle.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserTest {
    public static void main(String[] agrs){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        User user = ctx.getBean(User.class);
        System.out.println(user);
    }
}
