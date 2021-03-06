package ru.rienel.shs.headcontroller.domain;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Stack;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;

import ru.rienel.shs.headcontroller.domain.converter.ZonedDateTimeConverter;

@Entity(name = "ResourceMeter")
@Table(name = "ResourceMeter")
@ToString(of = {"id", "serialNumber", "type", "addedTime"})
public class ResourceMeter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "serial_number", unique = true)
	private String serialNumber;

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private ResourceType type;

	// TODO Create verification converter
	@Column(name = "verification")
	private Stack<ZonedDateTime> verification;

	@Column(name = "added_time")
	@Convert(converter = ZonedDateTimeConverter.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private ZonedDateTime addedTime;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "ResourceMeterNeighbors",
			joinColumns = @JoinColumn(name = "neighbor_resource_meter_id"),
			inverseJoinColumns = @JoinColumn(name = "resource_meter_id"))
	private List<ResourceMeter> neighbors;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public ResourceType getType() {
		return type;
	}

	public void setType(ResourceType type) {
		this.type = type;
	}

	public Stack<ZonedDateTime> getVerification() {
		return verification;
	}

	public void setVerification(Stack<ZonedDateTime> verification) {
		this.verification = verification;
	}

	public ZonedDateTime getAddedTime() {
		return addedTime;
	}

	public void setAddedTime(ZonedDateTime addedTime) {
		this.addedTime = addedTime;
	}

	public List<ResourceMeter> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(List<ResourceMeter> neighbors) {
		this.neighbors = neighbors;
	}
}
