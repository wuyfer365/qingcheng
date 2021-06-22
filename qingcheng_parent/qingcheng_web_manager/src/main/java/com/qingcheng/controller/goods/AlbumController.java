package com.qingcheng.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.entity.ImageVo;
import com.qingcheng.entity.PageResult;
import com.qingcheng.entity.Result;
import com.qingcheng.pojo.goods.Album;
import com.qingcheng.service.goods.AlbumService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/album")
public class AlbumController {

    @Reference
    private AlbumService albumService;

    @GetMapping("/findAll")
    public List<Album> findAll(){
        return albumService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Album> findPage(int page, int size){
        return albumService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Album> findList(@RequestBody Map<String,Object> searchMap){
        return albumService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Album> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  albumService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Album findById(Long id){
        return albumService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Album album){
        String imageItems = "[{\"url\":\"%s\",\"uid\":\"%s\",\"status\":\"%s\"}]";
        imageItems=String.format(imageItems, album.getImage(),UUID.randomUUID(),"success");
        album.setImageItems(imageItems);
        albumService.add(album);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Album album){
        albumService.update(album);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(Long id){
        albumService.delete(id);
        return new Result();
    }
    @GetMapping("/deleteImage")
    public Result deleteImage(Long id,int index){
        System.out.println(id+"------------"+index);
        albumService.deleteImage(id,index);
        return new Result();
    }
    @GetMapping("/findImagesPage")
    public PageResult<ImageVo> findImagesPage(Long id, int page, int size) {
        return albumService.findImagesPage(id, page, size);
    }
    @PostMapping("/uploadImage")
    public Result uploadImage(@RequestBody Album album) {
        albumService.uploadImage(album);
        return new Result();
    }
}
