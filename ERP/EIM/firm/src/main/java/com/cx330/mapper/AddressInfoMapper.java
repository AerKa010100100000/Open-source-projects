package com.cx330.mapper;

import com.cx330.entity.AddressInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressInfoMapper {
    // 新增地址
    @Insert("INSERT INTO address_info (company_id, address_line, city, state_province, country, postal_code) VALUES (#{companyId}, #{addressLine}, #{city}, #{stateProvince}, #{country}, #{postalCode})")
    int addAddress(AddressInfo addressInfo);
    // 修改地址
    @Update("UPDATE address_info SET  address_line = #{addressLine}, city = #{city}, state_province = #{stateProvince},country = #{country}, postal_code = #{postalCode} WHERE address_id = #{addressId} ")
    int updateAdder(AddressInfo addressInfo);
    // 删除地址
    @Delete("DELETE FROM address_info WHERE address_id = #{addressId} ")
    int deleteAddress(int addressId);

    // 查询所有地址信息
    @Select("SELECT * FROM address_info")
    List<AddressInfo> selectAllAddress();

}
