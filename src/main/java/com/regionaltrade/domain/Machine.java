package com.regionaltrade.domain;

import com.regionaltrade.enums.CriticalyLevel;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Cristian
 */
@Data
@Entity
@Table(name = "machine")
public class Machine implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String sector;
    private String task;
    private String description;
    private Boolean is_critical;
    @Enumerated(EnumType.STRING)
    private CriticalyLevel criticaly_level;//enum
    private Integer maintenance_frequency; 
    private LocalDate next_maintenance;
   
    
} 
