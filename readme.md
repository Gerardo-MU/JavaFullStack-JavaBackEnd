# Proyecto: ***BackEnd de Tienda Online***

**Diplomado en Programación Java:** Java BackEnd

**Insignia electrónica:** clic [aquí](https://www.acreditta.com/credential/09f099c3-ad68-4af8-bbca-8348f3a40db4)

---

## Objetivo
Desarrollar una aplicación Rest que imite el funcionamiento de un servidor el cual recibe información de una tienda online.

## Tecnologías utilizadas
- **Java Standart Edition**
- **Spring MVC**
- **Gradle** para la gestión del proyecto
- **Spring Data JPA** para la conexión de base de datos mediante consola H2
- **Junit 5** para pruebas

## Descripción

El proyecto consiste en una aplicación elaborada  Spring MVC, la estructura esta distribuida en seis capas:

- **cofig/** : Inicializa los datos de la aplicación usando @Bean y declara las excepciones usadas en la capa se servicios
- **controller/** : Define las rutas de los endpoint de cada uno de los controladores usados
- **exception/** : Declara las excepciones usadas en el proyecto y que heredan de la clase RuntimeException
- **model/** : Contiene las clases que definen los objetos que representan al cliente, los productos la orden de pago
- **persistence/** Define las interfaces que heredan de JpaRepository para establecer la conexión a la base de datos
- **service/**: Corresponde a la capa de servicios que define los métodos que llevan a cabo la lógica del programa

La información se obtiene por peticiones HTTP simulando que proviene de una aplicación externa, para ello se utilizo **Postman**
para probar cada una de las funcionalidades de la aplicación que consiste en en autentificar el usuario, registrar nuevos clientes y
registrar orden de pago. Los datos del usuario y de la orden se registran en una base de datos usando Spring Data JP y se puede acceder
mediante la consola H2. El proyecto implementa pruebas unitarias con Junit y Mokito para probar algunos métodos de la capa de servicios.


## Vista previa

La aplicacion se ejecuta sobre el enpoint **http://localhost:8080** en el cual llegarn las peticiones a los tres controladores desarrollados

![Aplicacion](https://raw.githubusercontent.com/Gerardo-MU/JavaFullStack-JavaBackEnd/refs/heads/master/public/screeshots/sc1.png)


**Login**

Para realizar el login de un cliente existente se usa el endpoint **/auth/login**

![Login](https://raw.githubusercontent.com/Gerardo-MU/JavaFullStack-JavaBackEnd/refs/heads/master/public/screeshots/sc2.png)


**Registro**

Para realizar un registro de nuevo cliente se usa el endpoint **/auth/register**

![Registro](https://raw.githubusercontent.com/Gerardo-MU/JavaFullStack-JavaBackEnd/refs/heads/master/public/screeshots/sc3.png)


**Confirmacion de orden**

Simulando que desde el browser el cliente ha hecho un pedido, la aplicación registra los datos de la orden usando el endpoint **/orders**

![Productos](https://raw.githubusercontent.com/Gerardo-MU/JavaFullStack-JavaBackEnd/refs/heads/master/public/screeshots/sc4.png)


**Base de datos**

La base de datos de la cual dispone de la consola H2 y se puede acceder mediante el endpoint **/h2-console** e ingresando las credenciales **admin** y **password**

![Productos](https://raw.githubusercontent.com/Gerardo-MU/JavaFullStack-JavaBackEnd/refs/heads/master/public/screeshots/sc5.png)


## 🔧 Instalación y ejecución

1. Clona el repositorio:

```bash
git clone https://github.com/Gerardo-MU/JavaFullStack-JavaBackEnd
```

2. Entra al directorio del proyecto:

```bash
cd JavaFullStack-JavaBackEnd
```

3. Instala las dependencias:

```bash
gradle build
```

4. Ejecuta el proyecto:

```bash
gradle bootrun
```
5. Prueba la aplicación

Abre [PostMan](https://www.postman.com/) e importa el archivo **BackEnd-de-Tienda-Online.postman_collection.json** situado en
**public/** y prueba cualquera de las peticiones POST
