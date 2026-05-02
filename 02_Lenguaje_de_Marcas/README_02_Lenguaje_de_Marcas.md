<div align="center">

![alt text](logo.png)


# NexHub Coworking

**Lenguajes de Marcas — Módulo 0373 · Miguel Montes Vicente · 1º DAW · 2026**

</div>

---

Web corporativa de NexHub Coworking hecha con HTML5, CSS3 y JavaScript vanilla. Sin frameworks, sin librerías externas. Cada cosa que se ve está escrita a mano.

---

## Estructura

```
02_Lenguaje_de_Marcas/

- index.html
- espacios.html
- tarifas.html
- comunidad.html
- contacto.html
- assets/
    - css/style.css
    - images/favicon.svg
    - js/main.js
```

---

## Páginas

| Archivo | Qué tiene |
|---------|----------|
| `index.html` | Hero con foto del espacio, seis tarjetas de servicios con imágenes y tres planes en preview |
| `espacios.html` | Open Space A y B, Oficina S y M, Sala Alpha, Sala Beta y Cabinas, cada uno con foto |
| `tarifas.html` | Seis planes de precio y tabla comparativa |
| `comunidad.html` | Cuatro perfiles de miembros, cuatro eventos con foto y tres testimonios |
| `contacto.html` | Formulario de contacto con validación en JavaScript |

---

## CSS

Todo el sistema de colores está en variables CSS al inicio de `style.css`. Si se quiere cambiar el color de acento de toda la web, es una sola línea:

```css
:root {
  --color-bg:      #0a0b0f;
  --color-surface: #10131a;
  --color-accent:  #6c63ff;
  --color-green:   #22d9a0;
  --color-text:    #eceef4;
  --color-muted:   #7a8599;
}
```

---

## JavaScript

`main.js` hace tres cosas:

- Menú hamburguesa en móvil: abre y cierra el `<nav>` con una X animada en SVG.
- Validación del formulario: comprueba campos obligatorios, formato de email con regex y mínimo 10 caracteres en el mensaje.
- Animaciones de scroll: usa `IntersectionObserver` para activar las clases `.reveal` cuando los elementos entran en pantalla.
- Actualización año del footer 
---

## Responsive

| Breakpoint | Qué cambia |
|------------|-----------|
| `max-width: 1024px` | Las rejillas de 3-4 columnas pasan a 2 |
| `max-width: 768px` | Menú hamburguesa, todo pasa a 1 columna |
| `max-width: 480px` | Hero más compacto, botones apilados |

---

## Imágenes

Las fotos son de Unsplash (licencia libre) y se cargan directamente desde su CDN. No hay nada que descargar. Todas tienen `loading="lazy"` menos el hero, que usa `eager` para que cargue sin retraso.

El favicon es un SVG con el hexágono y la N del logo.

---

## Abrir

Doble clic en `index.html`. Funciona en cualquier navegador sin necesidad de servidor.
