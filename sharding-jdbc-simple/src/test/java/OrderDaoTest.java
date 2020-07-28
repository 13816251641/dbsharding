import com.lujieni.dbsharding.simple.ShardingJdbcSimpleStarter;
import com.lujieni.dbsharding.simple.dao.OrderDao;
import com.lujieni.dbsharding.simple.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @Auther lujieni
 * @Date 2020/7/9
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingJdbcSimpleStarter.class)
public class OrderDaoTest {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void testInsertOrder(){
        IntStream.rangeClosed(1,20).forEach(e->{
            orderDao.insertOrder(new BigDecimal(e),1L,"success");
        });
    }

    @Test
    public void testSelectOrderByOrderId(){
        List<Map> result = orderDao.selectOrderById(Arrays.asList(493448537367904256L,493448537565036545L));
        for (Map map:result) {
            System.out.println(map);
        }
    }

    @Test
    public void testSelectOrderByUserIdAndOrderId(){
        List<Map> result = orderDao.selectOrderByIdAndUserId(Arrays.asList(493448537367904256L,493448537565036545L),2L);
        for (Map map:result) {
            System.out.println(map);
        }
    }

    @Test
    public void testInsertUser(){
        for(int i=0;i<10;i++){
            Long id = i+1L;
            userDao.insertUser(id,"姓名"+id);
        }
    }
}
