package com.userdata.app.entities;

import javax.validation.constraints.NotBlank;

public class User {
    
    private long id;
    
    @NotBlank(message = "Email is mandatory")
    private String email;
    
    private String startTime;
    private String endTime;

    public User() {}

    public User(String email, String startTime, String endTime) {
        this.email = email;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    
    
    public void setEmail(String email) {
        this.email = email;
    }


    public String getEmail() {
        return email;
    }

    public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

}
