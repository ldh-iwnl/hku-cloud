package hk.hku.main.config;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

@Slf4j
public class MayiktRangeShardingAlgorithm implements PreciseShardingAlgorithm<Integer> {
    private Long TABLE_SIZE = 5l;
    private String TABLE_NAME = "sys_user_";

    /**
     * aop 代理数据源 改写sql语句
     *
     * @param collection
     * @param preciseShardingValue
     * @return
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
        Double temp = Double.valueOf(preciseShardingValue.getValue()) / TABLE_SIZE;
        String tableName = TABLE_NAME + (int) Math.ceil(temp);
        log.info("<tableName{}>", tableName);
        return tableName;
    }
}