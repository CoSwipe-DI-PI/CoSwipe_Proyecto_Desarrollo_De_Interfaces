## 4.
### Documentacion
Se han documentado todas las clases públicas de la librería `Componentes`
(`com.dam.interfaces.componentes`): clase, constantes, constructores y métodos.

- Etiquetas usadas: `@author`, `@version`, `@since`, `@param`, `@return`, `@see`.
- En los métodos heredados (por ejemplo `addPropertyChangeListener`) se usa
  `@Override` junto con `{@inheritDoc}` para reutilizar la documentación de la clase padre.
- Generación: en NetBeans, clic derecho en el proyecto > **Generate Javadoc**
  (se crea `dist/javadoc/index.html`).

### BeanInfo
Cada componente tiene su clase `XxxBeanInfo` (por ejemplo `CampoPinBeanInfo`,
`BotonLikeDislikeBeanInfo`, `PanelEstadoSalaBeanInfo`,
`IndicadorParticipantesSalaBeanInfo`).

Sirven para que el componente se vea bien en el diseñador de NetBeans:
- Nombre legible e icono propio en la paleta.
- En *Properties* solo aparecen las propiedades propias del componente,
  no las decenas que hereda de `JPanel`.

El BeanInfo es opcional: sin él el componente funciona igual en ejecución,
solo es menos cómodo en el diseñador.

### Firma digital del .jar
El `.jar` se firma para garantizar quién lo ha creado y que no se ha modificado.

**Crear la clave (una vez)**
```
cd "C:\Program Files\Apache NetBeans\jdk\bin"
keytool -genkeypair -alias coswipe -keyalg RSA -keysize 2048 -validity 365 -keystore C:\claves\coswipe.jks
```
El almacén `coswipe.jks` se guarda fuera del repositorio.

**Firma automática en el build**
Al final de `build.xml`, justo antes de `</project>`:
```xml
<target name="-post-jar">
    <signjar jar="${dist.jar}"
             keystore="C:/claves/coswipe.jks"
             alias="coswipe"
             storepass="${firma.pass}"/>
</target>
```
La contraseña va en `nbproject/private/private.properties` (`firma.pass=...`),
que no se sube a GitHub.

**Comprobación**
Con **Clean and Build**, la ventana *Output* muestra `Signing JAR` y `BUILD SUCCESSFUL`.
Verificación: `jarsigner -verify dist\Componentes.jar` → `jar verified.`
El aviso "self-signed" es normal, porque el certificado es propio
