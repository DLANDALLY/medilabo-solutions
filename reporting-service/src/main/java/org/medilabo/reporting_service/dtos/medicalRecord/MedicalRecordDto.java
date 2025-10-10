package org.medilabo.reporting_service.dtos.medicalRecord;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class MedicalRecordDto {
    private Long patientId;
    private List<NoteHistoryDto> mhd;
}
