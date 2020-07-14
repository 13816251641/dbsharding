package com.lujieni.dbsharding.simple.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.core.strategy.route.inline.InlineShardingStrategy;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShardingJdbcConfig {


    Map<String, DataSource> createDataSourceMap(){
        DruidDataSource dataSource1 = new DruidDataSource();
        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://localhost:3306/order_db?useUnicode=true");
        dataSource1.setUsername("root");
        dataSource1.setPassword("root");
        Map<String,DataSource> result = new HashMap<>();
        result.put("m1",dataSource1);
        return result;
    }

    /**
     * 定于主键生成策略
     * @return
     */
    private static KeyGeneratorConfiguration getKeyGeneratorConfiguration(){
        KeyGeneratorConfiguration result = new KeyGeneratorConfiguration("snowflake","order_id");
        return result;
    }

    /**
     * 定于t_order表的分片策略
     * @return
     */
    TableRuleConfiguration getOrderTableRuleConfiguration(){
        TableRuleConfiguration result = new TableRuleConfiguration("t_order","m1.lujieni");
        result.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id","t_order_$->{order_id % 2 + 1}"));
        result.setKeyGeneratorConfig(getKeyGeneratorConfiguration());
        return result;
    }

    /**
     * 定义sharding-jdbc数据源
     * @return
     * @throws SQLException
     */
    @Bean
    DataSource getShardingDataSource() throws SQLException{
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        Properties properties = new Properties();
        properties.put("sql.show","true");
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(),shardingRuleConfig,properties);
    }


}