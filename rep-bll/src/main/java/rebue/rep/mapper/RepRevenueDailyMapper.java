package rebue.rep.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.rep.mo.RepRevenueDailyMo;
import rebue.rep.to.UpdateTurnoverTo;
import rebue.robotech.mapper.MybatisBaseMapper;

@Mapper
public interface RepRevenueDailyMapper extends MybatisBaseMapper<RepRevenueDailyMo, Long> {
    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(RepRevenueDailyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(RepRevenueDailyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    RepRevenueDailyMo selectByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(RepRevenueDailyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(RepRevenueDailyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<RepRevenueDailyMo> selectAll();

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<RepRevenueDailyMo> selectSelective(RepRevenueDailyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(RepRevenueDailyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(RepRevenueDailyMo record);
    
    
    int updateDayTurnover(UpdateTurnoverTo to);
}