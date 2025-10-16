FROM jenkins/jenkins:latest
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
ENV CASC_JENKINS_CONFIG /var/jenkins_home/casc.yaml
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
COPY dslJobs /usr/share/jenkins/ref/dslJobs
# Use jenkins-plugin-cli to install plugins
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

RUN sleep 10

COPY jenkins-config/jenkins-casc.yaml /usr/share/jenkins/ref/casc.yaml

