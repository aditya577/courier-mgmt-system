package com.speedxcourier.SpeedX.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "package_history")
@Data
@Builder
@AllArgsConstructor
public class PackageHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package.id")
    private Package package_;

    private String bookEvent;
    private String eventFrom;
    private String eventTo;
    private String otherDetails;
    private Date createdAt;
}
