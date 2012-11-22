/*
 * Created on 2012-11-22
 */
package com.osight.core.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import jxl.StringFormulaCell;

import com.osight.framework.pojos.PersistentObject;

/**
 * @author chenw
 * @version $Id$
 */
@Entity
@Table(name = "lottery_number")
public class LotteryNumberData extends PersistentObject {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "LOTTERY_ID", nullable = false)
    private long lotteryId;

    @Column(name = "IDX", nullable = false)
    private int index;

    @Column(name = "NUMBER", nullable = false)
    private int number;

    @Column(name = "NAME", nullable = true)
    private String name;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public long getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(long lotteryId) {
        this.lotteryId = lotteryId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("id:%s-lotteryId:%s-index:%s-number:%s-name:%s", id, lotteryId, index, number, name);
    }

}
