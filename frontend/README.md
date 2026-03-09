# React + TypeScript + Vite App

Esta es una base optimizada para el desarrollo de aplicaciones web modernas utilizando **React** y **TypeScript**, gestionada bajo el ecosistema de **Vite** para garantizar un entorno de desarrollo ágil y despliegues eficientes.

---

## Stack Tecnológico

* **Frontend:** React (Hooks & Functional Components)
* **Lenguaje:** TypeScript (Tipado estricto)
* **Tooling:** Vite (Hot Module Replacement - HMR)
* **Linter:** ESLint (Configuración avanzada para React y TS)

---

## Características del Entorno

El proyecto está configurado para utilizar uno de los dos plugins oficiales de Vite, según los requerimientos de rendimiento:

* **@vitejs/plugin-react:** Utiliza [Babel](https://babeljs.io/) para la funcionalidad de Fast Refresh.
* **@vitejs/plugin-react-swc:** Utiliza [SWC](https://swc.rs/) (un compilador basado en Rust) para un Fast Refresh significativamente más rápido en proyectos de gran escala.

---

## Configuración Avanzada de Calidad de Código (ESLint)

Para entornos de producción y aplicaciones robustas, se recomienda elevar las reglas de validación. La configuración actual permite habilitar reglas conscientes del tipado (*type-aware*).

### Habilitar reglas de tipado estrictas

Actualiza el archivo `eslint.config.js` para incluir reglas de validación profunda:

```javascript
export default tseslint.config([
  globalIgnores(['dist']),
  {
    files: ['**/*.{ts,tsx}'],
    extends: [
      // Sustituir la configuración recomendada por una más estricta según sea necesario:
      ...tseslint.configs.recommendedTypeChecked,
      // Opcional: Para reglas más rigurosas
      // ...tseslint.configs.strictTypeChecked,
      // Opcional: Para reglas de estilo consistentes
      // ...tseslint.configs.stylisticTypeChecked,
    ],
    languageOptions: {
      parserOptions: {
        project: ['./tsconfig.node.json', './tsconfig.app.json'],
        tsconfigRootDir: import.meta.dirname,
      },
    },
  },
])
```

### Reglas específicas para React y React DOM
Se recomienda integrar los plugins eslint-plugin-react-x y eslint-plugin-react-dom para asegurar las mejores prácticas en el manejo del DOM y componentes de React:
```
JavaScript
import reactX from 'eslint-plugin-react-x'
import reactDom from 'eslint-plugin-react-dom'

export default tseslint.config([
  {
    files: ['**/*.{ts,tsx}'],
    extends: [
      // Reglas recomendadas para React con TypeScript
      reactX.configs['recommended-typescript'],
      // Reglas recomendadas para interacciones con el DOM
      reactDom.configs.recommended,
    ],
  },
])
```
### Instalación y Ejecución
Clonar el repositorio:

Bash
git clone [https://github.com/tu-usuario/nombre-del-proyecto.git](https://github.com/tu-usuario/nombre-del-proyecto.git)
cd nombre-del-proyecto
Instalar dependencias:

Bash
npm install
Iniciar entorno de desarrollo:

Bash
npm run dev
Generar build de producción:

Bash
npm run build