package com.qingcheng.timer;

import com.qingcheng.dao.SeckillGoodsMapper;
import com.qingcheng.pojo.seckill.SeckillGoods;
import com.qingcheng.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Component
public class SeckillGoodsTask {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    @Scheduled(cron = "0/15 * * * * ?")
    public void loadGoods() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(new Date()));
        List<Date> dateMenus = DateUtil.getDateMenus();

        for (Date  startTime: dateMenus) {
            Example example = new Example(SeckillGoods.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("status", "1");
            criteria.andGreaterThan("stockCount", 0);
            criteria.andGreaterThanOrEqualTo("startTime", startTime);
            criteria.andLessThan("endTime", DateUtil.addDateHour(startTime,2));

            Set keys = redisTemplate.boundHashOps("SeckillGoods_" + DateUtil.date2Str(startTime)).keys();
            if (keys != null && keys.size() > 0) {
                criteria.andNotIn("id", keys);
            }

            List<SeckillGoods> seckillGoods=seckillGoodsMapper.selectByExample(example);
            System.out.println(format.format(startTime)+"共查询到商品"+seckillGoods.size()+"件");
            for (SeckillGoods seckillGood : seckillGoods) {
                redisTemplate.boundHashOps("SeckillGoods_"+DateUtil.date2Str(startTime)).put(seckillGood.getId(),seckillGood);
                Long[] ids = pushIds(seckillGood.getStockCount(), seckillGood.getId());
                redisTemplate.boundListOps("SeckillGoodsCountList_" + seckillGood.getId()).leftPushAll(ids);

                //创建自定义key的值
                redisTemplate.boundHashOps("SeckillGoodsCount").increment(seckillGood.getId(), seckillGood.getStockCount());
            }

        }
    }

    /**
     * 组装商品Id
     * @param len
     * @param id
     * @return
     */
    public Long[] pushIds(int len, Long id) {
        Long[] ids = new Long[len];
        for (int i = 0; i < len; i++) {
            ids[i]=id;
        }
        return ids;
    }
}
