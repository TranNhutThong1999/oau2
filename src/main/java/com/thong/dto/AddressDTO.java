package com.thong.dto;

public class AddressDTO extends CommonDTO{
	private String ward;
	private String subAddress;
	private DistrictDTO district;
	public AddressDTO() {
		super();
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getSubAddress() {
		return subAddress;
	}
	public void setSubAddress(String subAddress) {
		this.subAddress = subAddress;
	}
	public DistrictDTO getDistrict() {
		return district;
	}
	public void setDistrict(DistrictDTO district) {
		this.district = district;
	}
	
	
}
