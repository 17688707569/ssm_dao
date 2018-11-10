package com.itheima.dao;

import com.itheima.domain.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysUserDao {
    //查询所有用户数据
    @Select("select * from sys_user")
    List<SysUser> list();

    @SelectKey(statement = "select user_seq.nextval from dual",keyProperty = "id",before = true,resultType = Long.class)
    @Insert("insert into sys_user values(#{id},#{username},#{email},#{password},#{phoneNum},#{status})")
    int add(SysUser sysUser);

    @Select("select *  from sys_user where username = #{username}")
    @Results(
            value = {
                    @Result(property = "roles",column = "id",many = @Many(select = "com.itheima.dao.SysRoleDao.findRoleByUserId"))
            }
    )
    SysUser findByUsername(String username);

    //用户详细页面 （查询用户信息）
    @Select("select * from sys_user t where t.id = #{id}")
    @Results(
            value = {
                    @Result(property = "roles",column = "id",many = @Many(select = "com.itheima.dao.SysRoleDao.findRoleByUserId"))
            }
    )
    SysUser findSysUserById(Long id);
}
