package com.itheima.dao;

import com.itheima.domain.SysPermission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

public interface SysPermissionDao {
    @SelectKey(statement = "select permission_seq.nextval from dual",keyProperty = "id",before = true,resultType = Long.class)
    @Insert("insert into sys_permission values(#{id},#{permissionName},#{url},#{pid})")
    int add(SysPermission sysPermission);

    @Select("select * from sys_permission")
    List<SysPermission> list();


    //--查询权限信息
    @Select("select * from sys_permission sp,sys_role_permission srp where sp.id = srp.permissionid and srp.roleid = #{roleId}")
    List<SysPermission> findPermissionByRoleId(Integer roleId);
}
