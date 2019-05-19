package ru.rienel.shs.headcontroller.service.rest;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.rienel.shs.headcontroller.domain.IndicationRecord;
import ru.rienel.shs.headcontroller.domain.ResourceMeter;
import ru.rienel.shs.headcontroller.domain.ResourceType;
import ru.rienel.shs.headcontroller.domain.dto.IndicationRecordDto;
import ru.rienel.shs.headcontroller.domain.dto.IndicationRecordMobileDto;
import ru.rienel.shs.headcontroller.domain.dto.converters.Converter;
import ru.rienel.shs.headcontroller.repository.IndicationRecordRepository;
import ru.rienel.shs.headcontroller.repository.ResourceMeterRepository;

@RestController
@RequestMapping("api/v1/record")
public class IndicationRecordRestService {
	private static final Logger log = LoggerFactory.getLogger(IndicationRecordRestService.class);

	@Autowired
	private IndicationRecordRepository indicationRecordRepository;

	@Autowired
	private ResourceMeterRepository resourceMeterRepository;

	@Autowired
	private Converter<IndicationRecord, IndicationRecordDto> converter;

	@Autowired
	private Converter<IndicationRecord, IndicationRecordMobileDto> converterMobile;


	@PostMapping("/add")
	public ResponseEntity receiveRecord(@RequestBody IndicationRecordDto recordDto) {
		ResourceMeter meter = resourceMeterRepository.findBySerialNumber(recordDto.getSerialNumber());
		if (meter == null) {
			log.warn("Get record from non-registered resource meter. Please check resource meter register record");
			return new ResponseEntity(HttpStatus.FORBIDDEN);
		}
		IndicationRecord record = converter.fromDto(recordDto);
		indicationRecordRepository.save(record);
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/")
	public List<IndicationRecordMobileDto> getForMobileClient() {
		List<IndicationRecordMobileDto> dtoList = new LinkedList<>();
		Iterable<IndicationRecord> queryResult = indicationRecordRepository.findAll();
		for (IndicationRecord record : queryResult) {
			dtoList.add(converterMobile.fromDomain(record));
		}
		return dtoList;
	}

	@GetMapping("/{type}")
	public Object getForMobileClientByResourceType(@PathVariable("type") ResourceType resourceType) {
		if (resourceType == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<IndicationRecordMobileDto> dtoList = new LinkedList<>();
		Iterable<IndicationRecord> queryResult = indicationRecordRepository.findAllByDevice_Type(resourceType);
		for (IndicationRecord record : queryResult) {
			dtoList.add(converterMobile.fromDomain(record));
		}
		return dtoList;
	}
}
