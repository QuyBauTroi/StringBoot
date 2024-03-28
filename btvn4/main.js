document.addEventListener('DOMContentLoaded', function () {
    const colors = [
        '#3498db',
        '#9b59b6',
        '#e74c3c',
        '#2c3e50',
        '#d35400',
    ];

    let totalBoxes = 0;


    const boxContainer = document.querySelector('.boxes');
    function createBox(color) {
        const box = document.createElement('div');
        box.classList.add('box');
        box.style.backgroundColor = color;
        box.addEventListener('click', function () {
            box.remove();
            totalBoxes--;
            updateScore();
        });
        return box;
    }


    const score = document.querySelector('.points');
    function updateScore() {
        score.textContent = 'Total box: ' + totalBoxes;
    }



    const btnMoreBox = document.getElementById('btn');
    function addMoreBoxes() {
        for (let i = 0; i < 5; i++) {
            const randomColor = colors[Math.floor(Math.random() * colors.length)];
            const box = createBox(randomColor);
            boxContainer.appendChild(box);
            totalBoxes++;
        }
        updateScore();
    }
    btnMoreBox.addEventListener('click', addMoreBoxes);


    addMoreBoxes();
});
