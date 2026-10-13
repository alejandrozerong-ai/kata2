# Kata 2: Entrada, salida y carga de datos

## 1. Objetivo de la entrega
El objetivo de esta kata es practicar el procesamiento de flujos de datos en Java mediante `InputStream` y `OutputStream`. Se busca separar claramente las fronteras técnicas entre la lectura de orígenes externos, la transformación de datos tabulares (CSV/TSV) en entidades de dominio inmutables (`record`), y la escritura de resúmenes verificables sin acoplar la lógica de negocio al soporte físico.

## 2. Versión de JDK y Dependencias
* **JDK:** OpenJDK 27 (o compatible con Java 17+)
* **Gestor de dependencias:** Maven
* **Librerías externas:** Ninguna (implementado puramente con la API estándar de Java: Streams, IO, Collections).

## 3. Estructura de la entrega y clases principales
El proyecto sigue una arquitectura modular y limpia:
* `src/main/java/software.ulpgc.kata2.Main`: Clase principal encargada de conectar el flujo de entrada (`InputStream`), la transformación de líneas a objetos de dominio y la escritura del resumen calculado a través de un `OutputStream`.
* `src/main/java/software.ulpgc.kata2.Movie`: Entidad de dominio inmutable modelada como un `record` de Java.
* `src/main/resources/movies.tsv`: Dataset tabular pequeño utilizado como recurso local de prueba.

## 4. Cómo compilar y ejecutar
Puedes compilar y ejecutar el proyecto desde la terminal integrada de IntelliJ (Mayús + F10 ó Ctrl + Shift + A y escribir "run") o tu terminal habitual mediante Maven:

```bash
# Compilar el proyecto
mvn clean compile exec:java

```

## 5. Enlace al vídeo explicativo

Enlace: https://youtu.be/yIiDfotYIYc?si=aWVZmvi0LPhGvr0Z


## 6. Proceso de repetición y variaciones introducidas

Siguiendo la dinámica de repetición deliberada para ganar soltura y reducir la fricción técnica, el desarrollo se estructuró de forma incremental mediante 6 commits clave en cada repetición en repositorios clonados a partir del proyecto original en IntelliJ IDEA:

Commit 1: Inicialización del proyecto y configuración del .gitignore para Java e IntelliJ.

Commit 2: Creación e incorporación del dataset pequeño en formato tabular (movies.tsv).

Commit 3: Modelado de la entidad de dominio inmutable (Movie record).

Commit 4: Implementación inicial de la lectura y parseo mediante BufferedReader y recurso local.

Commit 5: Desacoplamiento de la entrada para aceptar un InputStream genérico, demostrando que la misma lógica funciona con fuentes alternativas como ByteArrayInputStream en memoria.

Commit 6: Incorporación del cálculo de resumen mediante Java Streams (Collectors.groupingBy) y canalización de la salida a través de un OutputStream.

## 7. Evidencias de verificación y pruebas realizadas

Prueba con recurso local/fichero: Validación de la lectura correcta del dataset tabular omitiendo cabeceras y limpiando espacios.

Prueba con otro origen (Memoria): Comprobación de que la transformación opera de manera idéntica recibiendo un flujo simulado mediante ByteArrayInputStream.

Prueba de salida y resumen: Redirección de la salida mediante System.out y OutputStreamWriter mostrando agrupaciones exitosas (ej. recuento de películas por director).

Control de errores: Gestión de líneas vacías (line.isEmpty()) y validación de formatos para evitar fallos opacos en tiempo de ejecución.