/*Carlos aceitou o desafio proposto por sua irmã Ana e está desenvolvendo um código em JavaScript para calcular a diferença de idade entre eles. O programa deve exibir a mensagem "Nossa diferença de idade é: " e concatenar o resultado da subtração da idade de Carlos com a idade de Ana (ou de um amigo). O resultado pode ser negativo, refletindo o fato de Carlos ser mais velho. Ao implementar esse código, é necessário utilizar recursos ou funções que sigam as boas práticas de desenvolvimento, especialmente aquelas que melhoram a interação entre o usuário e a máquina. Informação adicional: Carlos tem 18 anos e Ana tem 15 anos. Desenvolva o programa utilizando o paradigma funcional.
*/
const idadeCarlos = 18;
const idadeAna = 15;

function diferencaDeIdade(idade1, idade2) {
    return idade1 - idade2;
}

function exibirDiferencaIdade(diferenca) {
    const resultadoElement = document.getElementById('resultado');
    resultadoElement.textContent = "A diferença de idade entre Carlos e Ana é: " + diferenca;
}

window.onload = function() {
    const diferenca = diferencaDeIdade(idadeCarlos, idadeAna);
    exibirDiferencaIdade(diferenca);
};