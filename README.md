# Argo for JaCaMo
In this tutorial you will learn how to import the Argo BDI-agent Architecture in a JaCaMo project and execute a Blink Project (_hello world_). 

### Dependencies:
1. JaCaMo Framework already installed.
	
	[![](https://raw.githubusercontent.com/wiki/chon-group/Argo/.imgs/jaCaMoFramework.png)](https://jason-lang.github.io/jason/jason-cli/readme.html)
	
	- [JaCaMo-CLI tutorial installation in Linux via _apt-get_ ](https://github.com/chon-group/dpkg-jacamo)
	- [JaCaMo-CLI tutorial other platforms](https://jacamo-lang.github.io/jacamo/install.html)

2. An IoT device using a serial communication channel, programmed using the Javino Blink Example.
	
	|Arduino|SimulIDE|
	|:-----:|:------:|
	|[![](https://raw.githubusercontent.com/wiki/chon-group/Argo/.imgs/arduinoBoard.png)]((Blink-Tutorial-with-Arduino-Board))|[![](https://raw.githubusercontent.com/wiki/chon-group/Argo/.imgs/arduinoSimulIDE.png)](Blink-Tutorial-with-SimulIDE)|
	| [Blink tutorial with Arduino Board](https://github.com/chon-group/Argo/wiki/Blink-Tutorial-with-Arduino-Board) | [Blink tutorial with SimulIDE](https://github.com/chon-group/Argo/wiki/Blink-Tutorial-with-SimulIDE) |

### Instructions:
1) In a terminal command, create a Multiagent System Project, using the command below:
	```
	jacamo app create jacamoWithArgo --console
	```

2) Include the Argo Package in the JaCaMo Project. Edit  the project file __jacamoWithArgo/jacamoWithArgo.jcm__  as follows:

	```
	mas jacamoWithArgo {
		agent bob: sample_agent.asl {
		ag-arch: jason.Argo
		}
		uses package: argo "com.github.chon-group:Argo:+"
	}
	```

3) Change the file __jacamoWithArgo/src/agt/sample_agent.asl__ including the content below:

	```
	/* Initial beliefs and rules */
	serialPort(ttyACM0).            /* physical Arduino Board in /dev/ttyACM0  */
	//serialPort(ttyUSB0).          /* physical Arduino Board in /dev/ttyUSB0  */
	//serialPort(ttyEmulatedPort0). /* simulated arduino with simulIDE         */

	/* Initial goals */
	!start.

	/* Plans */
	+!start:
	serialPort(Port) <- 
		.print("Ah, Mr. Anderson, I see you are as predictable in this world as you are in the other.");
		.argo.port(Port);
		.argo.percepts(open).

	+ledStatus(on) <-
		.print("Turning ON  the Led in Arduino!");
		.argo.act(ledOff).

	+ledStatus(off) <-
		.print("Turning OFF the Led in Arduino!");
		.argo.act(ledOn).

	+port(Port,Status):
	Status = off | Status = timeout <-
		.argo.percepts(close);
		.print("It's not over, Mr. Anderson! It's not over!").

	```

5) Execute the Multiagent System
	```
	jacamo jacamoWithArgo/jacamoWithArgo.mas2j
	```
### The expected output:

![](https://raw.githubusercontent.com/wiki/chon-group/Argo/.imgs/jaCaMoBlinkSimulIDE.png)

## Examples and Others
See the [Argo Wiki Page](https://github.com/chon-group/Argo/wiki)

## COPYRIGHT
<a rel="license" href="http://creativecommons.org/licenses/by/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by/4.0/88x31.png" /></a><br />The Argo Jacamo Package is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by/4.0/">Creative Commons Attribution 4.0 International License</a>. The licensor cannot revoke these freedoms as long as you follow the license terms:

* __Attribution__ — You must give __appropriate credit__ like below:

Lazarin, N.M., Pantoja, C.E., Viterbo, J. (2024). Dealing with the Unpredictability of Physical Resources in Real-World Multi-agent Systems. In: Rocha, A.P., Steels, L., van den Herik, J. (eds) Agents and Artificial Intelligence. ICAART 2023. Lecture Notes in Computer Science(), vol 14546. Springer, Cham. https://doi.org/10.1007/978-3-031-55326-4_3

<details>
<summary>BibTeX Citation</summary>


```
@InProceedings{argoJCMPackage,
	doi="10.1007/978-3-031-55326-4_3"
	author="Lazarin, Nilson Mori
	and Pantoja, Carlos Eduardo
	and Viterbo, Jos{\'e}",
	editor="Rocha, Ana Paula
	and Steels, Luc
	and van den Herik, Jaap",
	title="Dealing with the Unpredictability of Physical Resources in Real-World Multi-agent Systems",
	booktitle="Agents and Artificial Intelligence",
	year="2024",
	publisher="Springer Nature Switzerland",
	address="Cham",
	pages="48--71",
	isbn="978-3-031-55326-4"
}
```	
</details>