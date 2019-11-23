package rebue.rep.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.rep.mo.RepRevenueMonthlyMo;
import rebue.rep.to.UpdateTurnoverTo;
import rebue.robotech.mapper.MybatisBaseMapper;

@Mapper
public interface RepRevenueMonthlyMapper extends MybatisBaseMapper<RepRevenueMonthlyMo, Long> {
    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(RepRevenueMonthlyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(RepRevenueMonthlyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    RepRevenueMonthlyMo selectByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(RepRevenueMonthlyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(RepRevenueMonthlyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<RepRevenueMonthlyMo> selectAll();

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<RepRevenueMonthlyMo> selectSelective(RepRevenueMonthlyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(RepRevenueMonthlyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(RepRevenueMonthlyMo record);
    
    
    int updateMonthTurnover(UpdateTurnoverTo to);
}