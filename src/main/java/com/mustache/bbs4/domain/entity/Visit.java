package com.mustache.bbs4.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper = true)
public class Visit extends BaseEntity{
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
