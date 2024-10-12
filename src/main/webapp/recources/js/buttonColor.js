function getCoordinates(event) {
    console.log("sadasads")
    const svg = event.target;
    const rect = svg.getBoundingClientRect(); // Получаем размеры SVG
    const x = event.clientX - rect.left; // Координаты клика в пикселях по X
    const y = event.clientY - rect.top;  // Координаты клика в пикселях по Y

    // Получаем значение радиуса R из скрытого элемента
    const r = parseFloat(document.getElementById("hiddenR").innerText);

    const centerX = 200; // Центр оси X
    const centerY = 200; // Центр оси Y

    // Преобразуем пиксельные координаты в систему координат графика
    const graphX = (x - centerX) / (150 / r); // Преобразование по X
    const graphY = (centerY - y) / (150 / r); // Преобразование по Y

    // Вызов AJAX-команды для передачи координат в Managed Bean
    setCoordinates([{name: 'x', value: graphX}, {name: 'y', value: graphY}]);
}