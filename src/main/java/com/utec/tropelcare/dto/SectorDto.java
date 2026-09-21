package com.utec.tropelcare.dto; import java.time.Instant;
public record SectorDto(Long id,String sectorCode,String climate,Integer capacity,Integer currentLoad,Integer stabilityLevel,Instant createdAt) {}
