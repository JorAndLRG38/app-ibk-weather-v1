@echo off
setlocal

REM Ruta a tu Dockerfile y a tus manifiestos de Kubernetes
set K8S_DIR=%~dp0k8s
set IMAGE_NAME=app-ibk-weather-v1
set IMAGE_TAG=latest
set DOCKERFILE_DIR=%~dp0Proyecto
set K8S_DIR=%~dp0k8s

echo 🚀 Iniciando despliegue automatico...

REM 1. Eliminar recursos previos en Kubernetes
echo 🧹 Eliminando recursos previos en Kubernetes...
kubectl delete -f %K8S_DIR% --ignore-not-found=true

REM 2. Aplicar nuevamente los manifiestos
echo 📦 Aplicando manifiestos de Kubernetes...
kubectl apply -f %K8S_DIR%

REM 3. Borrar contenedores locales de Docker
echo 🧹 Eliminando contenedores locales de Docker...
for /f %%i in ('docker ps -aq --filter "name=app-ibk-weather-v1:1.0"') do docker rm -f %%i
for /f %%i in ('docker ps -aq --filter "name=app-ibk-weather-v1:1.0.0"') do docker rm -f %%i
for /f %%i in ('docker ps -aq --filter "name=app-ibk-weather-v1:1.0.1"') do docker rm -f %%i

REM 4. Reconstruir la imagen Docker
echo 🔨 Construyendo imagen Docker...
docker build -t %IMAGE_NAME%:%IMAGE_TAG% .

REM 5. (Opcional) Borrar imagenes locales para forzar rebuild
REM ⚠️ Ten cuidado: esto borra todas las imagenes locales
REM for /f %%i in ('docker images -q') do docker rmi -f %%i

REM 5. Confirmar estado de pods
echo 🔍 Verificando estado de pods...
kubectl get pods

echo ✅ Script finalizado. Tus recursos estan desplegados nuevamente.

endlocal
pause