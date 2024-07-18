/*
Questão 1
Alterando texto com um botão: crie uma estrutura com dois botões. Estes dois botões deverão possuir cada um evento.
Evento do primeiro botão: trocar um texto contido no html;
Evento do segundo botão: dar reload na página para que o texto anterior retorne.
Estilize usando os recursos do Bootstrap;
 */
const paragraph = document.getElementById('paragraph');
const inputText = document.getElementById('input-text');
const changeTextBtn = document.getElementById('change-btn');
const reloadBtn = document.getElementById('reload-btn');

changeTextBtn.addEventListener('click', function() {
    const newText = inputText.value;
    if (newText) {
        paragraph.textContent = newText;
        localStorage.setItem('savedText', newText);
    } else {
        paragraph.textContent = 'Texto alterado com sucesso!';
    }
});

reloadBtn.addEventListener('click', function() {
    location.reload();
});

window.addEventListener('load', function() {
    const savedText = localStorage.getItem('savedText');
    if (savedText) {
        paragraph.textContent = savedText;
    }
});

/*
Questão2
Mudar cor do texto com Color Pick: criar uma estrutura usando o atributo ‘color’ do elemento formulário. Os eventos deverão ser:
Escolher a cor;
Clicar sobre o texto e modificar a cor;
Use a class ‘form-control’ para estilizar o elemento de formulário;
 */
const colorBtn = document.getElementById('color-btn');
const colorPicker = document.getElementById('color-picker');
const textElement = document.getElementById('change-color');


colorBtn.addEventListener('click', function() {
    colorPicker.click();
});

colorPicker.addEventListener('input', function() {
    const selectedColor = colorPicker.value;
    textElement.style.color = selectedColor;
});

/*
Questão 3
Counter em Js: criar uma estrutura para uma div receber um counter + dois botões para executar este counter.
Um botão deverá adicionar +1 valor iniciado por zero;
O segundo botão deverá resetar o contador.
 */
const counterDisplay = document.getElementById('counter');
const incrementBtn = document.getElementById('increment-btn');
const resetBtn = document.getElementById('reset-btn');

let count = 0;

incrementBtn.addEventListener('click', function() {
    count++;
    counterDisplay.textContent = count;
});

resetBtn.addEventListener('click', function() {
    count = 0;
    counterDisplay.textContent = count;
});

/*
Questão 4
Trocando filtro de Imagem: crie uma estrutura utilizando até 3 imagens (de preferência a mesma), use um editor online para aplicar filtros nestas imagens. Neste exemplo foi aplicado filtro em duas imagens:
Inserir 3 botões cada um para exibir uma imagem;
Estilize os botões usando as classes do Bootstrap;
Você pode usar document.getElementById();
Abaixo você vê o exemplo das organizações das imagens.
 
Foi utilizado IA 
*/
const img1Btn = document.getElementById('img1-btn');
const img2Btn = document.getElementById('img2-btn');
const img3Btn = document.getElementById('img3-btn');
const imageDisplay = document.getElementById('image-display');

function resetButtonStyles() {
    img1Btn.classList.remove('btn-primary');
    img2Btn.classList.remove('btn-secondary');
    img3Btn.classList.remove('btn-success');
    img1Btn.classList.add('btn-light');
    img2Btn.classList.add('btn-light');
    img3Btn.classList.add('btn-light');
}

img1Btn.addEventListener('click', function() {
    resetButtonStyles();
    img1Btn.classList.remove('btn-light');
    img1Btn.classList.add('btn-primary');
    imageDisplay.src = 'img/Vem-Ser-DBC-03_1600x500.ba2d5e.jpg';
    imageDisplay.style.filter = 'grayscale(0%)';
});

img2Btn.addEventListener('click', function() {
    resetButtonStyles();
    img2Btn.classList.remove('btn-light');
    img2Btn.classList.add('btn-secondary');
    imageDisplay.src = 'img/Vem-Ser-DBC-03_1600x500.ba2d5e.jpg';
    imageDisplay.style.filter = 'sepia(100%)';
});

img3Btn.addEventListener('click', function() {
    resetButtonStyles();
    img3Btn.classList.remove('btn-light');
    img3Btn.classList.add('btn-success');
    imageDisplay.src = 'img/Vem-Ser-DBC-03_1600x500.ba2d5e.jpg';
    imageDisplay.style.filter = 'blur(5px)';
});

/*
Questão 5
Calculadora I. Desenvolver uma calculadora somente as 4 operações usando elementos de formulário do HTML5.
Estilização em CSS básica;
Aplicar as estruturas condicionais para desenvolver as 4 operações;
A seleção das operações está na caixa Options.
 */

document.getElementById('calculate-btn').addEventListener('click', function() {
  const num1 = parseFloat(document.getElementById('num1').value);
  const num2 = parseFloat(document.getElementById('num2').value);
  const operation = document.getElementById('operation').value;
  
  let result;
  
  if (isNaN(num1) || isNaN(num2)) {
      alert('Por favor, insira ambos os números.');
      return;
  }

  switch (operation) {
      case 'add':
          result = num1 + num2;
          break;
      case 'subtract':
          result = num1 - num2;
          break;
      case 'multiply':
          result = num1 * num2;
          break;
      case 'divide':
          if (num2 === 0) {
              alert('Divisão por zero não é permitida.');
              result = 'Indefinido';
          } else {
              result = num1 / num2;
          }
          break;
      default:
          alert('Escolha uma operação.');
          return;
  }
  
  document.getElementById('result-value').textContent = result;
});

document.getElementById('clear-btn').addEventListener('click', function() {
  document.getElementById('num1').value = '';
  document.getElementById('num2').value = '';
  document.getElementById('operation').value = '';
  document.getElementById('result-value').textContent = '0';
});


/*
Questão 6
Calculadora II. Desenvolver uma calculadora somente com as 4 operações, adicionando a opção de limpar e apagar dígito por dígito.  A calculadora deverá ter:
estilização em CSS (pode ser básica);
é possível criar funções com (exemplo):
getElementById('resultado').innerTEXT
você pode consultar a documentação do Js e pode estudar sobre a função ‘eval’ no link: <eval() - JavaScript | MDN.> 
na imagem abaixo você vê um exemplo da calculadora com sua estilização;
Você pode consultar neste link Como criar uma calculadora com HTML, CSS e JavaScript - Programadores Deprê, que possui o desenvolvimento de uma calculadora similar, porém com declaração mais antiga.
 */
function insert(num) {
  var numero = document.getElementById('resultado').innerHTML;

  if (numero === "Nada...") {
      numero = "";
  }
  document.getElementById('resultado').innerHTML = numero + num;
}

function clean() {
  document.getElementById('resultado').innerHTML = "";
}

function back() {
  var resultado = document.getElementById('resultado').innerHTML;
  document.getElementById('resultado').innerHTML = resultado.substring(0, resultado.length - 1);
}

function calcular() {
  var resultado = document.getElementById('resultado').innerHTML;
  if (resultado) {
      try {
          document.getElementById('resultado').innerHTML = eval(resultado);
      } catch (error) {
          document.getElementById('resultado').innerHTML = "Erro";
      }
  } else {
      document.getElementById('resultado').innerHTML = "Nada...";
  }
}