package com.mustache.bbs4.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="hospital_id")
    private Hospital hospital;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    private String disease;
    private float amount;
}
