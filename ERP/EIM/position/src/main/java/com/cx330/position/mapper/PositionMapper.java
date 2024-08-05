package com.cx330.position.mapper;

import com.cx330.entity.Position;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PositionMapper {
    // 新增岗位
    @Insert("INSERT INTO position_table (position_name, position_code, responsibility_desc,salary_range, education_background_requirement, work_experience_requirement, position_status) VALUES (#{positionName}, #{positionCode}, #{responsibilityDesc}, #{salaryRange}, #{educationBackgroundRequirement}, #{workExperienceRequirement}, #{positionStatus})")
    int insertPosition(Position position);
    // 删除岗位
    @Delete("DELETE FROM position_table WHERE position_id = #{positionId}")
    int deletePosition(Integer positionId);
    // 修改岗位
    @Update("UPDATE position_table SET position_name = #{positionName}, position_code = #{positionCode}, responsibility_desc = #{responsibilityDesc}, salary_range = #{salaryRange}, education_background_requirement = #{educationBackgroundRequirement}, work_experience_requirement = #{workExperienceRequirement}, position_status = #{positionStatus} WHERE position_id = #{positionId}")
    int updatePosition(Position position);
    // 查询岗位列表
    @Select("SELECT * FROM position_table")
    List<Position> getPositionList();
    // 根据id查询岗位信息
    @Select("SELECT * FROM position_table WHERE position_id = #{positionId}")
    Position getPositionById(Integer positionId);

}
