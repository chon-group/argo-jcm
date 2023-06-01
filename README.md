# argo-jcm
Argo for JaCaMo

## Dependencies
You need an Arduino Board with [Javino](https://github.com/chon-group/javino2arduino).

### Deploying the Javino Blink example in your board.
+ Download the latest [Javino Library](https://github.com/chon-group/javino2arduino/archive/refs/tags/javino-latest.zip).
+ Import to your Arduino IDE.
+ Create a new project with the Javino Blink example
![arduino](https://github.com/chon-group/argo-jcm/assets/32855001/d5be0497-de49-46ab-8da5-f86c9db4a1da)
+ Deploy the code


### Creating a JaCaMo Project
```sh
wget -q http://jacamo-lang.github.io/jacamo/nps/np1.2.zip
unzip -qq np1.2.zip 
./gradlew -Dexec.args="hello-argo --console"  
```

### Configuring Project
Edit  the project file __hello-argo/hello_argo.jcm__
```
mas hello_argo {
    agent bob: sample_agent.asl {
      ag-arch: jason.Argo
    }
    uses package: argo "com.github.chon-group:argo-jcm:1.0"
}
```

Edit the agent file __hello-argo/src/agt/sample_agent.asl__
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

### Importing Deps
```sh
cd hello-argo/
./gradlew buildJCMDeps
``` 

### Run
```sh
sudo chmod 777 /dev/ttyACM0
./gradlew run
```
![Captura de tela de 2023-06-01 10-25-53](https://github.com/chon-group/argo-jcm/assets/32855001/06ca7ece-86ff-4a19-8ed5-1251e89dc8f0)

