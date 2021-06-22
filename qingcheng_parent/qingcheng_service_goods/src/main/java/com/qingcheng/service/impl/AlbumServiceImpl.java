package com.qingcheng.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qingcheng.dao.AlbumMapper;
import com.qingcheng.entity.ImageVo;
import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Album;
import com.qingcheng.service.goods.AlbumService;
import com.qingcheng.service.goods.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.jar.JarEntry;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<Album> findAll() {
        return albumMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<Album> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Album> albums = (Page<Album>) albumMapper.selectAll();
        return new PageResult<Album>(albums.getTotal(),albums.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<Album> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return albumMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<Album> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Album> albums = (Page<Album>) albumMapper.selectByExample(example);
        return new PageResult<Album>(albums.getTotal(),albums.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public Album findById(Long id) {
        return albumMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param album
     */
    public void add(Album album) {
        albumMapper.insert(album);
    }

    /**
     * 修改
     * @param album
     */
    public void update(Album album) {
        albumMapper.updateByPrimaryKeySelective(album);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(Long id) {
        albumMapper.deleteByPrimaryKey(id);
    }

    public void deleteImage(Long id,int index) {
        Album albumOld = albumMapper.selectByPrimaryKey(id);
        String imageItems = albumOld.getImageItems();
        List<ImageVo> ts = (List<ImageVo>) JSONArray.parseArray(imageItems, ImageVo.class);
        ts.remove(index);
        String s = JSONArray.toJSONString(ts);
        albumOld.setImageItems(s);
        albumMapper.updateByPrimaryKeySelective(albumOld);
    }
    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 相册名称
            if(searchMap.get("title")!=null && !"".equals(searchMap.get("title"))){
                criteria.andLike("title","%"+searchMap.get("title")+"%");
            }
            // 相册封面
            if(searchMap.get("image")!=null && !"".equals(searchMap.get("image"))){
                criteria.andLike("image","%"+searchMap.get("image")+"%");
            }
            // 图片列表
            if(searchMap.get("imageItems")!=null && !"".equals(searchMap.get("imageItems"))){
                criteria.andLike("imageItems","%"+searchMap.get("imageItems")+"%");
            }


        }
        return example;
    }

    public PageResult<ImageVo> findImagesPage(Long id,int page, int size) {
        Album album = albumMapper.selectByPrimaryKey(id);
        String imageItems = album.getImageItems();

        List<ImageVo> ts = (List<ImageVo>) JSONArray.parseArray(imageItems, ImageVo.class);
        System.out.println(ts.size());
        System.out.println(Arrays.asList(ts));
        return new PageResult<ImageVo>((long)ts.size(), ts);
    }

    public void uploadImage(Album album) {
        Album albumOld = albumMapper.selectByPrimaryKey(album.getId());
        String imageItems = albumOld.getImageItems();
        String imageItem = ",{\"url\":\"%s\",\"uid\":\"%s\",\"status\":\"%s\"}";
        imageItem=String.format(imageItem, album.getImage(), UUID.randomUUID(),"success");
        StringBuffer stringBuilder1=new StringBuffer(imageItems);
        int index=stringBuilder1.indexOf("]");
        imageItems=stringBuilder1.insert(index,imageItem).toString();
        album.setImageItems(imageItems);
        albumMapper.updateByPrimaryKeySelective(album);
    }
}
