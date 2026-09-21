package com.utec.tropelcare.dto; import java.time.Instant; import java.util.List;
public record DiaryDto(Long tropelId,String tropelName,List<NoteDto> notes){ public record NoteDto(Long signalId,String personalityNote,Instant createdAt){} }
