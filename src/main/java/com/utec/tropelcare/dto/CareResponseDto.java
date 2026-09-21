package com.utec.tropelcare.dto; import java.time.Instant;
public record CareResponseDto(Long id,Long signalId,String responseCode,String description,Instant createdAt) {}
