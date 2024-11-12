# Trabajo Práctico - Backend de Aplicaciones
Trabajo Práctico del Grupo N124 de la materia Backend de Aplicaciones.
## Agencia de Autos - Año 2024
- Este repositorio contiene los proyectos de Spring Boot que constituyen un backend de microservicios para la aplicación.
- Los servicios se disponen mediante una API Gateway que corre por defecto en el puerto 8090.
#### Rutas disponibles (se debe agregar el prefijo /api/v1)
- Servicio ***"Agencia"*** (/agencia)
    - Vehículos
        - <code style="color : gold">POST</code> /vehiculos/posicion/new
    - Pruebas
        - <code style="color : greenyellow">GET</code> /pruebas
        - <code style="color : greenyellow">GET</code> /pruebas/:idPrueba
        - <code style="color : gold">POST</code> /pruebas/new
        - <code style="color : lightskyblue">PUT</code> /pruebas/finalizar/:idPrueba
        - <code style="color : orangered">DELETE</code> /pruebas/:idPrueba
    - Reportes
        - <code style="color : greenyellow">GET</code> /reportes/incidentes
        - <code style="color : greenyellow">GET</code> /reportes/incidentes/:idEmpleado
        - <code style="color : greenyellow">GET</code> /reportes/kilometros-vehiculo/:idVehiculo?fechaDesde={value}&fechaHasta={value}
        - <code style="color : greenyellow">GET</code> /reportes/detalle-pruebas/:idVehiculo
- Servicio ***"Notificaciones"*** (/notificaciones)
    - Críticas
        - <code style="color : greenyellow">GET</code> /notificaciones/seguridad/radio-excedido
        - <code style="color : greenyellow">GET</code> /notificaciones/seguridad/zona-peligrosa
    - Promociones
        - <code style="color : gold">POST</code> /notificaciones/promocion
        - <code style="color : greenyellow">GET</code> /notificaciones/promocion

## Extras
### Notificaciones SMS

<div style="display: flex; align-items: center;">
  <div style="flex: 1; padding-right: 10px;">
    Decidimos investigar sobre el envío de Notificaciones por SMS e implementamos una solución que utiliza la API de Twilio (líder en la industria para este tipo de necesidades) para notificar a los usuarios registrados (por el momento, una lista de números telefónicos que se configuran en una variable de entorno) de algún suceso.
<br>
La integración es muy sencilla, ya que Twilio provee un SDK que maneja todos los detalles, y nosotros solo solicitamos que se envíe un mensaje específico.
<br>
Utilizamos un plan gratuito de prueba que es apto para nuestras necesidades y que se puede actualizar fácilmente.
  </div>
  <div style="flex: 0 0 auto;">
    <img src="https://raw.githubusercontent.com/JcBordino4/tpBackend/refs/heads/master/notification.png" alt="IMAGEN SMS" style="width: 210px; height: auto;"/>
  </div>
</div>

### Comunicación entre Microservicios
Quisimos indagar más profundamente en este aspecto y consideramos que el caso de la comunicación entre el servicio de Agencia y el de Notificaciones, que sucede cuando se registra una posición de vehículo (que esté fuera de norma) y se debe generar una notificación, sería el **caso ideal para probar Apache Kafka** como tecnología de streaming de eventos en vez de un simple <code style="color : gold">POST</code> entre ambos microservicios, lo cual generaría un alto acoplamiento y sería totalmente bloqueante para el servicio de Agencia.
<br>
Al utilizar una tecnología como Kafka, el microservicio de Agencia se desliga totalmente de la responsabilidad del envío de notificaciones, que queda por completo delegada al servicio de Notificaciones.
<br>
El flujo es el siguiente:
1. Se recibe un <code style="color : gold">POST</code> para actualizar la posición del vehículo en el servicio de Agencia.
2. Se registra en la BD.
3. Se verifica si la posición infringe alguna restricción (obtenida de un web service externo).
    - Si -NO- se viola ninguna restricción:
        1. Fin del proceso.
    - Si -SÍ- se viola una restricción:
        1. El servicio de Agencia (publisher) publica un mensaje de Kafka en un tópico específico dependiendo del tipo de violación (área restringida o radio excedido).
        2. El servicio de Notificaciones (consumer) recibe el mensaje en alguno de los dos tópicos.
        3. El servicio de Notificaciones dispara el envío de la notificación por SMS y registra la notificación en la BD.

#### <u>Flujo estándar para el registro de una posición y su posterior notificación</u>
![Flujo kafka](https://raw.githubusercontent.com/JcBordino4/tpBackend/refs/heads/master/kafka.png)
Para implementar esta solución utilizamos un Broker de Kafka en la nube llamado _RedPanda_ que ofrece una tarifa gratuita de prueba y que satisface perfectamente nuestros requerimientos.
### Cacheo de Restricciones
Las restricciones sobre la posición de los vehículos se obtienen de un Web Service externo, provisto por la cátedra. Sin embargo, nos dimos cuenta de que este dato es prácticamente estático, son reglas de negocio por lo que no deberían cambiar muy frecuentemente.
<br>
Es por este motivo que nos pareció un despropósito tener que buscar este dato en el servidor externo cada vez que se recibe un <code style="color : gold">POST</code> para actualizar la posición del vehículo (probablemente varias veces por minuto).<br>Por esta razón, decidimos que la mejor opción sería cachear el valor y refrescarlo cada cierto tiempo de expiración configurable (5 minutos por defecto). Logramos esto usando la librería Caffeine de forma muy sencilla en Spring Boot:
```java
@Cacheable("restrictionsApiCache")
public RestriccionesDto getRestricciones() {
    return restTemplate.getForObject(urlRestricciones, RestriccionesDto.class);
}
```
## Requests de Ejemplo
Se presentan algunos ejemplos de las request mas complejas que soporta el sistema. Se debe tener encuenta que se necesita estar previamente autenticado y autorizado.
<hr>

> <code style="color : gold">POST</code> /api/v1/agencia/pruebas/new

Consulta para crear una nueva prueba para un vehiculo, empleado e interesado en especifico.
#### Body (JSON)
```
{
    "vehiculo": {
        "id": 6
        // El resto de campos pueden ser omitidos porque no son necesarios para crear un vehiculo.
    },
    "empleado": {
        "legajo": 4
    },
    "interesado": {
        "id": 3
    }
}
```

<hr>

> <code style="color : lightskyblue">PUT</code> /pruebas/finalizar/:idPrueba

Consulta para finalizar una prueba y agregar un comentario.
#### Body (JSON)
```
{
    "comentarios": "Prueba finalizada con cliente satisfecho."
}
```
<hr>

> <code style="color : gold">POST</code> /api/v1/agencia/vehiculos/posicion/new

Consulta para registrar la posicion actual de un vehiculo, verificar restricciones y notificar al empleado en caso de violacion.
#### Body (JSON)
<table>
  <tr>
    <th>Descripción</th>
    <th>JSON</th>
  </tr>
  <tr>
    <td><strong>Punto dentro de una zona restringida</strong></td>
    <td>
      <pre><code>{
  "vehiculo": { "id": 4 },
  "coordenadas": { "lat": 42.5088, "lon": 1.5370 }
}</code></pre>
    </td>
  </tr>
  <tr>
    <td><strong>Punto fuera del radio máximo</strong></td>
    <td>
      <pre><code>{
  "vehiculo": { "id": 4 },
  "coordenadas": { "lat": 42.5600, "lon": 1.5200 }
}</code></pre>
    </td>
  </tr>
  <tr>
    <td><strong>Punto autorizado</strong></td>
    <td>
      <pre><code>{
  "vehiculo": { "id": 4 },
  "coordenadas": { "lat": 42.5082, "lon": 1.5335 }
}</code></pre>
    </td>
  </tr>
</table>

