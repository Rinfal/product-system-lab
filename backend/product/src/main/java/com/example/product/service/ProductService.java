package com.example.product.service;

import com.example.product.exception.ProductErrorCode;
import com.example.product.mapper.CategoryMapper;
import com.example.product.mapper.ProductMapper;
import com.example.product.pojo.PojoConverter;
import com.example.product.pojo.dto.ProductDto;
import com.example.product.pojo.vo.ProductVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service //标记为spring的bean
public class ProductService implements IProductService {

    @Autowired
    private PojoConverter pojoConverter;

    @Resource
    private ProductMapper productMapper;
    @Resource
    private CategoryMapper categoryMapper;


    @Override
    public String add(ProductDto product) {

        product.setCreateTime(LocalDateTime.now());
        product.setCreatedBy("admin");
        product.setDeletedFlag(false);
        product.setUpdateTime(LocalDateTime.now());
        product.setUpdatedBy("admin");

       productMapper.insert(product);
       return "Success";
        //插入后端数据
        // 调用数据访问类
    }

    //查询
    @Override
    public PageInfo<ProductVo> search(ProductDto productDto, Integer pageNum, Integer pageSize) {
        //System.out.println(productQueryDto);
        PageHelper.startPage(pageNum, pageSize);
        List<ProductVo> list = pojoConverter.DtotoVoList(productMapper.search(productDto));

        return new PageInfo<>(list);


    }

    @Override
    //@Cacheable(cacheNames = "productVo", key="All",unless ="#result==null")
    public List<ProductVo> getAll() {
        return pojoConverter.DtotoVoList(productMapper.getAll());
    }

    @Override
    public PageInfo<ProductVo> getPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize); // 设置分页参数

        List<ProductVo> list = pojoConverter.DtotoVoList(productMapper.getAll()); // 不需要写分页SQL
        return new PageInfo<>(list); // 封装成 PageInfo，包含总条数等
    }

    @Override
    @CacheEvict(cacheNames = "productDto", key = "#productDto.productId")
    public String update(ProductDto productDto){
        //先这么写吧
        ProductErrorCode.UPDATE_NULL.assertNotVoid(productDto.getProductName());
        ProductErrorCode.UPDATE_NULL.assertNotVoid(productDto.getPrice());
        ProductErrorCode.UPDATE_NULL.assertNotVoid(productDto.getCategoryId());
        ProductErrorCode.UPDATE_NULL.assertNotVoid(productDto.getMemberPrice());
        ProductErrorCode.UPDATE_NULL.assertNotVoid(productDto.getProductNote());
        ProductErrorCode.UPDATE_NULL.assertNotVoid(productDto.getOriginPlace());
        ProductErrorCode.UPDATE_NULL.assertNotVoid(productDto.getShippingPlace());
        ProductErrorCode.UPDATE_NULL.assertNotVoid(productDto.getStock());
        productDto.setUpdateTime(LocalDateTime.now());
        productMapper.update(productDto);
        return "updateSuccess";
    }


    @Override
    @CacheEvict(cacheNames = "productDto", key = "#productId")
    public String delete(String productId) {
        //插入后端数据
       productMapper.delete(productId);//可以自动判断，无ID就新增，有ID就修改
        return "delete success";
        // 调用数据访问类
    }

    @Override
    @Cacheable(cacheNames = "productDto", key="#productId",unless ="#result==null")
    public ProductDto getById(String productId){
        ProductDto productDto = productMapper.getById(productId);

        return productDto;
    }

    //感觉可以和Update合并一下，以后弄吧
    @Override
    @CacheEvict(cacheNames = "productDto", key = "#productId")
    public void reduceStock(String productId, Integer stock) {
        ProductDto productDto = productMapper.getById(productId);
        productDto.setStock(productDto.getStock() - stock);
        productMapper.update(productDto);
    }

    @CacheEvict(cacheNames = "productDto", key = "#productId")
    @Override
    public void releaseStock(String productId, Integer numRelease) {
        ProductDto productDto = productMapper.getById(productId);
        productDto.setStock(productDto.getStock() + numRelease);
        productMapper.update(productDto);
    }







}
