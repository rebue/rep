package rebue.rep.mapper;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import rebue.rep.mo.RepRevanueMonthlyMo;
import rebue.robotech.mapper.MybatisBaseMapper;

@Mapper
public interface RepRevanueMonthlyMapper extends MybatisBaseMapper<RepRevanueMonthlyMo, Long> {

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(RepRevanueMonthlyMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(RepRevanueMonthlyMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    RepRevanueMonthlyMo selectByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(RepRevanueMonthlyMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(RepRevanueMonthlyMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<RepRevanueMonthlyMo> selectAll();

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<RepRevanueMonthlyMo> selectSelective(RepRevanueMonthlyMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(RepRevanueMonthlyMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(RepRevanueMonthlyMo record);
    
    int updateMonthTurnover(@Param("newTurnover") BigDecimal newTurnover,@Param("ordTurnover") BigDecimal oldTurnover,@Param("id") Long id);

}
