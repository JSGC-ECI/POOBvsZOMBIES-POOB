# 🧠🌻 POOBvsZOMBIES

SANTIAGO ROCHA DURAN y MARIA IRMA DIAZ ROZO
- JUAN SEBASTIÁN GUAYAZÁN CLAVIJO <juan.guayazan-c@mail.escuelaing.edu.co>

Programación Orientada a Objetos (ISIS POOB - 2 LEC y POOB-201 LAB)   
Decanatura Ingeniería de Sistemas → Centro de Estudios de Ingeniería de Software    
Ingeniería de Sistemas e Ingeniería Estadística    
Escuela Colombiana de Ingeniería Julio Garavito    
2024-2

---

## 🎮 Descripción

**POOBvsZOMBIES** es una versión mejorada e inspirada en el clásico videojuego *Plants vs Zombies*. En esta versión se integran nuevas reglas, elementos personalizados, modalidades de juego avanzadas y una arquitectura preparada para extensibilidad.

🌿 El jugador deberá proteger su base usando plantas con habilidades especiales.  
🧟‍♂️ El oponente controlará hordas de zombies con comportamientos distintos.  
📊 Se implementó un sistema de puntajes, persistencia, y múltiples modalidades de enfrentamiento.

---

## 🧩 Características del Juego

### 🌟 Modalidades de Juego
- 👤 **Player vs Machine (PvsM)**  
- 🤖 **Machine vs Machine (MvsM)**  
- 🎮 **Player vs Player (PvsP)**  

### ⚔️ Elementos del Juego

#### 🌱 Plantas
- 🌻 *Sunflower* – genera soles ☀️
- 🟢 *Peashooter* – dispara guisantes
- 🥔 *PotatoMine* – explota tras activación
- 🧱 *Wall-nut* – planta defensiva con alta resistencia
- 🌞 *ECIPlant* – genera soles grandes personalizados

#### 🧟 Zombies
- 🧠 *Basic* – zombi básico
- 🔺 *Conehead* – zombi con cono protector
- 🪖 *Buckethead* – zombi con cubeta resistente
- 🧠 *Brainstein* – genera cerebros como el girasol
- 💥 *ECIZombie* – lanza proyectiles POOmBas

### 🧠 Recursos y Sistema de Puntos
- ☀️ *Soles* para las plantas (25 cada 10 seg)
- 🧠 *Cerebros* para los zombies (50 cada 10 seg)
- 🏆 El ganador se define por puntuación o llegada a la casa de Dave

---

## 🔧 Funcionalidades

✔️ Selección de modalidad de juego  
✔️ Selección de plantas y zombies disponibles  
✔️ Configuración de recursos iniciales y tiempo de partida  
✔️ Simulación de hordas y rondas por oleadas  
✔️ Control de zombies automáticos (modo supervivencia)  
✔️ Podadoras automáticas por fila  
✔️ Herramienta pala para remover plantas sin recuperar soles  
✔️ Guardado y carga del estado del juego  
✔️ Log de errores y excepciones personalizadas

---

## 🧠 Arquitectura

El juego está desarrollado bajo una arquitectura por capas:

- 🎨 **Capa de presentación:** GUI dinámica para visualización y control
- 🧠 **Capa de aplicación:** lógica de juego, control de turnos, recursos y reglas
- 🧩 **Capa de dominio:** entidades, clases de plantas/zombies, estrategias


---

## 🧪 Pruebas

🔍 Se incluyen pruebas JUnit para:

- Validar las reglas del juego  
- Verificar estrategias automáticas  
- Simular partidas entre jugadores y máquinas  
- Comprobar condiciones de victoria/derrota

---

## 💾 Persistencia

💽 El estado del juego puede ser **guardado y cargado** para continuar partidas posteriormente.

---

## 📝 Requisitos Técnicos

- Java 8 o superior ☕  
- IDE: Eclipse o BlueJ 💻  
- JUnit 4+ para pruebas  
- Manejo de excepciones personalizado

---

## 📅 Planificación por ciclos

📦 El desarrollo se realizó en **4 versiones**, cada una con entregables definidos:

1. **Versión 1:** Configuración básica (PvsZombiesOriginal)  
2. **Versión 2:** Persistencia y todos los elementos del juego  
3. **Versión 3:** Integración de estrategias personalizadas  
4. **Versión 4:** Pruebas de aceptación y mejoras finales
