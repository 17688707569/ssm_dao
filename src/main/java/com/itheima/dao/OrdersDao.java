package com.itheima.dao;

import com.itheima.domain.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersDao {
    //orders 中属性叫product 手动赋值
    @Results(
            value = {
                    //property = "product.id" 实体类中属性名称  column = "pid"  查询语句字段名
                    @Result(property = "product.id",column = "pid"),
                    @Result(property = "product.productNum",column = "productnum"),
                    @Result(property = "product.productName",column = "productname"),
                    @Result(property = "product.cityName",column = "cityname"),
                    @Result(property = "product.departureTime",column = "departuretime"),
                    @Result(property = "product.productPrice",column = "productprice"),
                    @Result(property = "product.productDesc",column = "productdesc"),
                    @Result(property = "product.productStatus",column = "productstatus")
            }
    )
    @Select("select o.*,p.id pid,p.productnum,p.productname,p.cityname,p.departuretime,p.productprice,p.productdesc,p.productstatus from orders o,product p where o.productid = p.id")
    List<Orders> list();

    /**
     * 插入数据操作
     * @param orders
     * @return
     */
    @SelectKey(statement = "select orders_seq.nextval from dual",keyProperty ="id",before = true,resultType = Long.class)
    @Insert("insert into orders (id,orderNum,orderTime,peopleCount,orderDesc,payType,orderStatus,productId) values (#{id},#{orderNum},#{orderTime},#{peopleCount},#{orderDesc},#{payType},#{orderStatus},#{product.id})")
    int add(Orders orders);
}
