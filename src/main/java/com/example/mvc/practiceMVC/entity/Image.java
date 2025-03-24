package com.example.mvc.practiceMVC.entity;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class Image {
    private UUID id;
    private String originalName;
    private String path;
    private Long size;
    private Instant uploadedAt;
}
