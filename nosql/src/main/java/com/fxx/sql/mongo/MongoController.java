package com.fxx.sql.mongo;

import com.fxx.NosqlCurd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: FeiXinXin
 * @CreateDate: 2019/7/19 11:26
 * @Version: 1.0
 */
@RestController
@RequestMapping("/mongo")
public class MongoController {

    private Logger logger = LoggerFactory.getLogger(MongoController.class);

    @Autowired(required = false)
    private NosqlCurd nosqlCurd;

    @PostMapping("/save")
    public Object saveMongo(Shipper shipper) {
        logger.info("新增mongo数据");
        nosqlCurd.save(shipper);
        logger.info("新增mongo数据成功");
        return "成功";
    }

    @PostMapping("/delete")
    public Object deleteMongo(String userId) {
        logger.info("删除mongo数据");
        nosqlCurd.delete(userId);
        logger.info("删除mongo数据成功");
        return "成功";
    }

    @PostMapping("/update")
    public Object updateeMongo(Shipper shipper) {
        logger.info("修改 mongo数据");
        long update = nosqlCurd.update(shipper);
        if (update > 0) {
            logger.info("修改 mongo数据成功");
            return "成功";
        }
        logger.info("修改 mongo数据成功");
        return "失败";
    }

    @PostMapping("/queryAll")
    public Object queryAll() {
        logger.info("查询所有mongo数据");
        List list = nosqlCurd.queryAll();
        logger.info("查询所有mongo数据成功");
        return list;
    }

    @PostMapping("/queryByCondionMongo")
    public Object queryByCondionMongo(String userId) {
        logger.info("查询id --mongo数据");
        List list = nosqlCurd.queryByCondion(userId);
        logger.info("查询id --mongo数据成功");
        return list;
    }
}
