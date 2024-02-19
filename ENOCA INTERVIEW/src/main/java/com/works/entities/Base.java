package com.works.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class Base {
    private Boolean status;

    @JsonIgnore
    @CreatedBy
    private String createdBy;

    // ne zaman eklendi  @CreatedDate
    @JsonIgnore
    @CreatedDate
    private Long createdDate;

    //kim update etti   @LastModifiedBy
    @JsonIgnore
    @LastModifiedBy
    private String lastModifiedBy;

    @JsonIgnore
    @LastModifiedDate
    private Long lastModifiedDate;

}
