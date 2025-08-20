# 📌 POOBvsZOMBIES

> ⚠️ Estado: **En desarrollo**

Simulador inspirado en Plants vs Zombies cada uno con habilidades y características propias. El objetivo principal es administrar recursos y diseñar tácticas para defenderse o atacar según el modo de juego seleccionado. Explora estrategias entre jugadores y máquinas con nuevos tipos de plantas, zombies, modos de juego y lógica de puntajes.

---

## 👥 Autores

SANTIAGO ROCHA DURAN y MARIA IRMA DIAZ ROZO
- JUAN SEBASTIÁN GUAYAZÁN CLAVIJO <juan.guayazan-c@mail.escuelaing.edu.co>

Programación Orientada a Objetos (ISIS POOB - 2 LEC y POOB-201 LAB)   
Decanatura Ingeniería de Sistemas → Centro de Estudios de Ingeniería de Software    
Ingeniería de Sistemas e Ingeniería Estadística    
Escuela Colombiana de Ingeniería Julio Garavito    
2024-2

## 🧠 Índice

- 📌 [Nombre del Proyecto](#-poobvszombies)
- 🚀 [Características](#-características)
- ⚙️ [Tecnologías](#️-tecnologías)
- 📦 [Instalación](#-instalación-y-requisitos)
- ▶️ [Uso](#️-uso)
- 🧪 [Pruebas](#-pruebas)
- 📁 [Estructura del Proyecto](#-estructura-del-proyecto)
- 📌 [TODOs / Funcionalidades Futuras](#-todos--funcionalidades-futuras)
- 👥 [Autores](#-autores)
- 📄 [Licencia](#-licencia)

---

## 🚀 Características

- ✅ Tres modos de juego: PvP, PvM, MvM
- ✅ Plantas y zombies personalizados (ECIPlant y ECIZombie)
- ✅ Sistema de recursos: soles y cerebros
- ✅ Persistencia de partidas y sistema de puntajes
- ✅ Interfaz gráfica amigable y adaptable
- ✅ Ideal para fines educativos, desarrollo de lógica y estrategia

---

## ⚙️ Tecnologías

- Lenguaje: `Java`
- Herramientas: `BlueJ`, `JUnit`, `IntelliJ IDEA`
- Dependencias: JUnit 4, librerías estándar de Java

---

## 📦 Instalación y Requisitos

### Clonar el repositorio
```bash
git clone https://github.com/JuanSebastianGuayazanClavijoECI/POOBvsZOMBIES-POOB.git
```

### Requisitos

* Java 8+
* BlueJ, Eclipse o IntelliJ IDEA 
* JUnit 4+

### Instalación

1. Abrir el proyecto en BlueJ o importar como proyecto Java en Eclipse
2. Ejecutar las clases de la capa presentación o el simulador principal

---

## ▶️ Uso

Perfecto 🙌 Aquí te dejo cómo quedaría la **sección de Uso** en tu README de **POOBvsZOMBIES**, con el formato que me compartiste:

---

## ▶️ Uso

```bash
# Desde la carpeta del proyecto
javac src/presentation/Start.java
java src/presentation/Start
```

> \[!NOTE]
> Asegúrate de tener instalado **Java 17 o superior** para garantizar la compatibilidad.

> \[!TIP]
> Puedes ejecutar directamente la clase `Start` desde tu IDE favorito (ej. **IntelliJ IDEA**, **BlueJ**, **Eclipse**) sin necesidad de compilar manualmente.

> \[!IMPORTANT]
> La carpeta `resources` dentro de `presentation` debe mantenerse con su estructura, ya que contiene imágenes y sonidos necesarios para el juego.

> \[!WARNING]
> Si se cambian los nombres de las imágenes o audios, el juego podría no cargarlos correctamente y fallar al iniciar.

> \[!CAUTION]
> No modifiques los paquetes `domain` y `presentation` sin revisar las dependencias, ya que contienen la lógica principal y la interfaz gráfica.


---

## 🧪 Pruebas

```bash
javac -d bin src/domain/*.java src/presentation/*.java src/test/GameTest.java
```

---

## 📁 Estructura del Proyecto

```bash
📦 POOBvsZOMBIES-POOB
 ┣ 📂 src
 ┃ ┣ 📂 domain
 ┃ ┃ ┣ 📜 Attacking.java
 ┃ ┃ ┣ 📜 Basic.java
 ┃ ┃ ┣ 📜 Board.java
 ┃ ┃ ┣ 📜 Brainstein.java
 ┃ ┃ ┣ 📜 Buckethead.java
 ┃ ┃ ┣ 📜 Cell.java
 ┃ ┃ ┣ 📜 Character.java
 ┃ ┃ ┣ 📜 Conehead.java
 ┃ ┃ ┣ 📜 Defensive.java
 ┃ ┃ ┣ 📜 ECIPlant.java
 ┃ ┃ ┣ 📜 ECIZombie.java
 ┃ ┃ ┣ 📜 Evolve.java
 ┃ ┃ ┣ 📜 GameManager.java
 ┃ ┃ ┣ 📜 GameManagerMIvsMO.java
 ┃ ┃ ┣ 📜 GameManagerMIvsMS.java
 ┃ ┃ ┣ 📜 GameManagerMSvsMO.java
 ┃ ┃ ┣ 📜 GameManagerMSvsMS.java
 ┃ ┃ ┣ 📜 GameManagerPvsMO.java
 ┃ ┃ ┣ 📜 Generate.java
 ┃ ┃ ┣ 📜 Movement.java
 ┃ ┃ ┣ 📜 Pea.java
 ┃ ┃ ┣ 📜 Peashooter.java
 ┃ ┃ ┣ 📜 Plant.java
 ┃ ┃ ┣ 📜 POOBvsZOMBIESException.java
 ┃ ┃ ┣ 📜 PotatoMine.java
 ┃ ┃ ┣ 📜 Shovel.java
 ┃ ┃ ┣ 📜 Sunflower.java
 ┃ ┃ ┣ 📜 Support.java
 ┃ ┃ ┣ 📜 WallNut.java
 ┃ ┃ ┗ 📜 Zombies.java
 ┃ ┣ 📂 presentation
 ┃ ┃ ┣ 📂 resources
 ┃ ┃ ┃ ┣ 📂 images
 ┃ ┃ ┃ ┃ ┣ 📂 Fondos
 ┃ ┃ ┃ ┃ ┣ 📂 Nueva carpeta
 ┃ ┃ ┃ ┃ ┣ 📂 Objetos
 ┃ ┃ ┃ ┃ ┣ 📂 Pantallas
 ┃ ┃ ┃ ┃ ┣ 📂 Plantas 
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 Modos
 ┃ ┃ ┃ ┃ ┃ ┗ 📂 Tarjetas
 ┃ ┃ ┃ ┃ ┣ 📂 Zombies
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 Modos
 ┃ ┃ ┃ ┃ ┗ ┗ 📂 Tarjetas
 ┃ ┃ ┃ ┣ 📂 audio
 ┃ ┃ ┣ 📜 AudioManager.java
 ┃ ┃ ┣ 📜 Game.java
 ┃ ┃ ┣ 📜 GameMIvsMO.java
 ┃ ┃ ┣ 📜 GameMIvsMS.java
 ┃ ┃ ┣ 📜 GameMSvsMO.java
 ┃ ┃ ┣ 📜 GameMSvsMS.java
 ┃ ┃ ┣ 📜 GamePvsMO.java
 ┃ ┃ ┣ 📜 GamePvsMS.java
 ┃ ┃ ┣ 📜 PlantsMachines.java
 ┃ ┃ ┣ 📜 Select.java
 ┃ ┃ ┣ 📜 Start.java
 ┃ ┃ ┣ 📜 ZombieMachines.java
 ┃ ┃ ┗ 📜 ZombiePlantSelector.java          
 ┃ ┣ 📂 test
 ┃ ┗ ┗ 📜 GameTest.java
 ┣ 📂 bin
 ┃ ┣ 📂 domain
 ┃ ┗ 📂 presentation
 ┣ 📂 docs
 ┃ ┣ 📂 domain
 ┃ ┃ ┗ 📂 class-use
 ┃ ┣ 📂 presentation
 ┃ ┃ ┗ 📂 class-use
 ┃ ┣ 📂 legal
 ┃ ┣ 📂 class-use
 ┃ ┣ 📂 resource-files
 ┃ ┃ ┗ 📂 fonts
 ┗ ┗ 📂 script-files
```
👉 [Diagrama de clases](./POOBvsZOMBIES.asta)
---

## 📌 TODOs / Funcionalidades Futuras

* [ ] Añadir sonidos y animaciones
* [ ] Mejorar interfaz gráfica con controles dinámicos
* [ ] Integrar un sistema de dificultad progresiva

---

## 📄 Licencia

Este proyecto está licenciado bajo propósitos académicos y educativos. Puedes consultar el archivo [LICENSE](./LICENSE) para más información.

---

