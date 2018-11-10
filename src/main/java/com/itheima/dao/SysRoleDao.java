package com.itheima.dao;

import com.itheima.domain.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysRoleDao {


    @SelectKey(statement = "select role_seq.nextval from dual",keyProperty = "id",before = true,resultType = Integer.class)
    @Insert("insert into sys_role values(#{id},#{roleName},#{roleDesc})")
    int add(SysRole sysRole);

    @Select("select * from sys_role")
    List<SysRole> list();

    //--查询角色信息
    @Select("select * from sys_role sr,sys_user_role sur where sr.id = sur.roleid and sur.userid =  #{userId}")
    @Results(
            value = {
                    @Result(
                            property = "permissions",column = "roleid",many = @Many(select = "com.itheima.dao.SysPermissionDao.findPermissionByRoleId")
                    )
            }
    )
    List<SysRole> findRoleByUserId(Long userId);
}
