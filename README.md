# Prueba Técnica - Arquitectura MVVM

He planteado una arquitectura Clean desde cero,
utilizando como estructura de presentación MVVM con LiveData para la comunicación.

## Estructura
La estructura del proyecto está separada por los módulos:
- datarepository
- domaincontroller
- viewmodel
- app

![diagrama](https://i.postimg.cc/v87GcQZ4/diagrama.png)
Cada módulo cuenta con su interface para poder ser más escables e independientes.

## Requisitos
Se han cumplido los dos requisitos:
- Android Studio
- Kotlin

Se han utilizado las siguientes herramientas:
- Retrofit2: Para las llamadas a servicios web
- Koin: Como inyector de dependencias
- Navigator Component: Para gestionar las navegaciones
- SaveArgs: Para asegurar los argumentos entre vistas
- LifeCycle ViewModel
- LiveData
- BindViews
- Glide: Para la descarga de imágenes
- Lottie: Para mostrar aplicaciones

También se a creado un gestor de errores. 
