@echo off
where mvn >nul 2>nul
if errorlevel 1 (
  echo Maven no esta instalado. Instala Maven 3.9+ o ejecuta el proyecto desde un IDE con Maven integrado.
  exit /b 1
)
mvn %*
