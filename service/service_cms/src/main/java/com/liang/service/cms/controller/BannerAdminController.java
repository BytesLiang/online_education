package com.liang.service.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liang.common.utils.Result;
import com.liang.service.cms.entity.CrmBanner;
import com.liang.service.cms.entity.vo.BannerVo;
import com.liang.service.cms.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 后台管理首页banner表 前端控制器
 * </p>
 *
 * @author liang
 * @since 2022-07-29
 */
@Api(tags = "后台banner管理")
@RestController
@RequestMapping("/edu/cms/banner/admin")
@CrossOrigin
public class BannerAdminController {

    private final CrmBannerService bannerService;

    public BannerAdminController(CrmBannerService bannerService) {
        this.bannerService = bannerService;
    }

    @ApiOperation("分页查询")
    @GetMapping("/page/{current}/{limit}")
    public Result<Map<String, Object>> pageListBanner(@PathVariable long current,
                                                       @PathVariable long limit){
        Page<CrmBanner> bannerPage = new Page<>(current, limit);
        bannerService.page(bannerPage, null);
        Map<String, Object> map = new HashMap<>();
        map.put("total", bannerPage.getPages());
        map.put("rows", bannerPage.getRecords());
        return Result.success(map);
    }

    @ApiOperation("根据id查询")
    @GetMapping("/{id}")
    public Result<CrmBanner> getBanner(@PathVariable String id){
        return Result.success(bannerService.getById(id));
    }

    @ApiOperation("添加banner")
    @PostMapping("/add")
    public Result<Object> addBanner(@RequestBody CrmBanner crmBanner){
        return bannerService.save(crmBanner) ? Result.success() : Result.error();
    }

    @ApiOperation("修改banner")
    @PutMapping("/update")
    public Result<Object> updateBanner(@RequestBody BannerVo bannerVo){
        CrmBanner crmBanner = new CrmBanner();
        BeanUtils.copyProperties(bannerVo, crmBanner);
        return bannerService.updateById(crmBanner) ? Result.success() : Result.error();
    }

    @ApiOperation("删除banner")
    @DeleteMapping("/{id}")
    public Result<Object> removeBanner(@PathVariable String id){
        return bannerService.removeById(id) ? Result.success() : Result.error();
    }
}
