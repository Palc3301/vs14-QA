/*
Cardapio Queijos, mussarela,minas,parmesão,gorgonzola
lista atualizada: Mussarela, Minas, Parmesão, Gorgonzola, Mascapone, Ricota, Provolone
Lista atualizada em ordem alfabética: Gorgonzola, Mascarpone, Minas, Mussarela, Parmesão, Provolone, Ricota
Lista atualizada: CacheStorage, Gorgonzola, Maascarpone, Minas, Mussarela, Parmesão, Prato, Provolone, Ricota*/

var queijos = ['Mussarela', 'Minas', 'Parmesão', 'Prato', 'Gorgonzola'];

function exibirCardapio() {
    var resultado = 'Cardápio de Queijos: ' + queijos.join(', ');
    return resultado;
}


function atualizarLista() {
    queijos.push('Mascarpone', 'Ricota', 'Provolone');
    var resultado = 'Lista atualizada: ' + queijos.join(', ');
    return resultado;
}

function ordenarLista() {
    queijos.sort();
    var resultado = 'Lista atualizada em ordem alfabética: ' + queijos.join(', ');
    return resultado;
}

function adicionarCottage() {
    var i;
    for (i = 0; i < queijos.length; i++) {
        if (queijos[i] > 'Cottage') {
            break;
        }
    }
    queijos.splice(i, 0, 'Cottage');
    var resultado = 'Lista atualizada com Cottage: ' + queijos.join(', ');
    return resultado;
}


function exibirResultados() {
    var resultadoElement = document.getElementById('resultado');
    var resultados = [
        exibirCardapio(),
        atualizarLista(),
        ordenarLista(),
        adicionarCottage()
    ];
    resultadoElement.textContent = resultados.join('\n');
}

window.onload = function() {
    exibirResultados();
};