
function changeButtonStyle(button) {
    // Сбросить классы на всех кнопках
    var buttons = document.querySelectorAll('.button-container p\\:commandButton');
    buttons.forEach(function(btn) {
    btn.classList.remove('active-button');
    btn.classList.add('inactive-button');
});

    // Добавить активный стиль на нажатую кнопку
    button.classList.add('active-button');
    button.classList.remove('inactive-button');
}

