# jenkins-casc-k8s
This Repository contains Jenkins setup using casc running on K8S cluster

https://github.com/cnunciato/jenkins-jcasc-example/tree/main

Proper fix (use the agent):
Start the agent process separately on your VM and connect it to Jenkins. For example:

java -jar agent.jar -jnlpUrl http://<jenkins-url>:8080/computer/static-agent/jenkins-agent.jnlp -secret <agent-secret> -workDir "/home/jenkins"


You can grab the agent.jar from http://<jenkins-url>:8080/jnlpJars/agent.jar.

This will make your declared static-agent appear as “connected” in Jenkins and it will have 4 executors.

Docker trick:
If you want an agent inside the same container (not ideal for prod, fine for dev), you can install Java inside the Jenkins container and start agent.jar pointing back to itself.