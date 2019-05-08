package ru.rienel.shsheadcontroller.domain.dto.converters;

import ru.rienel.shsheadcontroller.domain.IndicationRecord;
import ru.rienel.shsheadcontroller.domain.dto.IndicationRecordDto;

public class IndicationRecordConverter {
	public IndicationRecord fromDto(IndicationRecordDto dto) {
		IndicationRecord indicationRecord = new IndicationRecord();
		indicationRecord.setRecordUuid(dto.getRecordUuid());
		indicationRecord.setRecordDate(dto.getRecordDate());
		indicationRecord.setDelta(dto.getDelta());
		indicationRecord.setValue(dto.getValue());
		return indicationRecord;
	}

	public IndicationRecordDto toDto(IndicationRecord indicationRecord) {
		IndicationRecordDto dto = new IndicationRecordDto();
		dto.setRecordUuid(indicationRecord.getRecordUuid());
		dto.setRecordDate(indicationRecord.getRecordDate());
		dto.setDelta(indicationRecord.getDelta());
		dto.setValue(indicationRecord.getValue());
		return dto;
	}
}
