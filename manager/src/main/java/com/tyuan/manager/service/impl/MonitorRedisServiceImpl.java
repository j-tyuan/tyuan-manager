package com.tyuan.manager.service.impl;

import com.tyuan.manager.service.MonitorRedisService;
import com.tyuan.model.vo.monitor.RedisTreeKeyVo;
import com.tyuan.model.vo.monitor.RedisValueVo;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class MonitorRedisServiceImpl implements MonitorRedisService {

    @Resource(name = "monitorRedisTemplate")
    RedisTemplate monitorRedisTemplate;

    @Override
    public List<RedisTreeKeyVo> getTreeKey() {

        Set<String> keys = monitorRedisTemplate.keys("*");

        RedisTreeKeyVo root = new RedisTreeKeyVo("root");

        for (String key : keys) {

            List<RedisTreeKeyVo> each = null;

            for (String k : key.split(":")) {
                if (each == null) {
                    each = root.getChildren();
                }

                each = creatChild(k, each, key);
            }

        }


        return root.getChildren();
    }

    @Override
    public RedisValueVo getValue(String key) {

        RedisValueVo valueVo = new RedisValueVo();

        DataType dataType = monitorRedisTemplate.type(key);
        valueVo.setType(dataType.code());
        Long expire = monitorRedisTemplate.getExpire(key);
        valueVo.setExpire(expire);

        switch (dataType) {
            case NONE:
                break;
            case STRING:
                ValueOperations valueOperations = monitorRedisTemplate.opsForValue();
                Object o = valueOperations.get(key);
                valueVo.setValue(o);
                return valueVo;
            case LIST:
                ListOperations listOperations = monitorRedisTemplate.opsForList();
                o = listOperations.range(key, 0, -1);
                valueVo.setValue(o);

                return valueVo;
            case SET:
                SetOperations<String, Object> setOperations = monitorRedisTemplate.opsForSet();
                o = setOperations.members(key);
                valueVo.setValue(o);

                return valueVo;
            case ZSET:
                ZSetOperations zSetOperations = monitorRedisTemplate.opsForZSet();
                o = zSetOperations.range(key, 0, -1);
                valueVo.setValue(o);

                return valueVo;
            case HASH:
                HashOperations hashOperations = monitorRedisTemplate.opsForHash();
                o = hashOperations.entries(key);
                valueVo.setValue(o);

                return valueVo;
        }

        return null;
    }


    private List<RedisTreeKeyVo> creatChild(String key, List<RedisTreeKeyVo> child, String path) {

        for (RedisTreeKeyVo tree : child) {
            if (key.equals(tree.getName())) {

                return tree.getChildren();
            }
        }

        RedisTreeKeyVo treeKeyVo = new RedisTreeKeyVo(key);
        treeKeyVo.setPath(path);
        child.add(treeKeyVo);

        return treeKeyVo.getChildren();
    }
}
