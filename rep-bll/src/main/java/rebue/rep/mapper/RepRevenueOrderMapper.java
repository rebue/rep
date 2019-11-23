package rebue.rep.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.rep.mo.RepRevenueOrderMo;
import rebue.robotech.mapper.MybatisBaseMapper;

@Mapper
public interface RepRevenueOrderMapper extends MybatisBaseMapper<RepRevenueOrderMo, Long> {
    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(RepRevenueOrderMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(RepRevenueOrderMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    RepRevenueOrderMo selectByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(RepRevenueOrderMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(RepRevenueOrderMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<RepRevenueOrderMo> selectAll();

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<RepRevenueOrderMo> selectSelective(RepRevenueOrderMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(RepRevenueOrderMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(RepRevenueOrderMo record);
}