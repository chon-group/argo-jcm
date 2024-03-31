# Argo for JaCaMo
It's a [JaCaMo](https://github.com/jacamo-lang/jacamo) Package that provides a customized agent architecture that extends standard agents by adding the ability to control microcontrollers (Arduino) using [Javino](https://github.com/chon-group/javino).

## Creating _Hello Argo_ with JaCaMo
###	Dependencies
First, you need to deploy the Javino Blink example on your Arduino Board. Follow the steps below:

+ Download the latest [Javino Library](https://github.com/chon-group/javino2arduino/archive/refs/tags/javino-latest.zip).
+ Import the library into your Arduino IDE.
+ Create a new project with the Javino Blink example.
![arduino](https://github.com/chon-group/argo-jcm/assets/32855001/d5be0497-de49-46ab-8da5-f86c9db4a1da)
+ Deploy the code on your Arduino Board.


### Creating a JaCaMo Project
To install JaCaMo-CLI follow these instructions: [JaCaMo-CLI Installation](https://github.com/chon-group/dpkg-jacamo).

In a terminal, execute the following command:
```sh
jacamo app create hello_argo --console
```

#### Including the Argo Package in the JaCaMo Project
Edit  the project file __hello-argo/hello_argo.jcm__  as follows:

```
mas hello_argo {
    agent bob: sample_agent.asl {
      ag-arch: jason.Argo
    }
    uses package: argo "com.github.chon-group:argo-jcm:1.1"
}
```

Edit the agent file __hello-argo/src/agt/sample_agent.asl__ as follows:
```sh
/* Initial beliefs and rules */
serialPort(ttyACM0).

/* Initial goals */
!start.

/* Plans */
+!start:
serialPort(Port) <- 
	.print("Ah, Mr. Anderson, I see you are as predictable in this world as you are in the other.");
	argo.port(Port);
	argo.percepts(open).

+ledStatus(on) <-
	.print("Turning ON  the Led in Arduino!");
	argo.act(ledOff).

+ledStatus(off) <-
	.print("Turning OFF the Led in Arduino!");
	argo.act(ledOn).

+port(Port,Status):
Status = off | Status = timeout <-
	argo.percepts(close);
	.print("It's not over, Mr. Anderson! It's not over!").
```

### Running
```sh
sudo chmod 777 /dev/ttyACM0
jacamo hello-argo/hello-argo.jcm
```
![Tutoria-argo-jcm](https://github.com/chon-group/argo-jcm/assets/23249901/d6314cba-2669-4317-8379-c35882e375eb)

## COPYRIGHT
<a rel="license" href="http://creativecommons.org/licenses/by/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by/4.0/88x31.png" /></a><br />Argo is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by/4.0/">Creative Commons Attribution 4.0 International License</a>. The licensor cannot revoke these freedoms as long as you follow the license terms:

* __Attribution__ — You must give __appropriate credit__ like below:

Pantoja, C.E., Stabile, M.F., Lazarin, N.M., Sichman, J.S. (2016). ARGO: An Extended Jason Architecture that Facilitates Embedded Robotic Agents Programming. In: Baldoni, M., Müller, J., Nunes, I., Zalila-Wenkstern, R. (eds) Engineering Multi-Agent Systems. EMAS 2016. Lecture Notes in Computer Science(), vol 10093. Springer, Cham. [https://doi.org/10.1007/978-3-319-50983-9_8](https://www.researchgate.net/publication/311692258_ARGO_An_Extended_Jason_Architecture_that_Facilitates_Embedded_Robotic_Agents_Programming)

```
@InProceedings{10.1007/978-3-319-50983-9_8,
	author="Pantoja, Carlos Eduardo and Stabile, M{\'a}rcio Fernando and Lazarin, Nilson Mori and Sichman, Jaime Sim{\~a}o",
	editor="Baldoni, Matteo and M{\"u}ller, J{\"o}rg P. and Nunes, Ingrid and Zalila-Wenkstern, Rym",
	title="{ARGO: An Extended Jason Architecture that Facilitates Embedded Robotic Agents Programming}",
	booktitle="Engineering Multi-Agent Systems",
	year="2016",
	publisher="Springer International Publishing",
	address="Cham",
	pages="136--155",
	isbn="978-3-319-50983-9"
}
```	

## EXTRA
Some papers that have used Argo agents to control Arduino microcontrollers 

* [Swapping Physical Resources at Runtime in Embedded MultiAgent Systems](https://doi.org/10.5220/0011750700003393);
* [Integrating Embedded Multiagent Systems with Urban Simulation Tools and IoT Applications](https://doi.org/10.22456/2175-2745.110837);
* [Bio-Inspired Protocols for Embodied Multi-Agent Systems](https://doi.org/10.5220/0010257803120320);
