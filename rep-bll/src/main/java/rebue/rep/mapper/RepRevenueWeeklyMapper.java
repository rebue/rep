package rebue.rep.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import rebue.rep.mo.RepRevenueDailyMo;
import rebue.rep.mo.RepRevenueWeeklyMo;
import rebue.rep.to.UpdateTurnoverTo;
import rebue.robotech.mapper.MybatisBaseMapper;

@Mapper
public interface RepRevenueWeeklyMapper extends MybatisBaseMapper<RepRevenueWeeklyMo, Long> {
    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(RepRevenueWeeklyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(RepRevenueWeeklyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    RepRevenueWeeklyMo selectByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(RepRevenueWeeklyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(RepRevenueWeeklyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<RepRevenueWeeklyMo> selectAll();

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<RepRevenueWeeklyMo> selectSelective(RepRevenueWeeklyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(RepRevenueWeeklyMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(RepRevenueWeeklyMo record);
    
    int updateWeekTurnover(UpdateTurnoverTo to);
    
    
    @Select("select * from REP_REVENUE_WEEKLY where  SHOP_ID  = #{shopId,jdbcType=TINYINT} and YEAR =  #{year}  and WEEK_OF_YEAR >= #{revenueStartWeek} and  WEEK_OF_YEAR <=  #{revenueEndWeek}  order by YEAR ,WEEK_OF_YEAR  ")
    List<RepRevenueWeeklyMo> selectRevenueOfWeek( @Param("shopId") Long shopId, @Param("year") int year,@Param("revenueStartWeek") int revenueStartWeek, @Param("revenueEndWeek")  int revenueEndWeek);
}