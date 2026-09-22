# Entrega — Tuckersoft Branch Engine

## Implementación

Se implementó el backend Spring Boot 3.3.4 / Java 21 con PostgreSQL, JPA, BCrypt, JWT,
validación, manejo uniforme de errores y las cinco entidades requeridas.

Incluye:
- Registro, login, JWT y roles `ROLE_USER` / `ROLE_ADMIN`.
- `DataInitializer` para el administrador.
- CRUD de lectura/creación de nodos según permisos.
- Creación, aislamiento y recorrido de partidas.
- Motor determinista de clasificación de decisiones.
- Actualización de lucidez/control y finales.
- `DecisionCommittedEvent`.
- Listener separado con `@TransactionalEventListener(AFTER_COMMIT)`, `@Async` y `REQUIRES_NEW`.
- Envío real mediante `JavaMailSender` y auditoría en `RealityLog`.
- Simulación QA `X-Bandersnatch-Simulate: MAIL_FAILURE`.
- Cinco tests unitarios con Mockito para `DecisionService`.

## Autotests

No fue posible ejecutar los autotests en este entorno porque no hay Maven instalado y el
Maven Wrapper no pudo descargar Maven por falta de acceso de red. Por ello no se inventa
un resultado de estrellas.

Antes de entregar al TA:
1. Configurar PostgreSQL.
2. Crear `.env` a partir de `.env.example`.
3. Ejecutar `./mvnw spring-boot:run`.
4. En otra terminal ejecutar `cd autotests && ./mvnw test`.
5. Rellenar `equipo.json` con el equipo real y los tres códigos UTEC.

## Nota

La carpeta `autotests/` no fue modificada.
