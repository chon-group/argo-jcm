/* Initial beliefs and rules */
serialPort(ttyUSB0).

/* Initial goals */

!start.

/* Plans */

+!start:
serialPort(Port) <- 
	.print("Ah, Mr. Anderson, I see you are as predictable in this world as you are in the other.");
	.port(Port);
	.percepts(open).

+ledStatus(on) <-
	.print("Turning ON  the Led in Arduino!");
	.act(ledOff).

+ledStatus(off) <-
	.print("Turning OFF the Led in Arduino!");
	.act(ledOn).

+port(Port,Status):
Status = off | Status = timeout <-
	.print("It's not over, Mr. Anderson! It's not over!").