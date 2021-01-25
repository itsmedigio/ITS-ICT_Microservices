#  **Library Microservice Assignment by Davide Di Giovanni**

In this repo, you can see my solution to the final exam **assignment made by Denis Maggiorotto** for the Microservices class, with some explainations.

[Check assignment description page](https://github.com/sunnyvale-academy/ITS-ICT_Microservices/blob/master/assignments/01-Library_application/README.md "Before starting, check the assignment description page")

The assignment consist in a series of Microservices that permits to manage a library.
Specifically, you can call the APIs of this services by sending requests using Postman or similar software to this endpoints:

[http://localhost:8102/libraryms/v2/book](http://localhost:8102/libraryms/v2/book "http://localhost:8102/libraryms/v2/book")

[http://localhost:8103/libraryms/v2/borrow](http://localhost:8103/libraryms/v2/borrow "http://localhost:8103/libraryms/v2/borrow")

[http://localhost:8104/libraryms/v2/customer](http://localhost:8104/libraryms/v2/customer "http://localhost:8104/libraryms/v2/customer")

You can add, edit or remove books, customers and borrowings

There is also a Notification Service running at `localhost:8105` that runs on JDK8 (everything else runs on OpenJDK11)

#### Properties of Book
bookId, bookName, bookDescription (all Strings)

#### Properties of Borrow
borrowId, bookId, customerId, notifyToPhoneNr (all Strings)

#### Properties of Customer
customerId, customerName, customerSurname, customerPhone

## Running my service under Docker Compose (suggest increasing to 8GB of RAM in Docker Desktop configuration)

Before running, add `127.0.0.1 libraryms-mongodb kafka` to your /etc/hosts

There are some .sh files on my project as you can see, and they do what their name suggest. 
You can start the microservices and base infrastructure bu using ./start-libraryms.sh
This script will also do the Unit and Integration tests before starting. 
When it finishes loading (it takes a while), it will start displaying logs to the console, and if you CTRL+C it will gracefully stop.
There is also ELK infrastructure in the folder logging, and you can start that with ./start.sh

Elastic will run at [localhost:5601](localhost:5601 "localhost:5601")

username: elastic
password: changeme

To see the logs click on the sidebar, go on observability logs, then settings, then put
`logs-*,filebeat-*,kibana_sample_data_logs*,logstash*` and save.

## Deploying my service under Kubernetes

**On MacOS be sure to enable port forwarding using** `sudo sysctl -w net.inet.ip.forwarding=1`
You will need Helm and a Kubernetes installation (The one from Docker is okay)

Open the **helm** folder and run ./deploy.sh [(What does this?)](https://github.com/sunnyvale-academy/ITS-ICT_Microservices/blob/master/labs/06-Install_infrastructure_components_on_K8S/README.md "(What does this script?)")

Running ./delete.sh will remove everything created from the script above.

You will see your containers creating by doing `kubectl get pods`

**Microservices in kubernetes and ArgoCD will run on localhost:32102,32103,32104,32105!**

**To start Kibana, use kubectl port-forward svc/kibana --address 0.0.0.0 5601:5601 and connect to localhost:5601**

Then go back and open **deployk8s** folder and run ./deploy.sh [(What does this?)](https://github.com/sunnyvale-academy/ITS-ICT_Microservices/tree/master/labs/07-Deploy_microservices_on_K8S "(What does this?)")

Once again, run ./delete.sh to remove everything.

**Note:** You need to run this delete to proceed to the next step

## Deploying my service using ArgoCD 

**Requirement:** you need to install ArgoCD CLI first!

To install ArgoCD on Kubernetes run this commands:
`kubectl create ns argocd` (to create its namespace)

`kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml` to deploy it

`kubectl get all -n argocd` to check its status

To access its web UI you need to open **another terminal window** and run on it `kubectl port-forward svc/argocd-server --address 0.0.0.0 -n argocd 4000:443` **without closing it** until you have finished working with it. 

You can now access ArgoCD via [localhost:4000](localhost:4000 "localhost:4000")

username: admin

To get the password, open a terminal and paste this command: `kubectl get po -n argocd | grep argocd-server | cut -d " " -f 1`The output will be your password.

To setup your app then open a terminal window, login to the CLI using `argocd login localhost:4000 --insecure --username admin --grpc-web  --password $(kubectl get po -n argocd | grep argocd-server | cut -d " " -f 1)` and then create the app like so:



    argocd app create libraryms \
        --repo https://github.com/itsmedigio/ITS-ICT_Microservices.git \
        --path assignments/01-Library_application/libraryms/deployk8s/ \
        --dest-server https://kubernetes.default.svc \
        --dest-namespace default \
        --auto-prune \
        --self-heal \
        --sync-policy auto \
        --sync-option CreateNamespace=true \
        --revision master

To remove this, run`argocd app delete libraryms --cascade`
