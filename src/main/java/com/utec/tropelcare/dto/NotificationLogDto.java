package com.utec.tropelcare.dto; import java.time.Instant;
public record NotificationLogDto(Long id,Long signalId,String recipientEmail,String subject,String notifStatus,String errorMessage,Instant sentAt,Instant createdAt) {}
