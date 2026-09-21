package com.utec.tropelcare.dto; import java.time.Instant;
public record TropelDto(Long id,String name,String species,String vitalState,Integer energyLevel,Integer chaosIndex,Integer mutationStage,Long sectorId,String sectorCode,Long guardianId,String guardianName,Instant createdAt,Instant updatedAt) {}
