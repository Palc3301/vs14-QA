/*Crie um programa básico onde este recebe dois números naturais e o programa retorne a soma destes dois valores. */

document.getElementById('form').addEventListener('submit', function(event) {
  event.preventDefault(); 

  var num1 = parseFloat(document.getElementById('num1').value);
  var num2 = parseFloat(document.getElementById('num2').value);


  var soma = num1 + num2;

  document.getElementById('resultado').textContent = 'A soma é: ' + soma;
});
