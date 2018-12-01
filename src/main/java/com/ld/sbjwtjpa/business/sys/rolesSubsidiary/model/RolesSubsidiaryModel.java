package com.ld.sbjwtjpa.business.sys.rolesSubsidiary.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Data get/set
 * @NoArgsConstructor 无参构造器
 * @AllArgsConstructor 全参构造器
 */
@ApiModel(value = "RolesSubsidiaryModel", description = "角色与权限的对应关系")
@Entity(name = "roles_subsidiary_table")
@Table(comment = "角色与权限的对应关系", appliesTo = "roles_subsidiary_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class RolesSubsidiaryModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    @ApiModelProperty(name = "orgId", dataType = "String", required = true, allowableValues = "职位主键")
    @Column(name = "org_id", nullable = false)
    private String orgId;

    @ApiModelProperty(name = "jurId", dataType = "String", required = true, allowableValues = "权限主键")
    @Column(name = "jur_id", nullable = false)
    private String jurId;


    @Override
    public String toString() {
        return "RolesSubsidiaryModel{" +
                "uuid='" + uuid + '\'' +
                ", orgId='" + orgId + '\'' +
                ", jurId='" + jurId + '\'' +
                '}';
    }
}
