package com.pdp.producer.entity;

public class EnvironmentalData {
	private double pm25=0;
	private double co2=0;
	private double temperature=0;
	private double humidity=0;
	private String time=null;

	public EnvironmentalData(double pm25, double co2, double temperature, double humidity, String time) {
		this.pm25 = pm25;
		this.co2 = co2;
		this.temperature = temperature;
		this.humidity = humidity;
		this.time = time;
	}
	
	

	public EnvironmentalData() {
		super();
		// TODO Auto-generated constructor stub
	}



	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getPm25() {
		return pm25;
	}

	public double getCo2() {
		return co2;
	}
	
	

	public void setPm25(double pm25) {
		this.pm25 = pm25;
	}



	public void setCo2(double co2) {
		this.co2 = co2;
	}



	@Override
	public String toString() {
		return "{ \"pm25\" : " + pm25 + ", \"co2\" : " + co2 + ", \"temperature\" : " + temperature + ", \"humidity\" : " + humidity
				+ ", \"time\" : \"" + time + "\" }";
	}

}
