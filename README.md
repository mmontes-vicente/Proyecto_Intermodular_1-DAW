# Lenguajes de Marcas — Módulo 0373

Web corporativa de NexHub Coworking. HTML5, CSS3 y JavaScript vanilla, sin ningún framework externo.

---

## Estructura

```
web/

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

| Archivo | Contenido |
|---------|-----------|
| `index.html` | Hero con imagen del espacio, servicios destacados y tres planes en preview |
| `espacios.html` | Open Space A y B, Oficina S y M, Sala Alpha, Sala Beta y Cabinas — cada uno con foto |
| `tarifas.html` | Seis planes con precios y tabla comparativa |
| `comunidad.html` | Cuatro tipos de miembros, cuatro eventos periódicos y testimonios |
| `contacto.html` | Formulario validado por JavaScript y datos de contacto |

---

## CSS

Todo el sistema de colores, tamaños y radios está en variables CSS al inicio de `style.css`. Si se quiere cambiar el color de acento o el fondo de toda la web, es una sola línea:

```css
:root {
  --color-bg:      #0d0f14;
  --color-surface: #13161e;
  --color-accent:  #6c63ff;
  --color-green:   #22d9a0;
  --color-text:    #e8eaf0;
  --font-main:     'Segoe UI', system-ui, sans-serif;
}
```

Los componentes usan Flexbox y Grid sin clases de utilidad. El CSS está en un único archivo para las cinco páginas.

---

## JavaScript

`main.js` hace tres cosas concretas y nada más:

- Abre y cierra el menú en móvil al pulsar el botón hamburguesa.
- Valida el formulario de contacto antes de enviarlo: campos obligatorios, formato de email con regex, mínimo de 10 caracteres en el mensaje y aceptación de la política de privacidad.
- Hace scroll suave cuando se pulsa un enlace de anclaje interno.

---

## Responsive

`@media (max-width: 1024px)` — rejillas de 3-4 columnas pasan a 2.  
`@media (max-width: 768px)` — menú hamburguesa, todas las rejillas a 1 columna.  
`@media (max-width: 480px)` — hero más compacto, botones apilados.

---

## Imágenes

Las fotos vienen de Unsplash (licencia libre) y se cargan desde su CDN. No hay nada que descargar ni instalar. Todas tienen `loading="lazy"`.

El favicon es un SVG en `assets/images/favicon.svg`: fondo oscuro, hexágono con borde en el color accent y la letra N.

---

## Abrir

Doble clic en `index.html`. Funciona en cualquier navegador sin necesidad de servidor.
