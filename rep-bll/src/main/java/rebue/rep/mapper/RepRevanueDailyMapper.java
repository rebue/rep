package rebue.rep.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.rep.mo.RepRevanueDailyMo;
import rebue.robotech.mapper.MybatisBaseMapper;

@Mapper
public interface RepRevanueDailyMapper extends MybatisBaseMapper<RepRevanueDailyMo, Long> {
    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(RepRevanueDailyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(RepRevanueDailyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    RepRevanueDailyMo selectByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(RepRevanueDailyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(RepRevanueDailyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<RepRevanueDailyMo> selectAll();

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<RepRevanueDailyMo> selectSelective(RepRevanueDailyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(RepRevanueDailyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(RepRevanueDailyMo record);
}