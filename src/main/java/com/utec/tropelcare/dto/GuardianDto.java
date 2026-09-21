package com.utec.tropelcare.dto; import java.time.Instant;
public record GuardianDto(Long id,String displayName,String email,String notificationEmail,Instant createdAt) {}
