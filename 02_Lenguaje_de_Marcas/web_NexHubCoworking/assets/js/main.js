/* ============================================================
   NexHub Coworking — main.js
   Modulo: Lenguajes de Marcas (0373)
   ============================================================ */

/* --- 1. HEADER: sombra al hacer scroll --- */
const header = document.querySelector('.header');
if (header) {
  window.addEventListener('scroll', () => {
    header.classList.toggle('scrolled', window.scrollY > 20);
  }, { passive: true });
}

/* --- 2. MENU MOVIL: abrir/cerrar con icono hamburguesa/cruz --- */
const navToggle = document.getElementById('navToggle');
const nav       = document.getElementById('nav');

if (navToggle && nav) {
  navToggle.addEventListener('click', () => {
    const isOpen = nav.classList.toggle('open');
    navToggle.classList.toggle('active', isOpen);
    navToggle.setAttribute('aria-expanded', isOpen);
  });

  /* Cerrar al hacer clic en un enlace */
  nav.querySelectorAll('.nav__link').forEach(link => {
    link.addEventListener('click', () => {
      nav.classList.remove('open');
      navToggle.classList.remove('active');
      navToggle.setAttribute('aria-expanded', 'false');
    });
  });

  /* Cerrar al hacer clic fuera */
  document.addEventListener('click', (e) => {
    if (!header.contains(e.target)) {
      nav.classList.remove('open');
      navToggle.classList.remove('active');
      navToggle.setAttribute('aria-expanded', 'false');
    }
  });
}

/* --- 3. ANIMACIONES DE SCROLL con Intersection Observer --- */
const revealEls = document.querySelectorAll(
  '.reveal, .reveal--left, .reveal--right, .reveal--scale'
);

if (revealEls.length > 0 && 'IntersectionObserver' in window) {
  const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        entry.target.classList.add('visible');
        observer.unobserve(entry.target); /* anima solo una vez */
      }
    });
  }, {
    threshold: 0.12,
    rootMargin: '0px 0px -40px 0px'
  });

  revealEls.forEach(el => observer.observe(el));
} else {
  /* Fallback: mostrar todo si IntersectionObserver no está disponible */
  revealEls.forEach(el => el.classList.add('visible'));
}

/* --- 4. VALIDACION FORMULARIO DE CONTACTO --- */
const form = document.getElementById('contactForm');
if (form) {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  form.addEventListener('submit', (e) => {
    e.preventDefault();
    let ok = true;

    const nombre = document.getElementById('nombre');
    const email  = document.getElementById('email');
    const motivo = document.getElementById('motivo');
    const mensaje = document.getElementById('mensaje');
    const privacidad = document.getElementById('privacidad');

    clearErrors();

    if (!nombre?.value.trim()) {
      showError('errNombre', 'El nombre es obligatorio.');
      ok = false;
    }
    if (!email?.value.trim() || !emailRegex.test(email.value.trim())) {
      showError('errEmail', 'Introduce un email valido.');
      ok = false;
    }
    if (!motivo?.value) {
      showError('errMotivo', 'Selecciona un motivo de contacto.');
      ok = false;
    }
    if (!mensaje?.value.trim() || mensaje.value.trim().length < 10) {
      showError('errMensaje', 'El mensaje debe tener al menos 10 caracteres.');
      ok = false;
    }
    if (!privacidad?.checked) {
      showError('errPrivacidad', 'Debes aceptar la politica de privacidad.');
      ok = false;
    }

    if (ok) {
      form.style.display = 'none';
      const success = document.getElementById('formSuccess');
      if (success) success.style.display = 'block';
    }
  });

  function showError(id, msg) {
    const el = document.getElementById(id);
    if (el) el.textContent = msg;
  }
  function clearErrors() {
    ['errNombre','errEmail','errMotivo','errMensaje','errPrivacidad']
      .forEach(id => { const el = document.getElementById(id); if (el) el.textContent = ''; });
  }
}

// ---------- 5. ACTUALIZACIÓN DE AÑO EN FOOTER ----------
const footerYear = document.getElementById('footer-year');
if (footerYear) {
  footerYear.textContent = new Date().getFullYear();
}

/* --- 6. SCROLL SUAVE en anclas internas --- */
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
  anchor.addEventListener('click', (e) => {
    const target = document.querySelector(anchor.getAttribute('href'));
    if (target) {
      e.preventDefault();
      target.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }
  });
});
