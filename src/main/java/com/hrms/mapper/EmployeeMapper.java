package com.hrms.mapper;

import com.hrms.bean.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmployeeMapper {
    String TABLE_NAME = "tbl_emp";
    String INSERT_FIELDS = "emp_name, emp_email, gender, department_id";
    String SELECT_FIELDS = "emp_id, " + INSERT_FIELDS;


    @Delete({"DELETE FROM", TABLE_NAME, "WHERE emp_id = #{empId}"})
    int deleteOneById(@Param("empId") Integer empId);

    int updateOneById(@Param("empId") Integer empId,
                      @Param("employee") Employee employee);

    @Insert({"INSERT INTO", TABLE_NAME, "(",INSERT_FIELDS,") " +
            "VALUES(#{empName}, " +
            "#{empEmail}, " +
            "#{gender}, " +
            "#{departmentId})"})
    int insertOne(Employee employee);


    Employee selectOneById(@Param("empId") int empId);
    Employee selectOneByName(@Param("empName") String empName);

    Employee selectWithDeptById(@Param("empId") Integer empId);

    /**
     * 分页查询
     * @param limit 返回记录最大行数
     * @param offset 返回记录行的偏移量
     * @return 如offset=10，limit=5的时候，就会从数据库第11行记录开始返回5条查询结果，即范围从(offset+1)---(offset+limit)
     */
    List<Employee> selectByLimitAndOffset(@Param("offset") Integer offset,
                                          @Param("limit") Integer limit);

    /**
     * 查询总记录数
     * @return
     */
    @Select({"select count(*) from", TABLE_NAME})
    int countEmps();
}
