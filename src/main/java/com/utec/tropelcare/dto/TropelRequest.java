package com.utec.tropelcare.dto; import jakarta.validation.constraints.*;
public record TropelRequest(@NotBlank @Size(min=2,max=40) String name,@NotBlank String species,@NotNull Long sectorId,@NotNull Long guardianId) {}
