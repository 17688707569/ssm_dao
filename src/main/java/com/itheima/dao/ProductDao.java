package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProductDao {
    @Select("select * from product")
    List<Product> list();

    //statement 查询序列的语句
    //keyProperty 将查询的序列值设置到此属性中
    //before 在插入之前查询序列值
    //resultType 查询序列结果类型
    @SelectKey(statement = "select product_seq.nextval from dual",keyProperty = "id",before = true,resultType = Integer.class)
    @Insert("insert into product(id,productnum,productname,cityname,departuretime,productprice,productdesc,productstatus) values(#{id},#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    int add(Product product);

    @Select("select * from product where id = #{id}")
    Product findById(Integer id);

    @Update("update product set productnum=#{productNum},productname=#{productName},cityname=#{cityName},departuretime=#{departureTime},productprice=#{productPrice},productdesc=#{productDesc},productstatus=#{productStatus} where id = #{id}")
    int update(Product product);

    @Delete("delete from product where id = #{id}")
    int delete(Integer id);

    //查询总记录数
    @Select("select count(*) from product")
    int findCount();

    //实体对象 不用使用@Param注解  但如果多个基本数据类型要使用@Param
    @Select("select * from(select rownum rn, t.* from product t) where rn >= #{start} and rn <= #{end}")
    List<Product> pageList(@Param(value = "start") int start,@Param(value = "end") int end);
}
