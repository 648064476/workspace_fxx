package com.fxx.sql.mongo;

import com.fxx.NosqlCurd;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: FeiXinXin
 * @CreateDate: 2019/7/19 13:51
 * @Version: 1.0
 */
@Component
public class MongoCurd<T> implements NosqlCurd<T> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(T obj) {
        mongoTemplate.save(obj);
    }

    @Override
    public void delete(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        mongoTemplate.remove(query, Shipper.class);
    }

    @Override
    public long update(Shipper obj) {
        Query query = new Query(Criteria.where("userId").is(obj.getUserId()));
        Update update = new Update()
                .set("userName", obj.getUserName())
                .set("userSex", obj.getUserSex())
                .set("userAge", obj.getUserAge())
                .set("11011112222", obj.getUserPhone());
        UpdateResult result = mongoTemplate.updateFirst(query, update, Shipper.class);
        if (result != null){
            return  result.getMatchedCount();
        }
        return 0;
    }

    @Override
    public List queryByCondion(String userId) {
        Query query = new Query();
        Criteria criteria = Criteria.where("userId").is(userId);
        query.addCriteria(criteria);
        List<Shipper> result = mongoTemplate.find(query, Shipper.class);
        return result;
    }

    @Override
    public List queryAll() {
        return mongoTemplate.findAll(Shipper.class);
    }


}
