package com.dj.app.dto;

public class EquipmentsDto {

	private Integer equipmentId;
	private String equipmentName;

	public EquipmentsDto() {
	}

	public EquipmentsDto(Integer equipmentId, String equipmentName) {
		this.equipmentId = equipmentId;
		this.equipmentName = equipmentName;
	}

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
}
