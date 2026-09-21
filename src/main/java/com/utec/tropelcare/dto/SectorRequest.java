package com.utec.tropelcare.dto; import jakarta.validation.constraints.*;
public record SectorRequest(@NotBlank @Size(max=80) String sectorCode,@NotBlank String climate,@NotNull @Positive Integer capacity) {}
