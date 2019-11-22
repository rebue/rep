package rebue.rep.mapper;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import rebue.rep.mo.RepRevanueAnnualMo;
import rebue.rep.to.UpdateTurnoverTo;
import rebue.robotech.mapper.MybatisBaseMapper;

@Mapper
public interface RepRevanueAnnualMapper extends MybatisBaseMapper<RepRevanueAnnualMo, Long> {

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(RepRevanueAnnualMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(RepRevanueAnnualMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    RepRevanueAnnualMo selectByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(RepRevanueAnnualMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(RepRevanueAnnualMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<RepRevanueAnnualMo> selectAll();

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<RepRevanueAnnualMo> selectSelective(RepRevanueAnnualMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(RepRevanueAnnualMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(RepRevanueAnnualMo record);
    
    int updateYearTurnover(UpdateTurnoverTo to);

}
