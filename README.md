# Conversor de Monedas en Java

Este proyecto es un conversor de monedas que utiliza una API externa para obtener tasas de cambio actualizadas.

## Funcionalidades
- Conversión entre ARS, USD, EUR y MXN.
- Validación de opciones ingresadas por el usuario.
- Consumo de API con HttpClient y Gson.

## Tecnologías
- Java
- Gson
- ExchangeRate API

## Uso
1. Ejecutar el programa.
2. Seleccionar la opción deseada.
3. Ingresar el valor a convertir.
4. El programa mostrará el resultado.

## Requisitos
- Es necesario configurar una **API Key** para que el programa funcione.
- Debe existir un archivo llamado **.env** en la raíz del proyecto con el siguiente contenido: `API_KEY=tu_api_key_aqui`
- El programa leerá la API Key desde ese archivo para realizar las conversiones.