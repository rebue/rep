package rebue.rep.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.rep.mo.RepRevanueOrderMo;
import rebue.robotech.mapper.MybatisBaseMapper;

@Mapper
public interface RepRevanueOrderMapper extends MybatisBaseMapper<RepRevanueOrderMo, Long> {
    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(RepRevanueOrderMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(RepRevanueOrderMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    RepRevanueOrderMo selectByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(RepRevanueOrderMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(RepRevanueOrderMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<RepRevanueOrderMo> selectAll();

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<RepRevanueOrderMo> selectSelective(RepRevanueOrderMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(RepRevanueOrderMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(RepRevanueOrderMo record);
}