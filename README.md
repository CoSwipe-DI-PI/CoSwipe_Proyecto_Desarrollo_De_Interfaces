# Pruebas unitarias — CoSwipe

Las pruebas se realizan con **JUnit 5** sobre `ModeloApp.java` para comprobar el funcionamiento de sus métodos y propiedades.

## Pruebas realizadas

| Prueba | Qué comprueba |
|---|---|
| `comprobarValoresIniciales()` | Comprueba los valores iniciales de idioma, código de sala, participantes y categoría. | 
| `comprobarCambioIdioma()` | Cambia el idioma mediante `setIdioma()`. | 
| `comprobarNuevoParticipante()` | Añade un participante mediante `nuevoUsuarioConectado()`. | 
| `comprobarPinVacio()` | Comprueba que no se acepten PIN nulos, vacíos o con espacios. | 
| `comprobarPinValido()` | Comprueba que se acepta un PIN con contenido. | 
| `comprobarCambioParticipantes()` | Modifica el número de participantes. | 
| `comprobarCambioCategoria()` | Cambia la categoría seleccionada. | 

## Posibles resultados

- **SUCCESSFUL:** La prueba se ejecuta y el resultado coincide con el esperado.
- **FAILED:** El resultado es incorrecto o se produce una excepción durante la prueba.
- **SKIPPED:** La prueba no se ha ejecutado.
- **ABORTED:** La prueba se ha interrumpido por una condición de ejecución.
- **BUILD SUCCESSFUL:** Todas las tareas de Ant han finalizado correctamente.
- **BUILD FAILED:** Se ha producido algún error de compilación, configuración o ejecución de las pruebas.

## Ejecución

En `build.xml` click derecho, `Run Target`, seleccionar `qa-test`. Si no aparece `qa-test`: selecciona `Other Targets` y ahí tendrá que aparecer.

