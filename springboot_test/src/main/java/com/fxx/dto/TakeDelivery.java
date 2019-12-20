package com.fxx.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 提货通知单表
 * </p>
 *
 * @author ouyangdengfeng
 * @since 2019-10-21
 */
public class TakeDelivery {

    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    private Long id;

    private String goodsBelong;

    private String serialNo;

    /**
     * 交易流水号
     */
    private String txnSrlNo;

    /**
     * 交易日期
     */
    private String txnDt;

    /**
     * 交易时间戳
     */
    private String txnTs;

    /**
     * 平台
     */
    private String platformCode;

    private String platformName;


    /**
     * 1:邦采贷;2:邦收贷
     */
    private String product;

    private Object productCN;

    /**
     * 来源
     */
    private String source;

    /**
     * 提货申请回执编号
     */
    private String receiptNo;


    /**
     * 借款合同编号
     */
    private String loanContractNo;

    /**
     * 借款合同名称
     */
    private String loanContractName;

    /**
     * 购销合同编号
     */
    private String tradeContractNo;

    /**
     * 四方协议编号
     */
    private String quartetAgreementNo;

    /**
     * 提货总重量
     */
    private BigDecimal takeDeliveryWeight;

    /**
     * 提货总重量
     */
    private Integer takeDeliveryQuantity;

    /**
     * 提货总金额
     */
    private BigDecimal takeDeliveryAmount;


    /**
     * 客户编号
     */
    private String customerCode;

    /**
     * 客户名称
     */
    private String customerName;


    /**
     * 银行开具回执时间
     */
    @NotNull(message = "银行开局回执时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receiptTime;

    /**
     * 回执附件
     */
    private String appendix;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 复核状态  1:待复核; 2:已复核;3:待提交;
     * 状态
     */
    private Integer status;

    /**
     * 开单状态 4:待开单;5已开单;6:部分开单;7:已作废
     */
    private Integer orderStatus;
}
