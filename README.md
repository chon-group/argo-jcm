# argo-jcm
Argo for JaCaMo

### Creating a JaCaMo Project
```sh
wget -q http://jacamo-lang.github.io/jacamo/nps/np1.2.zip
unzip -qq np1.2.zip 
./gradlew -Dexec.args="hello-argo --console"  
```

### Configuring Project
Edit  the project file __hello-argo/hello_argo.jcm__
```
mas argo_hello {
    agent bob: sample_agent.asl
    uses package: argo "com.github.chon-group:argo-jcm:0.9-beta"
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
	.percepts(close);
	.print("It's not over, Mr. Anderson! It's not over!").
```

### Importing Deps
```sh
cd hello-argo/
./gradlew buildJCMDeps
``` 

### Run
```sh
./gradlew run
```
