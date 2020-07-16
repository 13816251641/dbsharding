import com.lujieni.dbsharding.simple.ShardingJdbcSimpleStarter;
import com.lujieni.dbsharding.simple.dao.OrderDao;
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

    @Test
    public void testInsertOrder(){
        IntStream.rangeClosed(1,20).forEach(e->{
            orderDao.insertOrder(new BigDecimal(e),1L,"success");
        });
    }

    @Test
    public void testSelectOrder(){
        List<Map> result = orderDao.selectOrderById(Arrays.asList(490490770374000640L));
        for (Map map:result) {
            System.out.println(map);
        }
    }
}
