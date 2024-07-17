/*
Os num pares da lista são: 2 e possuem o indice: 1
Os num pares da lista são: 4 e possuem o indice: 3
Os num pares da lista são: 6 e possuem o indice: 5
Os num pares da lista são: 8 e possuem o indice: 7
Os num pares da lista são: 10 e possuem o indice: 9
Os num pares da lista são: 12 e possuem o indice: 11
Os num pares da lista são: 14 e possuem o indice: 13
Os num pares da lista são: 16 e possuem o indice: 15
Os num pares da lista são: 18 e possuem o indice: 17
Os num pares da lista são: 20 e possuem o indice: 19
Os num pares da lista são: 22 e possuem o indice: 21
Os num pares da lista são: 24 e possuem o indice: 23
Os num pares da lista são: 26 e possuem o indice: 25
Os num pares da lista são: 28 e possuem o indice: 27
Os num pares da lista são: 20 e possuem o indice: 29
*/

let numeros = [];
for (let i = 1; i <= 30; i++) {
    numeros.push(i);
}

function exibirNumeros(lista) {
    let resultado = '';
    for (let i = 0; i < lista.length; i++) {
        let numero = lista[i];
        let indice = i;

        if (numero % 2 === 0 && indice % 2 === 1) {
            resultado += `Os num pares da lista são: ${numero} e possuem o indice: ${indice}<br>`;
        }
    }
    return resultado;
}

function mostrarResultado() {
    const resultadoElement = document.getElementById('resultado');
    const resultado = exibirNumeros(numeros);
    resultadoElement.innerHTML = resultado;
}

window.onload = function() {
    mostrarResultado();
};