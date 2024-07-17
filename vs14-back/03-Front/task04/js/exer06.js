/* Mafalda trabalha numa biblioteca e frequentemente ela verifica no sistema quantos livros fazem parte do acervo e quais são estes livros. Excepcionalmente hoje, além dela fazer a verificação de todos os livros, ela precisa verificar quantos e quais são os livros da categoria de Terror que a biblioteca possui. Crie um programa que percorra toda a lista de livros e retorne os títulos de todos os livros que estão catalogados e, em seguida, liste somente os livros da categoria Terror. 
Saida do programa:
O catalogo dos livros da biblioteca é composto por:
it. O morro dos ventos Uiavantes. O silêncio dos inocentes. Boneco de neve. Bird Box. Orgulho e preconceito.

Os livros de terror são:
It. O exorcista. Drácula.
*/
const livros = [
  { id: '01', categoria: 'Terror', titulo: 'It' },
  { id: '02', categoria: 'Terror', titulo: 'O Exorcista' },
  { id: '03', categoria: 'Terror', titulo: 'Drácula' },
  { id: '04', categoria: 'Romance', titulo: 'O Morro dos Ventos Uivantes' },
  { id: '05', categoria: 'Policial', titulo: 'O Silêncio dos Inocentes' },
  { id: '06', categoria: 'Suspense', titulo: 'Boneco de Neve' },
  { id: '07', categoria: 'Suspense', titulo: 'Bird Box' },
  { id: '08', categoria: 'Romance', titulo: 'Orgulho e Preconceito' }
];

function gerarRelatorioLivros() {
  let resultado = 'O catálogo dos livros da biblioteca é composto por:<br>';

  livros.forEach(function(livro) {
      resultado += `${livro.titulo}. `;
  });

  return resultado;
}

function gerarRelatorioTerror() {
  let resultado = '<br>Os livros de terror são:<br>';

  livros.filter(function(livro) {
      return livro.categoria === 'Terror';
  }).forEach(function(livro) {
      resultado += `${livro.titulo}. `;
  });

  return resultado;
}

function exibirRelatorio() {
  const resultadoElement = document.getElementById('resultado');
  const relatorioLivros = gerarRelatorioLivros();
  const relatorioTerror = gerarRelatorioTerror();
  resultadoElement.innerHTML = relatorioLivros + relatorioTerror;
}

window.onload = function() {
  exibirRelatorio();
};