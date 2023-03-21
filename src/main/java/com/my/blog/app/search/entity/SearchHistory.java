package com.my.blog.app.search.entity;

import com.my.blog.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class SearchHistory extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "history_id")
    private long id;

    @Column(unique = true)
    private String keyword;

    @ColumnDefault("0")
    private int count;

    public void countUp() {
        this.count += 1;
    }
}
