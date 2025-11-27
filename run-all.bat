@echo off
echo Starting backend (Spring Boot)...
start cmd /k "cd my-workstation-backend && mvn spring-boot:run"

echo Waiting 5 seconds for backend to start...
timeout /t 5 >nul

echo Starting frontend (Vite React)...
start cmd /k "cd my-workstation-frontend && npm run dev"

echo Opening frontend in your browser...
start http://localhost:3000/

echo All services are running.
