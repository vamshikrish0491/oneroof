package com.acculytixs.wayuparty.dto.user;

public class VendorsDashboardCountsDTO {

	private Long bottleServiceCount;
	
	private Long tableServiceCount;
	
	private Long packagesServiceCount;
	
	private Long entryServiceCount;
	
	private Long surpriseServiceCount;
	
	private Long offersServiceCount;

	public Long getBottleServiceCount() {
		if(bottleServiceCount == null) {
			bottleServiceCount = 0l;
		}
		return bottleServiceCount;
	}

	public void setBottleServiceCount(Long bottleServiceCount) {
		this.bottleServiceCount = bottleServiceCount;
	}

	public Long getTableServiceCount() {
		if(tableServiceCount == null) {
			tableServiceCount = 0l;
		}
		return tableServiceCount;
	}

	public void setTableServiceCount(Long tableServiceCount) {
		this.tableServiceCount = tableServiceCount;
	}

	public Long getPackagesServiceCount() {
		if(packagesServiceCount == null) {
			packagesServiceCount = 0l;
		}
		return packagesServiceCount;
	}

	public void setPackagesServiceCount(Long packagesServiceCount) {
		this.packagesServiceCount = packagesServiceCount;
	}

	public Long getEntryServiceCount() {
		if(entryServiceCount == null) {
			entryServiceCount = 0l;
		}
		return entryServiceCount;
	}

	public void setEntryServiceCount(Long entryServiceCount) {
		this.entryServiceCount = entryServiceCount;
	}

	public Long getSurpriseServiceCount() {
		if(surpriseServiceCount == null) {
			surpriseServiceCount = 0l;
		}
		return surpriseServiceCount;
	}

	public void setSurpriseServiceCount(Long surpriseServiceCount) {
		this.surpriseServiceCount = surpriseServiceCount;
	}

	public Long getOffersServiceCount() {
		if(offersServiceCount == null) {
			offersServiceCount = 0l;
		}
		return offersServiceCount;
	}

	public void setOffersServiceCount(Long offersServiceCount) {
		this.offersServiceCount = offersServiceCount;
	}

	
	
	
	
}
