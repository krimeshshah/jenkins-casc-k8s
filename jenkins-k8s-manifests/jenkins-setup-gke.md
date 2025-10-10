
# Below Document Depicts on how to run/setup Jenkins on GKE autopilot cluser

## Pre-requisites 

   1. Available custom built jenkins image on gcp or other artifactory registr available over internet
    
### 1. Create GKE Cluster

#### For standard cluster
    
    gcloud container clusters create jenkins-cluster \
        --zone us-central1-a \
        --machine-type e2-standard-2 \
        --num-nodes 3 \
        --enable-ip-alias

#### GKE Auto pilot cluster

    gcloud container clusters create-auto jenkins-cluster \
        --region us-central1

### 2. Obtain kubeconfig to connect to kubectl to cluster

    gcloud container clusters get-credentials jenkins-cluster --zone us-central1-a

### 3. Create namespace for jenkins

   k create ns jenkins

### 4. Apply jenkins configuration file available in jenkins-k8s-manifest folder (i.e jenkins service, jenkins deployment)


## Configure ingress with gce ingressclass to access jenkins over internet

#### 1. Register a domain on domain provider site.

#### 2. Create Global IP  (i.e public IP) on gce 

     gcloud compute addresses create jenkins-ip --global   

    Note:  “Global” means public IP, it can be used with a global load balancer (like GKE Ingress on HTTP/HTTPS). It will be reserved at the project level and shows up under vpc network --> External IP

#### 3. Create A record on domain register site for the registered domain against the global IP created

#### 4. Create Ingress controller in GCP

      gcloud container clusters update <your-cluster> \
          --update-addons=HttpLoadBalancing=ENABLED

    Note: GKE Autopilot does not create an ingress controller by default. By enabling --addons=HttpLoadBalancing, GKE installs the GLBC (GCP HTTP Load Balancer controller), which works with Ingress objects of class gce, not nginx
    
#### 4. Crete Ingress resource.
        Ingress resource yaml file available in jenkins-k8s manifest file.


        k apply -f ingress.yaml

#####  Note: In gce, annotation for ingressclass resource, creates the ingress load balancer on gce which also creates neg to backend services to offload ther equest comming on load balancer.

#### 5. Backend config Resource

      create Backend config resource using gke-jenkins-backend-config.yaml file

      k apply -f gke-jenkins-backend-config.yaml

     
#####  Note: 
           1. In order to create and successfully work this backendconfig resource, make sure backendconfigs.cloud.google.com crd already exists on gke cluster. In case of gke auto pilot cluster this crd by default exists.

           2.This backend config resource is more specific to gke loadbalancer as loadbalancer has its own healthcheck resource which validates the readiness of backend service by testing the backend config resource.

    











