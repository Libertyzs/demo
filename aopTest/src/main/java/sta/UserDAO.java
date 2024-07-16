package sta;

public class UserDAO implements IUserDAO{
    @Override
    public void save(User user) {
        System.out.println("UserDAO执行了更新/修改用户的操作");
    }

    public UserDAO(){}
}
