package com.utec.tropelcare.dto; import jakarta.validation.constraints.*;
public record SignalRequest(@NotNull Long tropelId,@NotNull Long guardianId,@NotBlank @Size(max=120) String senderTag,@NotBlank @Size(min=10) String rawContent) {}
