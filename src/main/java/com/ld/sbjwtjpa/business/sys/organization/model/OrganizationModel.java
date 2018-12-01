package com.ld.sbjwtjpa.business.sys.organization.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@ApiModel(value = "OrganizationModel", description = "组织机构表")
@Data
@Entity(name = "organization_table")
@Table(comment = "组织机构表", appliesTo = "organization_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganizationModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    /**
     * 组织机构名称
     */
    @ApiModelProperty(value = "组织机构名称", name = "orgName", dataType = "String", required = true, allowableValues = "组织机构名称")
    @NotBlank(message = "组织机构名称不能为空")
    @Size(max = 200, message = "组织机构名称最大长度为200位")
    @Column(name = "org_name", nullable = false)
    private String orgName;
    /**
     * 组织机构父级
     */
    @ApiModelProperty(value = "组织机构父级", name = "orgParent", dataType = "String", required = true, allowableValues = "组织机构父级,第一级用0标识")
    @NotBlank(message = "组织机构父级不能为空")
    @Size(max = 200, message = "组织机构父级最大长度为200位")
    @Column(name = "org_parent", nullable = false)
    private String orgParent;
    /**
     * 数据库中不创建该字段
     */
    @Transient
    private List<OrganizationModel> list = new ArrayList<>();

    @Version
    private Long version;

    @Override
    public String toString() {
        return "OrganizationModel{" +
                "uuid='" + uuid + '\'' +
                ", orgName='" + orgName + '\'' +
                ", orgParent='" + orgParent + '\'' +
                ", list=" + list +
                ", version=" + version +
                '}';
    }
}
