package ru.rienel.shs.headcontroller.repository;

import org.springframework.data.repository.CrudRepository;

import ru.rienel.shs.headcontroller.domain.ResourceBill;

public interface ResourceBillRepository extends CrudRepository<ResourceBill, Long> {
	ResourceBill findBySerialNumber(String serialNumber);
}
