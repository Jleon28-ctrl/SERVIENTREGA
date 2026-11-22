# 📦 Sistema de Envíos - Servientrega (Prueba Técnica Java)

Este proyecto es una aplicación de consola desarrollada en **Java** que simula el sistema de gestión de envíos de la empresa Servientrega. Fue diseñado como resolución a una prueba técnica de Programación Orientada a Objetos, aplicando los pilares fundamentales del paradigma y las buenas prácticas de desarrollo.

## 🚀 Características principales
- **Gestión en tiempo real:** Registro de paquetes, consulta de estado, entrega y listado de historial.
- **Tres tipos de servicio:** Envíos Nacionales, Express e Internacionales, cada uno con sus propias reglas de negocio y tarifas.
- **Validación de datos:** Uso de bloques `try-catch` y estructuras de control para asegurar que el usuario ingrese datos correctos (evitando caídas del sistema).
- **Persistencia en memoria:** Uso de `HashMap` para envíos en tránsito (búsqueda rápida por guía) y `ArrayList` para el historial de entregados.

## 🧠 Conceptos de POO aplicados
- **Herencia Multinivel:** `EnvioExpress` hereda de `EnvioNacional`, la cual hereda de `Envio`; mientras que `EnvioInternacional` hereda directamente de `Envio`.
- **Sobreescritura de métodos:** Personalización de `calcularCosto()` y `describir()` en cada clase hija adaptando las reglas de negocio.
- **Sobrecarga de constructores:** En `EnvioExpress` para manejar el tiempo de entrega por defecto (48 horas) o personalizado (24 horas).
- **Polimorfismo:** El sistema maneja un `HashMap` y un `ArrayList` del tipo `Envio`, permitiendo tratar a todos los subtipos de envíos de manera uniforme sin preguntar por su tipo específico.
- **Encapsulamiento:** Atributos privados con acceso controlado a través de métodos getters y setters, incluyendo validaciones lógicas (ej. el peso no puede ser negativo).

## 🛠️ Reglas de Negocio Implementadas
- **Kilos facturables:** Redondeo hacia arriba del peso para el cálculo de la tarifa (ej. 2.3 kg se cobra como 3 kg).
- **Guías únicas:** Generación automática de guías con prefijos (`NAC`, `EXP`, `INT`) y un contador global que solo avanza si el envío es válido y registrado.
- **Regla 4x4 (Aduana):** Rechazo automático de envíos internacionales que superen los 4 kg o los 400 USD.
- **Recargos:** Cálculo exacto de costos por entrega a domicilio, envíos express (recargo del 40%) y aranceles internacionales (costo base + $20).

## ⚙️ Tecnologías utilizadas
- **Java** (JDK 8 o superior)
- **IntelliJ IDEA** (Entorno de desarrollo)
- Programación Orientada a Objetos (POO)
- Colecciones de Java (`List`, `Map`, `ArrayList`, `HashMap`)

## 💻 Cómo ejecutar el proyecto
1. Clona este repositorio o descarga el código fuente.
2. Abre el proyecto en tu IDE de preferencia (IntelliJ IDEA, Eclipse, NetBeans).
3. Ubica la clase `Main.java` dentro del paquete principal.
4. Ejecuta el método `main` y empieza a interactuar con el menú en la consola.
