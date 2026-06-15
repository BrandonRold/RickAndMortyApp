# Rick & Morty App - Android Professional Portfolio

![Android](https://img.shields.io/badge/Platform-Android-brightgreen.svg)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-orange.svg)
![Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-blue.svg)
![Clean Architecture](https://img.shields.io/badge/Architecture-Clean%20%2B%20MVVM-red.svg)

## 1. Instrucciones de Instalación
1. **Requisitos:**
    - Android Studio Ladybug o superior.
    - JDK 17.
    - SDK de Android 34+.

2. **Clonación y Ejecución:**
   ```bash
   git clone https://github.com/brandonroldan/RickAndMortyApp.git
   cd RickAndMortyApp
   ```
3. Abrir el proyecto en Android Studio.
4. Sincronizar Gradle.
5. Ejecutar en un emulador o dispositivo físico.

---


## 2. Descripción General
**Rick & Morty App** es una aplicación de Android moderna diseñada para demostrar la implementación de una arquitectura robusta, escalable y mantenible. La aplicación consume la API pública de Rick and Morty para listar personajes, ver sus detalles y gestionar una lista de favoritos persistente localmente.

### Problema que resuelve
El proyecto sirve como una implementación de referencia para aplicaciones que requieren:
- Consumo de APIs RESTful.
- Persistencia de datos local (Offline-first / Cache).
- Manejo de estados de UI complejos (Carga, Error, Éxito).
- Navegación moderna y reactiva.

### Funcionalidades
- **Listado de Personajes:** Visualización de todos los personajes con carga optimizada.
- **Búsqueda Avanzada:** Filtrado de personajes por nombre tanto en la API como en favoritos.
- **Detalle del Personaje:** Información exhaustiva de cada individuo.
- **Gestión de Favoritos:** Posibilidad de marcar personajes para verlos sin conexión mediante una base de datos local.
- **Paginación:**  `Paging 3` para el listado infinito de personajes.
---

## 3. Arquitectura del Proyecto
Se ha implementado una arquitectura basada en **Clean Architecture** junto con el patrón de diseño **MVVM (Model-View-ViewModel)**.

### Capas del Proyecto
- **Data:** Contiene la implementación de los repositorios, las fuentes de datos (Retrofit para red y Room para local) y los DTOs (Data Transfer Objects).
- **Domain:** Es la capa más interna y contiene las reglas de negocio. Aquí residen los **Use Cases**, las entidades puras de dominio y las interfaces de los repositorios (siguiendo el principio de inversión de dependencia).
- **Presentation:** Implementada con **Jetpack Compose**. Contiene los ViewModels que gestionan el estado de la UI mediante **StateFlow** y los componentes visuales.

### Flujo de Datos
1. La UI observa un `StateFlow` expuesto por el `ViewModel`.
2. El `ViewModel` invoca un `UseCase`.
3. El `UseCase` solicita datos al `Repository`.
4. El `Repository` decide si obtener los datos de la red (`RemoteDataSource`) o de la base de datos (`LocalDataSource`), realiza el mapping a entidades de dominio y devuelve el resultado.

---

## 4. Decisiones Técnicas y Justificación

| Tecnología | Justificación | Alternativas |
| :--- | :--- | :--- |
| **Jetpack Compose** | UI declarativa que reduce el código boilerplate y mejora la velocidad de desarrollo. | XML (View System) - Descartado por ser imperativo y más propenso a errores de estado. |
| **Hilt (Dagger)** | Inyección de dependencias estándar de la industria que facilita las pruebas unitarias y el desacoplamiento. | Koin - Aunque es más simple, Hilt ofrece validación en tiempo de compilación y mejor integración con el ecosistema Android. |
| **Retrofit + Moshi** | Retrofit es el estándar para redes. Moshi es preferido sobre GSON por su mejor soporte para Kotlin (null-safety y Reflection). | Ktor Client - Excelente, pero Retrofit tiene una integración más madura con OkHttp y convertidores. |
| **Room** | Abstracción sobre SQLite que permite persistencia robusta con soporte nativo para Flow/Coroutines. | Realm / SQLDelight - Room es la solución oficial recomendada con excelente soporte de Google. |
| **StateFlow** | Manejo de estado reactivo y consciente del ciclo de vida. | LiveData - Descartado por ser una tecnología más antigua y menos potente para flujos de datos complejos. |

---

## 5. Estructura de Carpetas
```text
com.brs.rickyandmorthy/
├── core/                # Utilidades compartidas y constantes
├── data/                # Implementación de datos
│   ├── local/           # Room Database, DAOs, Entidades
│   ├── remote/          # Retrofit API, DTOs
│   └── repository/      # Implementación de interfaces de dominio
├── domain/              # Reglas de negocio (Independiente de Android)
│   ├── model/           # Entidades de dominio
│   ├── repository/      # Interfaces de repositorios
│   └── usecase/         # Casos de uso específicos
├── presentation/        # Capa de UI
│   ├── feature/         # Pantallas organizadas por funcionalidad
│   │   ├── character/   # Listado y Detalle
│   │   └── favorites/   # Listado de favoritos
│   ├── navigation/      # Configuración de Compose Navigation
│   └── common/          # Componentes de UI reutilizables
└── di/                  # Módulos de Hilt (Dependency Injection)
```

---



## 6. API Utilizada
Se utiliza la [Rick and Morty API](https://rickandmortyapi.com/).

- **Base URL:** `https://rickandmortyapi.com/api/`
- **Endpoints principales:**
    - `/character`: Obtener lista de personajes.
    - `/character/{id}`: Obtener un personaje único.

---


---

## 7. Créditos
- **Autor:** Brandon Roldán
- **Tecnologías:** Kotlin, Compose, Hilt, Room, Retrofit, Coroutines, Flow.

---
