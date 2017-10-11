function goToTop(){
	$('html, body').animate({scrollTop: 0}, 'fast');
}

function getLength() {
    getWord = document.getElementById('resumo').value,
    num = document.getElementById('palavras');
   
    if ( getWord == '' ) num.textContent = '0 palavras.';
    else if ( getWord.search( /\s[a-z0-9áÁéÉíÍóÓúÚçÇàÀ]+$/gi ) > -1 ) num.textContent = getWord.replace( / +/g, ' ' ).split(' ').length + ' palavras.';
    else if ( getWord.search( /[^\s]$/ ) > -1 ) num.textContent = '1 palavra.';
} 

//Mostrar tempo de sessão
var tempo = new Number();
var url = window.location.pathname;
// Tempo em segundos
//tempo = 1800;
tempo = 1800;
function startCountdown(){
    // Se o tempo não for zerado
    if((tempo - 1) >= 0){
        // Pega a parte inteira dos minutos
        var min = parseInt(tempo/60);
        // Calcula os segundos restantes
        var seg = tempo%60;
        // Formata o número menor que dez, ex: 08, 07, ...
        if(min < 10){
            min = "0"+min;
            min = min.substr(0, 2);
        }

        if(seg <=9){
            seg = "0"+seg;
        }
        // Cria a variável para formatar no estilo hora/cronômetro
        horaImprimivel = 'Sessão: '+ min + 'min' + seg + 's';
        // JQuery pra setar o valor
        $("#sessao").html(horaImprimivel);

        // Define que a função será executada novamente em 1000ms = 1 segundo
        setTimeout('startCountdown()',1000);
        // diminui o tempo
        tempo--;
    // Quando o contador chegar a zero faz esta ação
    } else {
    	if(url === '/<NOME-APLICAÇÃO>/dashboard.xhtml'){
    		window.open('../<NOME-APLICAÇÃO>/logout.xhtml', '_self');
    	}else{
    		window.open('../logout.xhtml', '_self');
    	}
    }
}
// Chama a função ao carregar a tela
startCountdown();




