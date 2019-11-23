package rebue.rep.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.rep.mo.RepRevenueAnnualMo;
import rebue.rep.to.UpdateTurnoverTo;
import rebue.robotech.mapper.MybatisBaseMapper;

@Mapper
public interface RepRevenueAnnualMapper extends MybatisBaseMapper<RepRevenueAnnualMo, Long> {
    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(RepRevenueAnnualMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(RepRevenueAnnualMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    RepRevenueAnnualMo selectByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(RepRevenueAnnualMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(RepRevenueAnnualMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<RepRevenueAnnualMo> selectAll();

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<RepRevenueAnnualMo> selectSelective(RepRevenueAnnualMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(RepRevenueAnnualMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(RepRevenueAnnualMo record);
    
    int updateYearTurnover(UpdateTurnoverTo to);
}