/*
Você é dono de uma pizzaria e no final do dia sempre será gerado um relatório resumido dos produtos que foram consumidos no dia. Este relatório gera uma lista de informações com a identificação, nome do cliente, a pizza que comeu. Você precisa consultar no relatório:
Quais foram os pedidos de pizza;
Quais os clientes que pediram refrigerante;
Quais os clientes que pediram suco;
Quais os clientes que pediram cerveja;
Dica: você pode usar os métodos filter e map no Array;
→ Abaixo um modelo de saída do programa
Pizzaria Gula - Relatorio diário
No dia de hoje os pedidos de pizza foram:
Pizza Calabresa, Pizza Mafiosa, Pizza Calabresa, Pizza Mussarela, Pizza Quatro Queijos, Pizza Quatro Queijos

Os cliente que fizeram o pedido com refrigerante goram:
Claudino, Maribela, Jacinto, Olimpo

Os clientes que fizeram pedido com suco foram;
Pafuncio, Felisberta

Os clientes que fizeram pedido com cerveja foram:
Pafuncio, Felisberta
*/

// Lista de pedidos
var pedidos = [
  { id: 1, cliente: 'Claudino', item: 'Pizza Calabresa' },
  { id: 2, cliente: 'Maribela', item: 'Pizza Mafiosa' },
  { id: 3, cliente: 'Jacinto', item: 'Pizza Calabresa' },
  { id: 4, cliente: 'Olimpo', item: 'Pizza Mussarela' },
  { id: 5, cliente: 'Pafuncio', item: 'Pizza Quatro Queijos' },
  { id: 6, cliente: 'Felisberta', item: 'Pizza Quatro Queijos' },
  { id: 7, cliente: 'Claudino', item: 'Refrigerante' },
  { id: 8, cliente: 'Maribela', item: 'Refrigerante' },
  { id: 9, cliente: 'Jacinto', item: 'Refrigerante' },
  { id: 10, cliente: 'Olimpo', item: 'Refrigerante' },
  { id: 11, cliente: 'Pafuncio', item: 'Suco' },
  { id: 12, cliente: 'Felisberta', item: 'Suco' },
  { id: 13, cliente: 'Pafuncio', item: 'Cerveja' },
  { id: 14, cliente: 'Felisberta', item: 'Cerveja' }
];

function obterPedidosPizza(pedidos) {
  return pedidos
    .filter(function(pedido) { return pedido.item.indexOf('Pizza') === 0; })
    .map(function(pedido) { return pedido.item; });
}

function obterClientesPorItem(pedidos, item) {
  return pedidos
    .filter(function(pedido) { return pedido.item === item; })
    .map(function(pedido) { return pedido.cliente; })
    .filter(function(cliente, index, self) { return self.indexOf(cliente) === index; });
}

var pedidosPizza = obterPedidosPizza(pedidos);
var clientesRefrigerante = obterClientesPorItem(pedidos, 'Refrigerante');
var clientesSuco = obterClientesPorItem(pedidos, 'Suco');
var clientesCerveja = obterClientesPorItem(pedidos, 'Cerveja');

function exibirRelatorio() {
  var relatorio = '';

  relatorio += '<h2>Pizzaria Gula - Relatório diário</h2>';
  relatorio += '<p>No dia de hoje os pedidos de pizza foram:</p>';
  relatorio += '<p>' + pedidosPizza.join(', ') + '</p>';

  relatorio += '<p>Os clientes que fizeram o pedido com refrigerante foram:</p>';
  relatorio += '<p>' + clientesRefrigerante.join(', ') + '</p>';

  relatorio += '<p>Os clientes que fizeram pedido com suco foram:</p>';
  relatorio += '<p>' + clientesSuco.join(', ') + '</p>';

  relatorio += '<p>Os clientes que fizeram pedido com cerveja foram:</p>';
  relatorio += '<p>' + clientesCerveja.join(', ') + '</p>';

  document.getElementById('relatorio').innerHTML = relatorio;
}

window.onload = function() {
  exibirRelatorio();
};
