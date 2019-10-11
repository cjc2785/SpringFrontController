package com.ss.lms.model;

public class Publisher {
    private Integer publisherId;
    private String publisherName;
    private String publisherAddress;
    private String publisherPhoneNumber;

    
    public Publisher() {};
    
    public Publisher(Integer pubId, String pubName, String pubAddress, String pubPhone) {
    	publisherId = pubId;
    	publisherName = pubName;
    	publisherAddress = pubAddress;
    	publisherPhoneNumber = pubPhone;
    }
    
    
    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherAddress() {
        return publisherAddress;
    }

    public void setPublisherAddress(String publisherAddress) {
        this.publisherAddress = publisherAddress;
    }

	public String getPublisherPhoneNumber() {
		return publisherPhoneNumber;
	}

	public void setPublisherPhoneNumber(String publisherPhone) {
		this.publisherPhoneNumber = publisherPhone;
	}
}
