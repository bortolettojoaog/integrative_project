var botao = document.getElementById('botao');

botao.addEventListener('click', function() {
    var nav = document.querySelector('.botao');
    if (nav.classList.contains('active-menu')) {
        nav.classList.remove('active-menu');
        document.querySelector('nav.menu-mobile').style.display = 'none';
    } else {
        nav.classList.add('active-menu');
        document.querySelector('nav.menu-mobile').style.display = 'block';
    }
});

var botaoModal = document.querySelectorAll('#modal');

for (var i = 0; i < botaoModal.length; i++) {
    let element = botaoModal[i];
    element.addEventListener('click', function(event) {
        event.preventDefault();

        let child = element.querySelector('div.img');
        let attr = child.getAttribute('img-src');
        
        if (attr == undefined) return;

        document.querySelector('.modal-create').style.display = 'block';

        document.querySelector('#modal-img').setAttribute('style', "background-image: url('images/" + attr + "');");
    })
}

var botaoFechar = document.querySelector(".modal-close");

if (botaoFechar != null) {
	botaoFechar.addEventListener('click', function() {
	    document.querySelector('.modal-create').style.display = 'none';
	});
}