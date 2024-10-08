let x = 0, y, r, n = 0, checkX = false, checkY = false;

function validateX() {
    if (!checkX) {
        x = document.getElementById("nums").value;
    } else if (x === null || x === "") {
        createNotification("Значение X не выбрано");
        return false;
    } else if (!isNumeric(x)) {
        createNotification("X должно быть числом");
        return false;
    }
    checkX = false;
    return true;
}

function validateY() {
    if (!checkY) {
        y = parseInt(document.querySelector("input[name=Y-input]").value.replace(",", "."));
    }
    if (y === undefined || y === "") {
        createNotification("Y не введён");
        return false;
    } else if (!isNumeric(y)) {
        createNotification("Y не число");
        return false;
    } else if (!(y >= -3 && y <= 5)) {
        createNotification("Y не входит в область допустимых значений (-3 <= Y <= 5)");
        return false;
    }
    checkY = false
    return true;
}

function validateR() {
    r = parseInt(document.querySelector("input[name=R-input]").value.replace(",", "."));
    if (r === undefined || r === "") {
        createNotification("R не введён");
        return false;
    } else if (!isNumeric(r)) {
        createNotification("R не число");
        return false;
    } else if (!(r >= 2 && r <= 5)) {
        createNotification("R не входит в область допустимых значений (2 <= R <= 5)");
        return false;
    }
    return true;
}

function createNotification(message) {
    let outputContainer = document.getElementById("outputContainer");
    showNotification(message)
    if (outputContainer.contains(document.querySelector(".notification"))) {
        let stub = document.querySelector(".notification");
        stub.textContent = message;
        stub.classList.replace("outputStub", "errorStub");
    } else {
        let notificationTableRow = document.createElement("h4");
        notificationTableRow.innerHTML = "<span class='notification errorStub'></span>";
        outputContainer.prepend(notificationTableRow);
        let span = document.querySelector(".notification");
        span.textContent = message;
    }
}

function showNotification(message) {
    let audioPlayer4 = document.getElementById('audioPlayer4');
    audioPlayer4.play();
    const popup = document.getElementById('popup');
    popup.textContent = message;
    popup.style.display = 'block';
    popup.style.backgroundColor = 'red';
    setTimeout(function () {
        popup.style.display = 'none';
    }, 3000); // 3000 миллисекунд = 3 секунды
}

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);

}

document.addEventListener('DOMContentLoaded', function () {
    const numsSelect = document.getElementById('nums');
    const xValueSpan = document.getElementById('x-value');

    numsSelect.addEventListener('change', function () {
        const selectedValue = numsSelect.value;
        xValueSpan.textContent = 'Текущий X: ' + selectedValue;
        x = selectedValue;
    });
});

document.getElementById("plot").addEventListener("click", function (event) {
    console.log("Нажали на график")
    if (!validateR()) return;
    let svg = event.currentTarget;
    let rect = svg.getBoundingClientRect();
    let R = document.querySelector("input[name='R-input']").value;
    console.log(R)
    if (R === "") {
        createNotification("Необходимо выбрать параметр R")

    } else {
        let clickX = event.clientX - rect.left;
        let clickY = event.clientY - rect.top;
        let xValue = ((clickX - 200) / 150) * R;
        let yValue = ((200 - clickY) / 150) * R;
        document.getElementById("nums").value = Math.round(xValue * 100) / 100;
        document.querySelector("input[name='Y-input']").value = Math.round(yValue * 100) / 100;
        let pointer = document.getElementById("pointer");
        pointer.setAttribute('cx', clickX);
        pointer.setAttribute('cy', clickY);
        pointer.setAttribute('visibility', 'visible');
        pointer.setAttribute('fill', 'black');
        document.getElementById("x-value").textContent = "Текущий X: " + parseFloat(xValue).toFixed(2);
        x = parseFloat(xValue).toFixed(2);
        y = parseFloat(yValue).toFixed(2);
        checkX = checkY = true;
    }
});

function sendedData(message) {
    //let audioPlayer4 = document.getElementById('audioPlayer4');
    //audioPlayer4.play();
    const popup = document.getElementById('popup');
    popup.textContent = message;
    popup.style.display = 'block';
    popup.style.backgroundColor = 'green';
    setTimeout(function () {
        popup.style.display = 'none';
    }, 3000);

}


function makeData(data) {
    console.log(data.x, data.y, data.r, data.hit, data.time);

    let newRow = document.createElement("tr");
    let tdN = document.createElement("td");
    let tdX = document.createElement("td");
    let tdY = document.createElement("td");
    let tdR = document.createElement("td");
    let tdH = document.createElement("td");
    let tdT = document.createElement("td");
    let tdS = document.createElement("td");

    // Заполняем значения
    tdN.innerHTML = n;
    tdX.innerHTML = data.x;
    tdY.innerHTML = data.y;
    tdR.innerHTML = data.r;
    n++; // Увеличиваем индекс для следующей строки

    // Проверка попадания
    let pointer = document.getElementById("pointer");
    let cx = 200 + 150 * Number.parseFloat(data.x) / Number.parseFloat(data.r);
    let cy = 200 - 150 * Number.parseFloat(data.y) / Number.parseFloat(data.r);

    if (data.hit) {
        pointer.setAttribute("fill", "green");
        sendedData("Попадание");
        tdH.innerHTML = "Да";
        let audioPlayer = document.getElementById('audioPlayer');
        audioPlayer.play();
    } else {
        pointer.setAttribute("fill", "red");

        createNotification("Мимо!");
        tdH.innerHTML = "Нет";
        let audioPlayer2 = document.getElementById('audioPlayer2');
        audioPlayer2.play();
    }
    let currentDate = new Date();
    tdT.innerHTML = currentDate;
    let d = new Date();
    tdS.innerHTML = d.toLocaleTimeString();
    pointer.setAttribute('cx', cx);
    pointer.setAttribute('cy', cy);
    pointer.setAttribute('visibility', 'visible');
    newRow.appendChild(tdN);
    newRow.appendChild(tdX);
    newRow.appendChild(tdY);
    newRow.appendChild(tdR);
    newRow.appendChild(tdH);
    newRow.appendChild(tdT);
    newRow.appendChild(tdS);
    document.getElementById("outputContainer").appendChild(newRow);
}

document.getElementById("checkButton").onclick = function () {
    if (validateX() && validateY() && validateR()) {
        //console.log(x, y, r);
        const params = new URLSearchParams({
            x: x,
            y: y,
            r: r
        });
        fetch(`controller?${params.toString()}`, {
            method: 'GET',
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Ошибка запроса: ' + response.status);
                }
                if (response.headers.get("Content-Length") === "0") {
                    throw new Error('Сервер вернул пустой ответ');
                }
                return response.json();
            })
            .then(data => {
                console.log("Ответ от сервера:", data);
                makeData(data);
            })
            .catch(error => {
                console.error('Ошибка при отправке запроса:', error);
            });
    }
};
document.getElementById("resLink").clicked = function (event) {
    event.preventDefault();
    let audioPlayer3 = document.getElementById('audioPlayer3');
    audioPlayer3.play().then(() => {
        audioPlayer3.onended = function() {
            window.location.href = "result.jsp";
        };
    }).catch(error => {
        window.location.href = "result.jsp";
    });
}

