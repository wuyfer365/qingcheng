package com.qingcheng.pojo.order;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Table(name = "tb_category_report")
public class CategoryReport implements Serializable {
    @Id
    private Integer categoryId1;
    @Id
    private Integer CategoryId2;
    @Id
    private Integer CategoryId3;
    @Id
    private Date countDate;
    private Integer num;
    private Integer money;

    public Integer getCategoryId1() {
        return categoryId1;
    }

    public void setCategoryId1(Integer categoryId1) {
        this.categoryId1 = categoryId1;
    }

    public Integer getCategoryId2() {
        return CategoryId2;
    }

    public void setCategoryId2(Integer categoryId2) {
        CategoryId2 = categoryId2;
    }

    public Integer getCategoryId3() {
        return CategoryId3;
    }

    public void setCategoryId3(Integer categoryId3) {
        CategoryId3 = categoryId3;
    }

    public Date getCountDate() {
        return countDate;
    }

    public void setCountDate(Date countDate) {
        this.countDate = countDate;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
