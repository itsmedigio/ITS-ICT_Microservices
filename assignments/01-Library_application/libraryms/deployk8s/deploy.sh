kubectl create configmap book-conf --from-file ../book/src/main/resources/application.properties
kubectl create configmap borrow-conf --from-file ../borrow/src/main/resources/application.properties
kubectl create configmap customer-conf --from-file ../customer/src/main/resources/application.properties
kubectl create configmap notification-conf --from-file ../notification/src/main/resources/application.properties

kubectl apply -f deploy_book.yaml
kubectl apply -f deploy_borrow.yaml
kubectl apply -f deploy_customer.yaml
kubectl apply -f deploy_notification.yaml