<div align="center">

<svg width="60" height="60" viewBox="0 0 32 32" xmlns="http://www.w3.org/2000/svg">
  <rect width="32" height="32" rx="6" fill="#0a0b0f"/>
  <polygon points="16,4 26,9.5 26,22.5 16,28 6,22.5 6,9.5" fill="#6c63ff" opacity="0.18"/>
  <polygon points="16,4 26,9.5 26,22.5 16,28 6,22.5 6,9.5" fill="none" stroke="#6c63ff" stroke-width="1.5"/>
  <text x="16" y="21" text-anchor="middle" font-family="Segoe UI,sans-serif" font-size="13" font-weight="800" fill="#6c63ff">N</text>
</svg>


# Reflexión Final

**Miguel Montes Vicente · 1º DAW · 2026**

</div>

---

Antes de este proyecto veía las asignaturas como piezas separadas. Bases de datos era una cosa, Java era otra, la web era otra. Trabajar en NexHub me hizo entender que son capas del mismo sistema. Cambiar el modelo de datos obliga a cambiar los servicios Java, que a su vez afectan a lo que puede mostrar la web. Tenerlo todo junto hace que eso sea evidente de una manera que no lo es cuando trabajas cada módulo por separado.

---

## Lo que he aprendido

Más allá de las tecnologías concretas, aprendí a pensar antes de implementar. Empezar por el diagrama E-R, luego el SQL, luego Java, luego la web, en ese orden, marcó la diferencia. Cada vez que me salté ese orden y fui directamente a escribir código sin tenerlo claro, tuve que volver atrás y rehacer cosas.

También aprendí que documentar no es un trámite. Los README que escribí me han salvado más de una vez cuando retomé el proyecto después de unos días y no recordaba exactamente cómo estaba estructurado.

---

## Lo que mejor me salió

El diseño de la base de datos. Empecé por el diagrama E-R antes de abrir phpMyAdmin, y eso fue la mejor decisión del proyecto. Pensar en las entidades y en cómo se relacionan antes de escribir SQL me obligó a entender el problema primero. El DDL salió bien a la primera y no tuve que volver a tocarlo.

El CSS también me salió bastante bien. Usar variables desde el principio en lugar de poner colores y medidas sueltos me ahorró mucho tiempo cuando tuve que ajustar el diseño en las cinco páginas a la vez.

---

## Lo que más me costó

La conexión JDBC fue lo más difícil. Entender el flujo `Connection → PreparedStatement → ResultSet` no es complicado en teoría, pero cuando empecé a tener problemas con recursos que se quedaban abiertos o excepciones que no capturaba bien, tardé bastante en encontrar la causa. El patrón try-with-resources lo entendí tarde, y ojalá lo hubiera aplicado desde el principio.

Separar el código en capas también requirió varios intentos. Al principio mezclaba la lógica de base de datos con la de presentación en el controlador. Cuando lo leía al día siguiente no entendía bien qué hacía cada parte. Refactorizarlo para que cada clase tuviera una responsabilidad clara fue tedioso pero mereció la pena.

---

## Lo que haría diferente

Haría tests de cada servicio Java antes de integrar el controlador. Varias veces encontré bugs que venían de un método concreto del servicio y los descubrí tarde, cuando ya tenía todo conectado. Con un test básico los habría pillado antes y me habría ahorrado horas.

También haría commits más pequeños y más frecuentes. Al principio hacía un commit grande cuando terminaba una sección entera. Al final del proyecto aprendí que un commit pequeño con un mensaje claro es mucho más útil cuando tienes que buscar cuándo dejó de funcionar algo.

---

## Conclusión

Lo que me llevo del proyecto es que la parte técnica es la más fácil de explicar pero no es la más importante. Lo importante es saber qué problema resuelves antes de escribir la primera línea de código. Y entender que el software que construyes tiene que poder entenderlo otra persona, no solo tú el día que lo escribiste.
