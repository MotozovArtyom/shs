package ru.rienel.shs.headcontroller.domain;

import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;

import ru.rienel.shs.headcontroller.domain.converter.UuidConverter;
import ru.rienel.shs.headcontroller.domain.converter.ZonedDateTimeConverter;

@Entity(name = "IndicationRecord")
@Table(name = "IndicationRecord")
@ToString(of = {"id", "recordUuid", "value", "recordDate", "device"})
public class IndicationRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "record_uuid", unique = true, nullable = false)
	@Convert(converter = UuidConverter.class)
	private UUID recordUuid;

	@Column(name = "value", precision = 10, scale = 3)
	private Double value;

	@Column(name = "record_date", nullable = false)
	@Convert(converter = ZonedDateTimeConverter.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private ZonedDateTime recordDate;

	@Column(name = "delta", precision = 10, scale = 3)
	private Double delta;

	@JoinColumn(name = "device", nullable = false)
	@ManyToOne(optional = false, targetEntity = ResourceMeter.class)
	private ResourceMeter device;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UUID getRecordUuid() {
		return recordUuid;
	}

	public void setRecordUuid(UUID recordUuid) {
		this.recordUuid = recordUuid;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public ZonedDateTime getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(ZonedDateTime recordDate) {
		this.recordDate = recordDate;
	}

	public Double getDelta() {
		return delta;
	}

	public void setDelta(Double delta) {
		this.delta = delta;
	}

	public ResourceMeter getDevice() {
		return device;
	}

	public void setDevice(ResourceMeter device) {
		this.device = device;
	}
}
