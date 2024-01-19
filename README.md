Este repositorio es la entrega para la prueba técnica de acceso al proyecto, siguiendo las especificaciones y diseño entregadas.

El proyecto está estructurado siguiendo Clean Architecture y usando MVVM. Está estructurado en 4 módulos:

app -> Módulo aplicativo, que contiene la capa de presentación/UI
di -> Módulo Android Lib, contiene la inyección de dependencias para mantenerla segregada del módulo app y que este no tenga que tener visión más que sobre domain
domain -> Módulo Kotlin Lib, contiene la capa de negocio, interfaces de los repositorios a implementar y usecases.
data -> Modulo Android Lib, contiene la implementación de los repositorios de domain y los data sources necesarios. En este caso el API client.

Respecto a las tecnologías utilizadas, se han evitado 3rd parties que no sean de uso extendido y recomendado. Destacarían Glide para carga de imágenes y Retrofit2 + OkHttp3 + Gson para el cliente API y serialización de datos.
También se han utilizado dentro del entorno de Androidx / Jetpack: Hilt (DI), Paging3 (paginación de datos, control de estado de la vista), Navigation component, Splashscreen, y diferentes componentes de UI. También se ha utilizado el SDK de Google Maps de Android.

Por último, se han añadido algunos tests unitários para validar el comportamiento del Usecase existente.

Sobre las decisiones funcionales y de diseño:

Aunque se ha seguido al mayor detalle el prototipo entregado, se han tomado algunas consideraciones, tales como utilizar la fuente de sistema para los textos genéricos (dado que la empleada era la fuente de sistema de iOS), o no utilizar los iconos de flecha en el listado de contactos (>) por no considerarlos estándard de Android.
Sí se ha empleado la fuente Oswald empleada en los títulos de las páginas por considerarla parte del brand del hipotético proyecto.

Aunque originalmente se planteó seguir el estándar y tener una única toolbar en la Activity y sincronizarla con el Navigation component, finalmente y para poder tener un diseño personalizado de la misma se optó por que cada fragment utilice una propia. 

En la pantalla de listado de contactos, se ha añadido la opción de búsqueda de contenidos atendiendo a la definición, aunque cabe destacar que la API no provée de un endpoint para realizar una búsqueda de datos remotos según criterio de texto. Por contra, se realiza un filtrado local sobre los datos en memoria, y en caso de que los datos mostrados por pantalla hagan que la paginación cargue nuevos datos estos se seguirán lanzando en background.
Pese a no ser el comportamiento óptimo, se ha considerado que es el que da un resultado más cercano a la opción ideal, dado que, asumiendo que en un caso real la bbdd sería finita, sería la única opción para obtener todos los contactos que cumplan con el criterio de búsqueda.

Se han planteado algunos añadidos al diseño para cubrir casos posibles (Empty view si la API no devolviese datos, pantalla de error de carga en la obtención inicial de contactos, y footer de carga y de error en la obtención de nuevas páginas en el scroll infinito). También se ha añadido un SwipeRefreshLayout en la pantalla inicial.
En la pantalla de detalle de contacto, se ha considerado utilizar un CoordinatorLayout y un parallax para la imagen del header a falta de una indicación de otro tipo. 

Se ha considerado que la edición de datos / selección de foto estarían fuera de alcance dado que la API no permite modficaciones (Valoré añadir una segunda capa con persistencia de datos con Room para usar como SSOT y modificar los contactos aquí pero al final consideré que se alejaba demasiado del propósito original).
Finalmente sí se ha añadido el picker de imágenes de sistema, aunque luego no se procede con el proceso de media content y su obtención.

