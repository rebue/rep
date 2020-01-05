package rebue.rep.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import rebue.rep.mo.RepRevenueDailyMo;
import rebue.rep.to.UpdateTurnoverTo;
import rebue.robotech.mapper.MybatisBaseMapper;

@Mapper
public interface RepRevenueDailyMapper extends MybatisBaseMapper<RepRevenueDailyMo, Long> {
    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(RepRevenueDailyMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(RepRevenueDailyMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    RepRevenueDailyMo selectByPrimaryKey(Long id);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(RepRevenueDailyMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(RepRevenueDailyMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<RepRevenueDailyMo> selectAll();

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<RepRevenueDailyMo> selectSelective(RepRevenueDailyMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(Long id);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(RepRevenueDailyMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(RepRevenueDailyMo record);

    int updateDayTurnover(UpdateTurnoverTo to);
    
    /**
     * 当年份不同的时候使用这个方法，如果能合并与下面的方法请合并
     * @param shopId
     * @param revenueStartYear
     * @param revenueStartDay
     * @param revenueEndYear
     * @param revenueEndDay
     * @return
     */
    @Select("select * from REP_REVENUE_DAILY where  SHOP_ID  = #{shopId,jdbcType=TINYINT} and ((YEAR =  #{revenueStartYear}  and DAY_OF_YEAR >= #{revenueStartDay} ) or ( YEAR =  #{revenueEndYear}  and DAY_OF_YEAR <=  #{revenueEndDay} ))  order by YEAR ,DAY_OF_YEAR  ")
    List<RepRevenueDailyMo> selectRevenueOfDay1(@Param("shopId") long shopId,
            @Param("revenueStartYear") Integer revenueStartYear, @Param("revenueStartDay") Integer revenueStartDay,
            @Param("revenueEndYear") Integer revenueEndYear, @Param("revenueEndDay") Integer revenueEndDay);

    /**
     * 当年份相同的时候使用这个方法，如果能合并与上面的方法请合并
     * @param shopId
     * @param revenueStartYear
     * @param revenueStartDay
     * @param revenueEndYear
     * @param revenueEndDay
     * @return
     */
    @Select("select * from REP_REVENUE_DAILY where  SHOP_ID  = #{shopId,jdbcType=TINYINT} and (YEAR =  #{revenueStartYear}  and DAY_OF_YEAR >= #{revenueStartDay} ) and ( YEAR =  #{revenueEndYear}  and DAY_OF_YEAR <=  #{revenueEndDay} )  order by YEAR ,DAY_OF_YEAR  ")
    List<RepRevenueDailyMo> selectRevenueOfDay2(@Param("shopId") long shopId,
            @Param("revenueStartYear") Integer revenueStartYear, @Param("revenueStartDay") Integer revenueStartDay,
            @Param("revenueEndYear") Integer revenueEndYear, @Param("revenueEndDay") Integer revenueEndDay);
}