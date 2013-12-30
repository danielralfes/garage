package org.cresse.garage;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class RaspberryPiGarageService implements GarageService {

	private GpioController gpio = GpioFactory.getInstance();
	private GpioPinDigitalInput doorSensor;

	public RaspberryPiGarageService() {
		doorSensor = gpio.provisionDigitalInputPin(RaspiPin.GPIO_07, PinPullResistance.PULL_DOWN);
	}
	
	@Override
	public Garage getGarage() {
		DoorStatus status = mapStatus(doorSensor.getState());
		return new Garage(status);
	}

	@Override
	public void toggleGarageDoor() {
		throw new UnsupportedOperationException("Not Ready Yet");
	}

	private DoorStatus mapStatus(PinState state) {
		switch(state) {
			case HIGH: return DoorStatus.OPEN;
			case LOW: return DoorStatus.CLOSED;
			default: return DoorStatus.UNKNOWN;
		}
	}

}