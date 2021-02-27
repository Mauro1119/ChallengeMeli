
# ChallengeMeli
Challenge técnico Java

Este proyecto es un Challenge técnico realizado en Java para presentar a Mercado Libre para su evaluación.
Es una Api que realiza el cálculo de la posición del envío y el descifrado del mensaje enviado a los diferentes satelites, según lo planteado en la propuesta recibida.

## Comenzando 

_Estas instrucciones te permitirán obtener una copia del proyecto y ejecutarlo localmente._


### Pre-requisitos 

* [IDE Spring Boot 4](https://spring.io/tools)
* [JDK 8](https://www.oracle.com/ar/java/technologies/javase/javase-jdk8-downloads.html)
* [Git Tools](https://git-scm.com/downloads)
* [Postman](https://www.postman.com/) _Opcional_ 

### Obtener fuentes desde GitHub

_Posicionarse en donde se desee descargar el proyecto._

```
git clone https://github.com/Mauro1119/ChallengeMeli
```

### Ejecutando el proyecto localmente. 

* Abrir Spring Boot 4 y seleccionar el workspace del proyecto descargado.


![seleccionworkspace](https://user-images.githubusercontent.com/35334417/109387613-2c3b7180-78e1-11eb-87a1-0ef5291ce9a4.png)

_Nota: esperar a que el proyecto se reconstruya._


* Ejecutar el proyecto.

![Ejecucion](https://user-images.githubusercontent.com/35334417/109387956-0747fe00-78e3-11eb-9a2a-dd62a3569e46.png)


* Invocar a los distintos Endpoints de la Api con Postman.

El proyecto cuenta con los endpoint documentados con Swagger, en donde se puede verificar la estructura json de los distintos endpoints.
Puede acceder traves de este [Link de Documentación Swagger](http://localhost:8080/swagger-ui.html)

_Ejemplo_

![postman](https://user-images.githubusercontent.com/35334417/109388498-4166cf00-78e6-11eb-931e-332a7356456c.png)


## Ejecutando las pruebas

Para ejecutar las pruebas proceder de la siguiente manera.

* Pruebas de módulo de calculo de posición.

Click derecho sobre el archivo PositionDecoderTest.java dentro del paquete src/test/java/com/meli/quasar, como se muestra en la imagen.

![pruebas](https://user-images.githubusercontent.com/35334417/109397269-b0f3b300-7914-11eb-806a-d2d930b53b70.png)

* Pruebas de módulo de descifrado del mensaje.

Click derecho sobre el archivo MessageDecoderTest.java dentro del paquete src/test/java/com/meli/quasar.

## Despliegue 

El sitio se encuentra desplegado en la dirección

tal

## Autor

**Mauro Marozzi** - *Trabajo Inicial* - [mauro1119](https://github.com/mauro1119)
_mauromarozzi@gmail.com_





