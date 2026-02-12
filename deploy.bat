@echo off
setlocal

mvn clean package -DskipTests
docker build -t app-ibk-weather-v1:latest .
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/hpa.yaml
kubectl apply -f k8s/monitor-service.yaml
kubectl apply -f k8s/redis-client.yaml
kubectl apply -f k8s/redis-deployment.yaml
kubectl apply -f k8s/redis-service.yaml
kubectl apply -f k8s/service.yaml

REM 5. Confirmar estado de pods
echo 🔍 Verificando estado de pods...
REM kubectl get pods

echo ✅ Script finalizado. Tus recursos estan desplegados nuevamente.

endlocal
pause