package com.fn.rest;

import com.fn.domain.QiniuConfig;
import com.fn.domain.QiniuContent;
import com.fn.service.QiNiuService;
import com.fn.service.dto.QiniuQueryCriteria;
import lombok.extern.slf4j.Slf4j;
import com.fn.aop.log.Log;
import com.fn.domain.QiniuConfig;
import com.fn.domain.QiniuContent;
import com.fn.service.QiNiuService;
import com.fn.service.dto.QiniuQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 发送邮件
 * @author 郑杰
 * @date 2018/09/28 6:55:53
 */
@Slf4j
@RestController
@RequestMapping("api")
public class QiniuController {

    @Autowired
    private QiNiuService qiNiuService;

    @GetMapping(value = "/qiNiuConfig")
    public ResponseEntity get(){
        return new ResponseEntity(qiNiuService.find(), HttpStatus.OK);
    }

    @Log("配置七牛云存储")
    @PutMapping(value = "/qiNiuConfig")
    public ResponseEntity emailConfig(@Validated @RequestBody QiniuConfig qiniuConfig){
        qiNiuService.update(qiniuConfig);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("查询文件")
    @GetMapping(value = "/qiNiuContent")
    public ResponseEntity getRoles(QiniuQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(qiNiuService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    /**
     * 上传文件到七牛云
     * @param file
     * @return
     */
    @Log("上传文件")
    @PostMapping(value = "/qiNiuContent")
    public ResponseEntity upload(@RequestParam MultipartFile file){
        QiniuContent qiniuContent = qiNiuService.upload(file,qiNiuService.find());
        Map map = new HashMap(3);
        map.put("id",qiniuContent.getId());
        map.put("errno",0);
        map.put("data",new String[]{qiniuContent.getUrl()});
        map.put("qiniuContent",qiniuContent);
        return new ResponseEntity(map,HttpStatus.OK);
    }

    /**
     * 同步七牛云数据到数据库
     * @return
     */
    @Log("同步七牛云数据")
    @PostMapping(value = "/qiNiuContent/synchronize")
    public ResponseEntity synchronize(){
        log.warn("REST request to synchronize qiNiu : {}");
        qiNiuService.synchronize(qiNiuService.find());
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 下载七牛云文件
     * @param id
     * @return
     * @throws Exception
     */
    @Log("下载文件")
    @GetMapping(value = "/qiNiuContent/download/{id}")
    public ResponseEntity download(@PathVariable Long id){
        Map map = new HashMap(1);
        map.put("url", qiNiuService.download(qiNiuService.findByContentId(id),qiNiuService.find()));
        return new ResponseEntity(map,HttpStatus.OK);
    }

    /**
     * 删除七牛云文件
     * @param id
     * @return
     * @throws Exception
     */
    @Log("删除文件")
    @DeleteMapping(value = "/qiNiuContent/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        qiNiuService.delete(qiNiuService.findByContentId(id),qiNiuService.find());
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 删除多张图片
     * @param ids
     * @return
     */
    @Log("删除图片")
    @DeleteMapping(value = "/qiNiuContent")
    public ResponseEntity deleteAll(@RequestBody Long[] ids) {
        qiNiuService.deleteAll(ids, qiNiuService.find());
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 上传文件到七牛云
     * @param file
     * @return
     */
    @Log("上传文件")
    @PostMapping(value = "/qiNiuContentall")
    public ResponseEntity uploadall(@RequestParam MultipartFile[] file){
        List contetList = new ArrayList();

        for(MultipartFile file1: file){
            QiniuContent qiniuContent = qiNiuService.upload(file1,qiNiuService.find());
            Map context = new HashMap();
            context.put("attachment",qiniuContent.getUrl());
            context.put("name",qiniuContent.getKey());
            context.put("bucket",qiniuContent.getBucket());
            context.put("size",qiniuContent.getSize());
            context.put("type",qiniuContent.getType());

            contetList.add(context);

        }


        return new ResponseEntity(contetList,HttpStatus.OK);
    }
}
