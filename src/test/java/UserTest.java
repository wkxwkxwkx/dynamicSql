import com.qcby.dao.UserDao;
import com.qcby.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UserTest {
    private InputStream in = null;
    private SqlSession session = null;
    private UserDao mapper = null;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        mapper = session.getMapper(UserDao.class);
    }
    @After
    public void destory() throws IOException {
        session.close();
        in.close();
    }
    @Test
    public void selectUsernameAndSex(){
        User user = new User();
//        user.setUsername("熊大");
//        user.setSex("女");
        List<User> users = mapper.selectUsernameAndSex(user);
        for(User user1 : users) System.out.println(user1.toString());
    }
    @Test
    public void updateBySet(){
        User user = new User();
        user.setUsername("修改后");
        user.setId(18);
        int count = mapper.updateBySet(user);
        session.commit();
        System.out.println(count);
    }
    @Test
    public void selectByChoose(){
        User user = new User();
//        user.setId(18);
//        user.setUsername("更新");
//        user.setSex("女");
        List<User> users = mapper.selectByChoose(user);
        for (User user1 : users) {
            System.out.println(user1.toString());
        }
    }
    @Test
    public void deleteByArray(){
        Integer[] ids = new Integer[]{14,17,18};
        int count= mapper.deleteByArray(ids);
        session.commit();
    }
    @Test
    public void insertByList(){
        User user1 = new User("wkx",new Date(),"男","邯郸");
        User user2 = new User("wkx2",new Date(),"男","邯郸");
        User user3 = new User("wkx3",new Date(),"男","邯郸");
        User user4 = new User("wkx4",new Date(),"男","邯郸");
        List<User> users = Arrays.asList(user1,user2,user3,user4);
        int count = mapper.insertByList(users);
        session.commit();
    }
}
